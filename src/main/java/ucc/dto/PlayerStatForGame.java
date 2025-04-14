package ucc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class PlayerStatForGame {

    @Schema(description = "Титул игрока", example = "10")
    private int count;
    @Schema(description = "Титул игрока", example = "1.2")
    private BigDecimal averageKda;
    @Schema(description = "Титул игрока", example = "Ahri")
    private String mostFrequentCharacter;
    @Schema(description = "Словарь ключ персонаж, значение кол-во игр")
    private Map<String, Long> characterCount;
}
