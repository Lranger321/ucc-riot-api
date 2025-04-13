package ucc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatForCherry extends PlayerStatForGame {

    @Schema(description = "Среднее место игрока на арене", example = "1.2")
    private BigDecimal averagePlace;

    public StatForCherry(BigDecimal averagePlace) {
        this.averagePlace = averagePlace;
    }
}
