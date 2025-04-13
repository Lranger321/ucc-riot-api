package ucc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatForRanked extends StatForNormal {

    @Schema(description = "Изменение рейтинга (пока не реализованно, возможно будет выпелено)", example = "25")
    private int ratingChange;
}
