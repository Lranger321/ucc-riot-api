package ucc.riot.api;

import com.riot.api.RiotApi;
import com.riot.api.model.RiotAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SummonerApi {

    private final RiotApi riotApi;

    public RiotAccount getRiotAccount(String name, String tagLine) {
        return riotApi.getAccount(name, tagLine).getBody();
    }
}
