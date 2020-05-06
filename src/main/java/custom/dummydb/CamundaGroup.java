package custom.dummydb;

import org.camunda.bpm.engine.identity.Group;

public class CamundaGroup implements Group {
    String id;
    String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        if ("camunda-admin".equals(getId())) {
            return "SYSTEM";
        } else {
            return "WORKFLOW";
        }
    }

    @Override
    public void setType(String s) {

    }
}
