package ucc.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Player {

    private final String puuid;
    private final String gameName;
    private final String tagLine;
}
