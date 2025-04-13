package ucc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ucc.dto.PlayerStat;
import ucc.service.MatchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final MatchService matchService;

    @GetMapping("/api/ucc/getStat")
    public List<PlayerStat> getPlayerStat() {
        return matchService.getPlayersStat();
    }
}
