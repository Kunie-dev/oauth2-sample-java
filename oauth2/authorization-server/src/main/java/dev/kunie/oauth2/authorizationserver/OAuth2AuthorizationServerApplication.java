package dev.kunie.oauth2.authorizationserver;

import dev.kunie.oauth2.OAuth2Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@Import({OAuth2Application.class})
public class OAuth2AuthorizationServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2AuthorizationServerApplication.class, args);
    }
}
