package custom.camunda.identity;


import custom.dummydb.Persons;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.Session;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IdentityProviderFactory implements SessionFactory {
    private static final Logger log = LoggerFactory.getLogger(IdentityProviderFactory.class);
    private static ExternalIdentityProvider identityProvider;
    private final Persons persons;


    public IdentityProviderFactory(Persons persons) {
        log.info("Identity provide factory");
        this.persons = persons;

    }

    public ExternalIdentityProvider getIdentityProvider() {
        //maybe it should be in constructor, but i don't know the camunda lifecyccel
        if (identityProvider == null) {
            identityProvider = new ExternalIdentityProvider(persons);
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