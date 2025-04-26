package ucc.service.stat;

import org.springframework.stereotype.Service;
import ucc.dto.StatForCherry;
import ucc.persistence.model.GameMode;
import ucc.persistence.model.Participant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class CherryGameStatParser extends StatParser<StatForCherry> {

    @Override
    protected StatForCherry getStat(List<Participant> participantDtoList) {
        return new StatForCherry(BigDecimal.valueOf(participantDtoList.stream()
                        .mapToInt(Participant::getPlacement)
                        .filter(Objects::nonNull)
                        .average()
                        .orElse(0)));
    }

    @Override
    public GameMode gameMode() {
        return GameMode.CHERRY;
    }
}
