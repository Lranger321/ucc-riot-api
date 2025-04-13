package ucc.service;

import com.riot.api.model.RiotAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ucc.configuration.UccConfig;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UccMemberService {

    private final UccConfig uccConfig;
    private final AccountService accountService;

    @EventListener(ApplicationStartedEvent.class)
    public void findMembers() {
        List<RiotAccount> ucc = uccConfig.getMembers().stream()
                .map(uccMember -> accountService.getRiotAccountByAccount(uccMember.getName(), uccMember.getTagLine()))
                .toList();
        log.info("Register {} players", ucc.size());
    }
}
