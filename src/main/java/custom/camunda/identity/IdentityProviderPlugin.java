package custom.camunda.identity;

import custom.redis.Persons;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class IdentityProviderPlugin implements ProcessEnginePlugin {

    private static final Logger log = LoggerFactory.getLogger(IdentityProviderPlugin.class);

    private Persons persons;

   private final ObjectFactory<HttpSession> httpSessionFactory;
    @Autowired
    public IdentityProviderPlugin(Persons persons,ObjectFactory<HttpSession> httpSessionFactory) {
        this.persons = persons;
        this.httpSessionFactory=httpSessionFactory;
    }


    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        log.info("Identity Provider plugin initialzed");

    }

    @Override
    public void postProcessEngineBuild(ProcessEngine processEngine) {

    }


    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        log.info("Identity Provider plugin pre-init");
        IdentityProviderFactory identityProviderFactory = new IdentityProviderFactory(persons,httpSessionFactory);
        processEngineConfiguration.setIdentityProviderSessionFactory(identityProviderFactory);

    }
}
