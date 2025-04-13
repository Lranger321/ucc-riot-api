package ucc.configuration;

import feign.Contract;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RiotApiConfig {

    @Bean
    public RequestInterceptor apiKeyInterceptor(
            @Value("${riot.api.token}") String apiKey
    ) {
        return requestTemplate -> {
            requestTemplate.header("X-Riot-Token", apiKey);
        };
    }

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }

}
