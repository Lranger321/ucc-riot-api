package ucc.service.impl;

import com.ucc.api.model.PlayerDto;
import com.ucc.api.model.PlayerRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ucc.exception.PlayerException;
import ucc.mapper.PlayerMapper;
import ucc.persistence.repository.PlayerRepository;
import ucc.riot.api.SummonerApi;
import ucc.service.PlayerService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final SummonerApi summonerApi;
    private final PlayerMapper playerMapper;

    @Override
    public List<PlayerDto> getAllRegistredPlayers() {
        return playerMapper.mapToPlayerDto(playerRepository.getRegisteredPlayers());
    }

    @Override
    public PlayerDto addPlayer(PlayerRequestDto playerDto) {
        try {
            var riotAccount = playerMapper.mapToPlayer(summonerApi.getRiotAccount(playerDto.getGameName(), playerDto.getTagLine()));
            playerRepository.regPlayer(riotAccount);
            return playerMapper.mapToPlayerDto(riotAccount);
        } catch (Exception e) {
            var exceptionLog = String.format("Возникла ошибка при регистрирования пользователя.Exception: %s", e.getMessage());
            log.error(exceptionLog);
            throw new PlayerException(exceptionLog);
        }
    }

    @Override
    public void deletePlayer(String puuid) {
        playerRepository.deletePlayer(puuid);
    }
}
