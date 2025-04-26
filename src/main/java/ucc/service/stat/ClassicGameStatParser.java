package ucc.service.stat;

import org.springframework.stereotype.Service;
import ucc.dto.StatForNormal;
import ucc.persistence.model.GameMode;
import ucc.persistence.model.Participant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClassicGameStatParser extends StatParser<StatForNormal> {

    private static final int PERCENT = 100;

    @Override
    protected StatForNormal getStat(List<Participant> matches) {
        var stat = StatForNormal.builder();

        Map<String, Long> roleFrequency = matches.stream()
                .filter(p -> p.getTeamPosition() != null)
                .collect(Collectors.groupingBy(Participant::getTeamPosition, Collectors.counting()));

        stat.mostFrequentRole(roleFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A"));

        stat.pentaKillCount(matches.stream().mapToInt(Participant::getPentaKills)
                .sum());
        stat.quadroKillCount(matches.stream().mapToInt(Participant::getQuadraKills)
                .sum());

        long winCount = matches.stream()
                .filter(p -> p.getWin() != null && p.getWin())
                .count();

        BigDecimal winRate = BigDecimal.valueOf((double) winCount / matches.size() * PERCENT)
                .setScale(2, RoundingMode.HALF_UP);
        stat.winRate(winRate);

        return stat.build();
    }

    @Override
    public GameMode gameMode() {
        return GameMode.CLASSIC;
    }
}
