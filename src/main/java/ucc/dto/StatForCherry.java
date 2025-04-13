package ucc.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatForCherry extends PlayerStatForGame {

    private BigDecimal averagePlace;

    public StatForCherry(BigDecimal averagePlace) {
        this.averagePlace = averagePlace;
    }
}
