package ucc.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatForNormal extends PlayerStatForGame {

    private String mostFrequentRole;
    private BigDecimal winRate;
}
