package custom.camunda.identity;

import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.GroupQueryImpl;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;

import java.util.List;

public class RedisGroupQuery extends GroupQueryImpl {
    private  HelperCamundaSession sess;

    public RedisGroupQuery(CommandExecutor commandExecutorTxRequired) {
        super(commandExecutorTxRequired);
    }

    public RedisGroupQuery(){
        super();
    }

    public RedisGroupQuery(HelperCamundaSession sess){
        this.sess=sess;
    }
    @Override
    public long executeCount(CommandContext commandContext) {

        final List<Group> groupByQueryCriteria = getExternalIdentityProvider(commandContext).findGroupByQueryCriteria(this);
        return groupByQueryCriteria.size();
    }

    @Override
    public List<Group> executeList(CommandContext commandContext, Page page) {

        return getExternalIdentityProvider(commandContext).findGroupByQueryCriteria(this);
    }

    private ExternalIdentityProvider getExternalIdentityProvider(CommandContext commandContext) {
        return (ExternalIdentityProvider) commandContext.getReadOnlyIdentityProvider();
    }
}
