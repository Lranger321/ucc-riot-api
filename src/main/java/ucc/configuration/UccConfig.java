package ucc.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ucc.model.UccMember;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "ucc")
@Data
public class UccConfig {

    private List<UccMember> members;
}
