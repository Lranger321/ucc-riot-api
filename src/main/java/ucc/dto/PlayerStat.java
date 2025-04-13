package ucc.dto;

import com.riot.api.model.GameMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
public class PlayerStat {

    private String playerName;
    private String title;
    private Map<GameMode, PlayerStatForGame> games;
}
