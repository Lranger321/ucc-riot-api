package ucc.controller.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ucc.dto.PlayerStat;
import ucc.service.StatService;

import java.util.List;

@RestController
@RequestMapping("/api/stat/v2")
@RequiredArgsConstructor
@Tag(
        name = "LOL Statistics API",
        description = "API для получения статистики игроков"
)
public class StatV2Controller {

    private final StatService statService;

    @GetMapping("/players")
    @Operation(
            summary = "Получить статистику всех игроков",
            description = "Возвращает список статистик по всем участникам UCC"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Успешный запрос"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Внутренняя ошибка сервера"
    )
    public List<PlayerStat> getStatByPlayer(@RequestParam List<String> players) {
        return statService.getPlayersStatForPlayers(players);
    }
}
