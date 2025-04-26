package ucc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class StatForCherry extends PlayerStatForGame {

    @Schema(description = "Среднее место игрока на арене", example = "1.2")
    private BigDecimal averagePlace;

    public StatForCherry(BigDecimal averagePlace) {
        this.averagePlace = averagePlace;
    }
}
