package custom.camunda.identity;

import org.camunda.bpm.engine.impl.interceptor.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelperCamundaSession implements Session {
    private static final Logger log = LoggerFactory.getLogger(HelperCamundaSession.class);
    String userId;

    public HelperCamundaSession(){
        log.info("*****************Creating camunda session");
    }
    public void setUserId(String userOd){
        this.userId=userId;
    }

    public String getUserId(){
        return userId;
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() {

    }
}
