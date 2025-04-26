package ucc.controller.v2;

import com.ucc.api.PlayersApi;
import com.ucc.api.model.PlayerDto;
import com.ucc.api.model.PlayerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucc.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientV2Controller implements PlayersApi {

    private final PlayerService playerService;

    @Override
    public ResponseEntity<Object> deletePlayer(String puuid) {
        playerService.deletePlayer(puuid);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllRegistredPlayers());
    }

    @Override
    public ResponseEntity<PlayerDto> registerPlayer(PlayerRequestDto playerRequest) {
        return ResponseEntity.ok(playerService.addPlayer(playerRequest));
    }
}
