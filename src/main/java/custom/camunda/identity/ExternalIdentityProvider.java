package custom.camunda.identity;


import custom.redis.CamundaGroup;
import custom.redis.Person;
import custom.redis.Persons;
import org.camunda.bpm.engine.identity.*;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;
import org.camunda.bpm.engine.impl.persistence.GenericManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExternalIdentityProvider implements ReadOnlyIdentityProvider {

    final Persons persons;
    private static final Logger log = LoggerFactory.getLogger(ExternalIdentityProvider.class);
    private final ObjectFactory<HttpSession> httpSessionFactory;

    public ExternalIdentityProvider(Persons persons, ObjectFactory<HttpSession> httpSessionFactory){
        this.persons=persons;
        this.httpSessionFactory=httpSessionFactory;
    };

    @Autowired
    UserSession userSession;

    public User findUserById(String s) {

        Person person = persons.get(s);
        return person;
    }

    public UserQuery createUserQuery() {
        return new RedisUserQuery(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
    }

    public UserQuery createUserQuery(CommandContext commandContext) {
        return new RedisUserQuery();
    }

    public NativeUserQuery createNativeUserQuery() {
        return null;
    }

    public boolean checkPassword(String s, String s1) {
        return true;//any password, for development. For production, think about it
    }

    public Group findGroupById(String group) {
        CamundaGroup cg=new CamundaGroup();
        cg.setId(group);
        cg.setName(group);
        return cg;
    }

    public GroupQuery createGroupQuery() {
        return new RedisGroupQuery(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
    }

    public GroupQuery createGroupQuery(CommandContext commandContext) {
        final HelperCamundaSession session = commandContext.getSession(HelperCamundaSession.class);
        return new RedisGroupQuery(session);
    }

    public Tenant findTenantById(String s) {
        return null;
    }

    public TenantQuery createTenantQuery() {
        return new DummyTenantQuery(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
    }

    public TenantQuery createTenantQuery(CommandContext commandContext) {
        return new DummyTenantQuery();
    }

    public void flush() {

    }

    public void close() {

    }


    private boolean inGroup(String userId, String group) {
        if (userId.equals("7061") && "camunda-admin".equals(group)) {
            return true;//TODO this condition just for development
        }
        final List<String> groups = persons.getGroups(userId);
        return groups.contains(group);
    }

    public List<User> findUserByQueryCriteria(RedisUserQuery query) {
        final List<User> users = new ArrayList<>(persons.getAllPersons());
        if (query.getId() != null)
            users.removeIf(user -> !user.getId().equals(query.getId()));
        if (query.getFirstName() != null)
            users.removeIf(user -> !user.getFirstName().equals(query.getFirstName()));
        if (query.getLastName() != null)
            users.removeIf(user -> !user.getLastName().equals(query.getLastName()));
        if (query.getEmail() != null)
            users.removeIf(user -> !user.getEmail().equals(query.getEmail()));
        if (query.getGroupId() != null)
            users.removeIf(user -> !inGroup(user.getId(), query.getGroupId()));
        return users;
    }

    public List<Group> findGroupByQueryCriteria(RedisGroupQuery query) {
//        final ProcessEngineConfigurationImpl processEngineConfiguration = Context.getCommandContext().getProcessEngineConfiguration();
//        Map<Class<?>, SessionFactory> sessionFactories = processEngineConfiguration.getSessionFactories();
//        if (sessionFactories.get(HelperCamundaSession.class)==null){
//            sessionFactories.put(HelperCamundaSession.class, new GenericManagerFactory(HelperCamundaSession.class));
//        }
//
//        processEngineConfiguration.setSessionFactories(sessionFactories);
      //  final HelperCamundaSession session = Context.getCommandContext().getSession(HelperCamundaSession.class);
        final HttpSession session = httpSessionFactory.getObject();
        if (query.getUserId()!=null){
          //  session.setUserId(query.getUserId());
            if (userSession!=null){
                userSession.setUserId(query.getUserId());
            }
            return getGroupsForUser(query.getUserId());
        }
        final List<String> allGroups = persons.getAllGroups();
        List<Group> ret = getGroups(allGroups);

        if (query.getId()!=null){
       //     log.info ("AND THE USER SESSION IS "+userSession.getUserId());
            ret.removeIf(group->!group.getId().equals(query.getId()));
        }


        if (userSession!=null && userSession.getUserId()!=null){
            return getGroupsForUser(userSession.getUserId());
        }
        //TODO this is just for development, thisk of some other way to store the camunda admin group
       // log.info ("AND THE USER SESSION IS "+session.getUserId());
        return ret;
    }

    private List<Group> getGroups(List<String> groups) {
        List<Group> ret = new ArrayList<>();
        for (String g : groups) {
            CamundaGroup cg = new CamundaGroup();
            cg.setId(g);
            ;
            cg.setName(g);
            ret.add(cg);
        }
//        CamundaGroup cg = new CamundaGroup();
//        cg.setId("camunda-admin");
//        cg.setName("Admin Group");
//        ret.add(cg);
        return ret;
    }

    private List<Group> getGroupsForUser(String userId) {
        final List<String> groups = persons.getGroups(userId);
        return getGroups(groups);
    }
}
