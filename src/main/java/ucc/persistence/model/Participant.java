package ucc.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Participant {

    private final String riotIdGameName;
    private final String championName;
    private final Integer kills;
    private final Integer deaths;
    private final Integer assists;
    private final Integer teamId;
    private final Boolean win;
    private final String teamPosition;
    private final Integer placement;
    private final Integer pentaKills;
    private final Integer quadraKills;
    private final Integer totalDamageDealtToChampions;
}
