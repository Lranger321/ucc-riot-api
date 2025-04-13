package ucc.service.stat;

import com.riot.api.model.GameMode;
import com.riot.api.model.ParticipantDto;
import org.springframework.stereotype.Service;
import ucc.dto.StatForNormal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClassicGameStatParser extends StatParser<StatForNormal> {

    private static final int PERCENT = 100;

    @Override
    protected StatForNormal getStat(List<ParticipantDto> matches) {
        var stat = new StatForNormal();

        Map<String, Long> roleFrequency = matches.stream()
                .filter(p -> p.getTeamPosition() != null)
                .collect(Collectors.groupingBy(ParticipantDto::getTeamPosition, Collectors.counting()));

        stat.setMostFrequentRole(roleFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A"));

        long winCount = matches.stream()
                .filter(p -> p.getWin() != null && p.getWin())
                .count();

        BigDecimal winRate = BigDecimal.valueOf((double) winCount / matches.size() * PERCENT)
                .setScale(2, RoundingMode.HALF_UP);
        stat.setWinRate(winRate);

        return stat;
    }

    @Override
    public GameMode gameMode() {
        return GameMode.CLASSIC;
    }
}
