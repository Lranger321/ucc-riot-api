package ucc.riot.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MatchesVariable {

    private static final int DEFAULT_START = 0;
    private static final int DEFAULT_COUNT = 20;

    /**
     * Epoch timestamp
     */
    private Long startTime;
    /**
     * Epoch timestamp
     */
    private Long endTime;
    private Integer queue;
    private String type;
    private int start;
    private int count;

    public MatchesVariable() {
        start = DEFAULT_START;
        count = DEFAULT_COUNT;
    }
}
