package ucc.riot.api;

import com.riot.api.LolApi;
import com.riot.api.model.MatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ucc.riot.model.request.MatchesVariable;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchesApi {

    private final LolApi lolApi;

    public List<String> getMatchesByPuuid(String puuid, MatchesVariable variable) {
        return lolApi.getMatchIdsByPUUID(
                puuid,
                variable.getStartTime(),
                variable.getEndTime(),
                variable.getQueue(),
                variable.getType(),
                variable.getStart(),
                variable.getCount()
        ).getBody();
    }

    @Cacheable(value = "matches", key = "#matchId")
    public MatchDto getMatchStat(String matchId) {
        return lolApi.getMatchById(matchId).getBody();
    }

}
