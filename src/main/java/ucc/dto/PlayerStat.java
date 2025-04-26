package ucc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ucc.persistence.model.GameMode;

import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
public class PlayerStat {

    @Schema(description = "Имя игрока", example = "Lranger")
    private String playerName;
    @Schema(description = "Статистика матчей")
    private Map<GameMode, PlayerStatForGame> games;
}
