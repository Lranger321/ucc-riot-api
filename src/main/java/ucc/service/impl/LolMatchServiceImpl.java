package ucc.service.impl;

import com.riot.api.model.MatchDto;
import com.ucc.api.model.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import ucc.mapper.MatchMapper;
import ucc.persistence.model.Match;
import ucc.persistence.repository.MatchRepository;
import ucc.persistence.repository.ParticipantRepository;
import ucc.riot.api.MatchesApi;
import ucc.riot.model.request.MatchesVariable;
import ucc.service.LolMatchService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class LolMatchServiceImpl implements LolMatchService {

    private static final String RU_PREFIX = "RU_";
    private static final Map<String, ReentrantLock> LOCKS = new ConcurrentHashMap<>();

    private final MatchesApi matchesApi;
    private final MatchRepository matchRepository;
    private final ParticipantRepository participantRepository;
    private final TransactionTemplate transactionTemplate;
    private final MatchMapper mapper;

    @Override
    public List<Match> getMatchesForPlayer(PlayerDto player, MatchesVariable matchesVariable) {
        return matchesApi.getMatchesByPuuid(player.getPuuid(), matchesVariable).stream()
                .map(id -> getMatch(id, player.getGameName()))
                .toList();
    }

    private Match getMatch(String id, String name) {
        String cleanId = id.replaceAll(RU_PREFIX, StringUtils.EMPTY);

        ReentrantLock lock = LOCKS.computeIfAbsent(cleanId, k -> new ReentrantLock());
        lock.lock();
        try {
            if (matchRepository.containsMatch(cleanId)) {
                return matchRepository.getMatchByIdAndPlayerName(cleanId, name);
            }
            var matchDto = matchesApi.getMatchStat(id);
            var match = mapToMatch(matchDto, name);
            transactionTemplate.execute(status -> {
                try {
                    matchRepository.saveMatch(match);
                    participantRepository.saveAll(
                            mapper.mapToParticipantList(matchDto.getInfo().getParticipants()),
                            match.getId());
                    return null;
                } catch (Exception e) {
                    status.setRollbackOnly(); // Откат транзакции
                    throw e;
                }
            });
            return match;
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            LOCKS.remove(cleanId, lock);
        }
    }

    private Match mapToMatch(MatchDto matchDto, String name) {
        return mapper.mapToMatch(matchDto, matchDto.getInfo().getParticipants().stream()
                .filter(participant -> participant.getRiotIdGameName().equals(name))
                .findFirst()
                .get());
    }
}
