package ucc.service.impl;

import com.ucc.api.model.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ucc.dto.PlayerStat;
import ucc.dto.PlayerStatForGame;
import ucc.persistence.model.GameMode;
import ucc.persistence.model.Match;
import ucc.persistence.model.Participant;
import ucc.riot.model.request.MatchesVariable;
import ucc.service.LolMatchService;
import ucc.service.PlayerService;
import ucc.service.StatService;
import ucc.service.stat.DefaultStatParser;
import ucc.service.stat.StatParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private static final DefaultStatParser DEFAULT_STAT_PARSER = new DefaultStatParser();

    private final PlayerService accountService;
    private final LolMatchService matchService;
    private final Map<GameMode, StatParser<PlayerStatForGame>> statParserMap;

    @Override
    public List<PlayerStat> getPlayersStat() {
        List<PlayerDto> players = accountService.getAllRegistredPlayers();
        List<CompletableFuture<PlayerStat>> futures = players.stream()
                .map(this::getFutureForPlayerStat)
                .toList();
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );
        return allFutures.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(toList())
        ).join();
    }

    private CompletableFuture<PlayerStat> getFutureForPlayerStat(PlayerDto riotAccount) {
        return CompletableFuture
                .supplyAsync(() -> matchService.getMatchesForPlayer(riotAccount, new MatchesVariable()))
                .thenApplyAsync(matchIds -> getStat(matchIds.stream()
                        .collect(Collectors.groupingBy(Match::getGameMode)), riotAccount.getGameName()));
    }

    private PlayerStat getStat(Map<GameMode, List<Match>> matchesPerGameModes, String name) {
        Map<GameMode, PlayerStatForGame> matches = new HashMap<>();
        for (GameMode mode : matchesPerGameModes.keySet()) {
            matches.put(mode, statParserMap.getOrDefault(mode, DEFAULT_STAT_PARSER).getPlayerStat(getGames(matchesPerGameModes.get(mode), name)));
        }
        return PlayerStat.builder()
                .playerName(name)
                .games(matches)
                .build();
    }

    private List<Participant> getGames(List<Match> matchDtos, String name) {
        return matchDtos.stream()
                .map(Match::getParticipant)
                .toList();
    }
}
