package ucc.service.stat;

import com.riot.api.model.GameMode;
import com.riot.api.model.ParticipantDto;
import ucc.dto.PlayerStatForGame;

import java.util.List;

public class DefaultStatParser extends StatParser<PlayerStatForGame> {

    @Override
    protected PlayerStatForGame getStat(List<ParticipantDto> participantDtoList) {
        return new PlayerStatForGame();
    }

    @Override
    public GameMode gameMode() {
        throw new UnsupportedOperationException();
    }
}
