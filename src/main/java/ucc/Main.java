package ucc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.riot.api")
@EnableConfigurationProperties
@EnableCaching
@MapperScan("ucc.persistence.repository")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
