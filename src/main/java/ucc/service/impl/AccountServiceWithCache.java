package ucc.service.impl;

import com.riot.api.model.RiotAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ucc.riot.api.SummonerApi;
import ucc.service.AccountService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceWithCache implements AccountService {

    private final SummonerApi summonerApi;
    private final Map<String, RiotAccount> riotAccountMap = new LinkedHashMap<>();

    @Override
    public List<RiotAccount> getRiotAccounts() {
        return new ArrayList<>(riotAccountMap.values());
    }

    @Override
    public RiotAccount getRiotAccountByAccount(String name, String tagLine) {
        var riotAccount = summonerApi.getRiotAccount(name, tagLine);
        riotAccountMap.put(riotAccount.getPuuid(), riotAccount);
        return riotAccount;
    }

}
