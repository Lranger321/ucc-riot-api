package ucc.service.stat;

import com.riot.api.model.GameMode;
import com.riot.api.model.ParticipantDto;
import org.springframework.stereotype.Service;
import ucc.dto.StatForCherry;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class CherryGameStatParser extends StatParser<StatForCherry> {

    @Override
    protected StatForCherry getStat(List<ParticipantDto> participantDtoList) {
        return new StatForCherry(BigDecimal.valueOf(participantDtoList.stream()
                .mapToInt(ParticipantDto::getPlacement)
                .filter(Objects::nonNull)
                .average()
                .orElse(0)));
    }

    @Override
    public GameMode gameMode() {
        return GameMode.CHERRY;
    }
}
