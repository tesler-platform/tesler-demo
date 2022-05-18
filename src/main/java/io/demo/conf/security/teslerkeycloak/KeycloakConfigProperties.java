package io.demo.conf.security.teslerkeycloak;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "app")
public class KeycloakConfigProperties {

	private Map<String, Object> keycloak;

}
