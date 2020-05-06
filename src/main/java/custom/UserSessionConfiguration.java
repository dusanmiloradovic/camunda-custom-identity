package custom;

import custom.camunda.identity.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class UserSessionConfiguration {

    private static final Logger log = LoggerFactory.getLogger(UserSessionConfiguration.class);
    public UserSessionConfiguration(){
        log.info ("++++++++++++user session config");
    }
    @Bean
    @SessionScope
    public UserSession getUserSession(){
        return new UserSession();
    }
}
