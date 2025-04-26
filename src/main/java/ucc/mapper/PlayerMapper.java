package ucc.mapper;

import com.riot.api.model.RiotAccount;
import com.ucc.api.model.PlayerDto;
import org.mapstruct.Mapper;
import ucc.persistence.model.Player;

import java.util.List;

@Mapper
public interface PlayerMapper {

    List<PlayerDto> mapToPlayerDto(List<Player> players);

    PlayerDto mapToPlayerDto(Player player);

    Player mapToPlayer(RiotAccount riotAccount);
}
