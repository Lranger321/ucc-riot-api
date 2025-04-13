package ucc.service.impl;

import com.riot.api.model.GameMode;
import com.riot.api.model.MatchDto;
import com.riot.api.model.ParticipantDto;
import com.riot.api.model.RiotAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ucc.dto.PlayerStat;
import ucc.dto.PlayerStatForGame;
import ucc.riot.api.MatchesApi;
import ucc.riot.model.request.MatchesVariable;
import ucc.service.AccountService;
import ucc.service.MatchService;
import ucc.service.stat.DefaultStatParser;
import ucc.service.stat.StatParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private static final DefaultStatParser DEFAULT_STAT_PARSER = new DefaultStatParser();

    private final AccountService accountService;
    private final MatchesApi matchesApi;
    private final Map<GameMode, StatParser<PlayerStatForGame>> statParserMap;

    @Override
    public List<PlayerStat> getPlayersStat() {
        List<RiotAccount> players = accountService.getRiotAccounts();
        List<CompletableFuture<PlayerStat>> futures = players.stream()
                .map(this::getFutureForPlayerStat)
                .toList();
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        return allFutures.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        ).join();
    }

    private CompletableFuture<PlayerStat> getFutureForPlayerStat(RiotAccount riotAccount) {
        return CompletableFuture
                .supplyAsync(() -> matchesApi.getMatchesByPuuid(riotAccount.getPuuid(), new MatchesVariable()))
                .thenApplyAsync(matchIds -> getStat(matchIds.stream()
                        .map(matchesApi::getMatchStat)
                        .collect(Collectors.groupingBy(matchDto -> matchDto.getInfo().getGameMode())), riotAccount.getGameName()));
    }

    private PlayerStat getStat(Map<GameMode, List<MatchDto>> matchesPerGameModes, String name) {
        Map<GameMode, PlayerStatForGame> matches = new HashMap<>();
        for (GameMode mode : matchesPerGameModes.keySet()) {
            matches.put(mode, statParserMap.getOrDefault(mode, DEFAULT_STAT_PARSER).getPlayerStat(getGames(matchesPerGameModes.get(mode), name)));
        }
        return PlayerStat.builder()
                .playerName(name)
                .games(matches)
                .build();
    }

    private List<ParticipantDto> getGames(List<MatchDto> matchDtos, String name) {
        return matchDtos.stream()
                .flatMap(match -> match.getInfo().getParticipants().stream())
                .filter(dto -> dto.getRiotIdGameName().equals(name))
                .toList();
    }
}
