package ucc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ucc.dto.PlayerStat;
import ucc.service.StatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "UCC Statistics API",
        description = "API для получения статистики игроков UCC"
)
public class StatV1tController {

    private final StatService statService;

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
    @Deprecated
    @GetMapping("/api/ucc/getStat")
    public List<PlayerStat> getPlayerStat() {
        return statService.getPlayersStat();
    }
}
