package ucc.service.stat;

import ucc.dto.PlayerStatForGame;
import ucc.persistence.model.GameMode;
import ucc.persistence.model.Participant;

import java.util.List;

public class DefaultStatParser extends StatParser<PlayerStatForGame> {

    @Override
    protected PlayerStatForGame getStat(List<Participant> participantDtoList) {
        return new PlayerStatForGame();
    }

    @Override
    public GameMode gameMode() {
        throw new UnsupportedOperationException();
    }
}
