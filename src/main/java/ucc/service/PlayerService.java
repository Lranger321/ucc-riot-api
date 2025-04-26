package ucc.service;

import com.ucc.api.model.PlayerDto;
import com.ucc.api.model.PlayerRequestDto;

import java.util.List;

public interface PlayerService {

    List<PlayerDto> getAllRegistredPlayers();

    PlayerDto addPlayer(PlayerRequestDto playerDto);

    void deletePlayer(String puuid);
}
