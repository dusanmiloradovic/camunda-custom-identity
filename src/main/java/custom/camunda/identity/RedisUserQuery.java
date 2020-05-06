package custom.camunda.identity;

import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.UserQueryImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;

import java.util.List;


public class RedisUserQuery extends UserQueryImpl {
    public RedisUserQuery(CommandExecutor commandExecutorTxRequired) {
        super(commandExecutorTxRequired);
    }

    public RedisUserQuery() {
        super();
    }

    @Override
    public long executeCount(CommandContext commandContext) {
       return  getExternalIdentityProvider(commandContext).findUserByQueryCriteria(this).size();
    }

    @Override
    public List<User> executeList(CommandContext commandContext, Page page) {
        ExternalIdentityProvider identityProvider = getExternalIdentityProvider(commandContext);
        return identityProvider.findUserByQueryCriteria(this);
    }

    private ExternalIdentityProvider getExternalIdentityProvider(CommandContext commandContext) {
        return (ExternalIdentityProvider) commandContext.getReadOnlyIdentityProvider();
    }
}
