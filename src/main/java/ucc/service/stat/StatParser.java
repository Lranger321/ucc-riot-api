package ucc.service.stat;

import com.riot.api.model.GameMode;
import com.riot.api.model.ParticipantDto;
import ucc.dto.PlayerStatForGame;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class StatParser<T extends PlayerStatForGame> {

    public T getPlayerStat(List<ParticipantDto> participantDtoList) {
        T stat = getStat(participantDtoList);
        stat.setCount(participantDtoList.size());
        stat.setAverageKda(getKda(participantDtoList));

        Map<String, Long> championFrequency = participantDtoList.stream()
                .filter(p -> p.getChampionName() != null)
                .collect(Collectors.groupingBy(ParticipantDto::getChampionName, Collectors.counting()));

        stat.setCharacterCount(championFrequency);
        stat.setMostFrequentCharacter(championFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A"));
        return stat;
    }

    protected abstract T getStat(List<ParticipantDto> participantDtoList);

    public abstract GameMode gameMode();

    private BigDecimal getKda(List<ParticipantDto> participantDtoList) {
        int totalKills = 0;
        int totalDeaths = 0;
        int totalAssists = 0;

        for (ParticipantDto match : participantDtoList) {
            totalKills += match.getKills() != null ? match.getKills() : 0;
            totalDeaths += match.getDeaths() != null ? match.getDeaths() : 0;
            totalAssists += match.getAssists() != null ? match.getAssists() : 0;
        }

        return (totalDeaths == 0
                ? BigDecimal.ZERO
                : BigDecimal.valueOf((totalKills + totalAssists) / (double) totalDeaths)
                .setScale(2, RoundingMode.HALF_UP));
    }
}
