package ucc.riot.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MatchesVariable {

    private static final int DEFAULT_COUNT = 15;
    private static final int DEFAULT_START = 0;

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
