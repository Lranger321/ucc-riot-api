package ucc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ucc.dto.PlayerStat;
import ucc.service.MatchService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "UCC AI Statistics API",
        description = "API для получения статистики игроков UCC (AI)"
)
public class ClientWithAiController {

    private final MatchService matchService;
    private final ChatClient chatClient;

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
    @GetMapping("/api/ai/ucc/getStat")
    public Map<String, Object> getPlayerStat() {
        List<PlayerStat> playerStats = matchService.getPlayersStat();
        PromptTemplate promptTemplate = new PromptTemplate("""
                        Какой титул можно дать каждому игроку по его статистике в игре League of Legends?
                         {message}.
                """);
        promptTemplate.add("message", matchService.getPlayersStat());
        return Map.of(
                "matches", playerStats,
                "ai", chatClient.call(promptTemplate.create()).getResult().getOutput().getContent()
        );
    }
}