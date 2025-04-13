package ucc.service;

import com.riot.api.model.RiotAccount;

import java.util.List;

public interface AccountService {

    List<RiotAccount> getRiotAccounts();

    RiotAccount getRiotAccountByAccount(String name, String tagLine);
}
