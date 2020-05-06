package custom.camunda.identity;


import custom.redis.Persons;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.Session;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;

import javax.servlet.http.HttpSession;


public class IdentityProviderFactory implements SessionFactory {
    private static final Logger log = LoggerFactory.getLogger(IdentityProviderFactory.class);
    private static ExternalIdentityProvider identityProvider;
    private final Persons persons;
    private final ObjectFactory<HttpSession> httpSessionFactory;

    public IdentityProviderFactory(Persons persons, ObjectFactory<HttpSession> httpSessionFactory) {
        log.info("Identity provide factory");
        this.persons = persons;
        this.httpSessionFactory=httpSessionFactory;
    }

    public ExternalIdentityProvider getIdentityProvider() {
        //maybe it should be in constructor, but i don't know the camunda lifecyccel
        if (identityProvider == null) {
            identityProvider = new ExternalIdentityProvider(persons,httpSessionFactory);
        }
        return identityProvider;
    }

    @Override
    public Class<?> getSessionType() {
        return ReadOnlyIdentityProvider.class;
    }

    @Override
    public Session openSession() {
        //return new ExternalIdentityProvider(persons);
        //log.info("GEtting the session, should come after the session bean is already created");
        //return session;
        return getIdentityProvider();
        // return externalIdentityProvider;
    }
}