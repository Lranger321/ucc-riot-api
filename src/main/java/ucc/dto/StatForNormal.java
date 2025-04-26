package ucc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class StatForNormal extends PlayerStatForGame {

    @Schema(description = "Самая популярная роль", example = "TOP")
    private String mostFrequentRole;
    @Schema(description = "Процент винрейта", example = "50")
    private BigDecimal winRate;
    private long pentaKillCount;
    private long quadroKillCount;
}
