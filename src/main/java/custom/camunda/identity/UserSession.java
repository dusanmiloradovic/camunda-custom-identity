package custom.camunda.identity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
In camunda, there is no easy way to restrict the groups to the user. This us a waoraround.
When the user logs in I remember its userid in session bean, add later when reading the groups , do it here
 */
public class UserSession {
    private static final Logger log = LoggerFactory.getLogger(UserSession.class);
    public UserSession(){
        log.info("Instantiate the User Session");

    }
    private String userId;
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
}
