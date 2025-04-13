package ucc.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlayerStatForGame {

    private int count;
    private BigDecimal averageKda;
    private String mostFrequentCharacter;
}
