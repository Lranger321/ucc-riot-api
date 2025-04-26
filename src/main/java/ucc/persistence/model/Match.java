package ucc.persistence.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class Match {

    private final String id;
    private final GameMode gameMode;
    private final Participant participant;
}
