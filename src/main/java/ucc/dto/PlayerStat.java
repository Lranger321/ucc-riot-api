package ucc.dto;

import com.riot.api.model.GameMode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
public class PlayerStat {

    @Schema(description = "Имя игрока", example = "Lranger")
    private String playerName;
    @Schema(description = "Титул игрока", example = "Feed дня")
    private String title;
    @Schema(description = "Статистика матчей")
    private Map<GameMode, PlayerStatForGame> games;
}
