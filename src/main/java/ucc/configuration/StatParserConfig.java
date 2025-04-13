package ucc.configuration;

import com.riot.api.model.GameMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ucc.dto.PlayerStatForGame;
import ucc.service.stat.StatParser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class StatParserConfig {

    @Bean
    public Map<GameMode, StatParser<PlayerStatForGame>> statParserMap(List<StatParser<? extends PlayerStatForGame>> parsers) {
        return parsers.stream()
                .collect(Collectors.toMap(StatParser::gameMode, parser -> (StatParser<PlayerStatForGame>) parser));
    }
}
