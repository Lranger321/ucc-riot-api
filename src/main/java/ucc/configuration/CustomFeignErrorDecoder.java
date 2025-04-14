package ucc.configuration;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ucc.exception.TooManyRequestException;

@Slf4j
@Component
public class CustomFeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.TOO_MANY_REQUESTS.value()) {
            log.error("429 Too many request from {}", methodKey);
            return new TooManyRequestException();
        }
        // Логируем другие ошибки
        log.warn("HTTP {} from {}", response.status(), methodKey);
        return defaultDecoder.decode(methodKey, response);
    }
}
