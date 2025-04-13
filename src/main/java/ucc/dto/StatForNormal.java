package ucc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatForNormal extends PlayerStatForGame {

    @Schema(description = "Самая популярная роль", example = "TOP")
    private String mostFrequentRole;
    @Schema(description = "Процент винрейта", example = "50")
    private BigDecimal winRate;
}
