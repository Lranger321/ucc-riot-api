package ucc.service.stat;

import ucc.dto.PlayerStatForGame;
import ucc.persistence.model.GameMode;
import ucc.persistence.model.Participant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class StatParser<T extends PlayerStatForGame> {

    public T getPlayerStat(List<Participant> participantList) {
        T stat = getStat(participantList);
        stat.setCount(participantList.size());
        stat.setAverageKda(getKda(participantList));

        Map<String, Long> championFrequency = participantList.stream()
                .filter(p -> p.getChampionName() != null)
                .collect(Collectors.groupingBy(Participant::getChampionName, Collectors.counting()));

        stat.setCharacterCount(championFrequency);
        stat.setMostFrequentCharacter(championFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A"));
        stat.setAverageDmg(getAverageDmg(participantList));
        return stat;
    }

    protected abstract T getStat(List<Participant> participantList);

    public abstract GameMode gameMode();

    private BigDecimal getKda(List<Participant> participantList) {
        int totalKills = 0;
        int totalDeaths = 0;
        int totalAssists = 0;

        for (Participant match : participantList) {
            totalKills += match.getKills() != null ? match.getKills() : 0;
            totalDeaths += match.getDeaths() != null ? match.getDeaths() : 0;
            totalAssists += match.getAssists() != null ? match.getAssists() : 0;
        }

        return (totalDeaths == 0
                ? BigDecimal.ZERO
                : BigDecimal.valueOf((totalKills + totalAssists) / (double) totalDeaths)
                .setScale(2, RoundingMode.HALF_UP));
    }

    private BigDecimal getAverageDmg(List<Participant> participantList) {
        return BigDecimal.valueOf(participantList.stream()
                .mapToInt(Participant::getTotalDamageDealtToChampions)
                .average()
                .orElse(0));
    }
}
