package dev.kunie.oauth2.client;

import dev.kunie.oauth2.OAuth2Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@Import({OAuth2Application.class})
public class OAuth2ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2ClientApplication.class, args);
    }
}
