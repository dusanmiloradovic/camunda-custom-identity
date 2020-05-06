package custom.redis;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class Persons {

    class DummyPerson extends Person{
	DummyPerson(String userId){
	    setFirstName(userId);
	    setLastName(userId);
	    setId(userId);
	}
    }
    
    private List<String> users=Arrays.asList(new String[]{"userA","userB"});
    private List<String> groups=Arrays.asList(new String[]{"groupA","groupB","groupC","groupD"});
    Map<String,List<String>> userGroups = new HashMap<>();
    Map<String,List<String>> groupUsers = new HashMap<>();
    
    public Persons(){
	userGroups.put("userA",Arrays.asList(new String[]{"groupA","groupB"}));
	userGroups.put("userB",Arrays.asList(new String[]{"groupC","groupD"}));
	groupUsers.put("groupA",Arrays.asList(new String[]{"userA"}));
	groupUsers.put("groupB",Arrays.asList(new String[]{"userA"}));
	groupUsers.put("groupC",Arrays.asList(new String[]{"userB"}));
	groupUsers.put("groupD",Arrays.asList(new String[]{"userB"}));
    }

    public Person get(String userId){
	return new DummyPerson(userId);
    }
    
    public List<String> getUsers(String group) {
	return groupUsers.get(group);
    }

    public List<String> getGroups(String userid) {
	return userGroups.get(userid);
    }

    public List<Person> getAllPersons() {
	return users.stream().map(u->new DummyPerson(u)).collect(Collectors.toList());
    }

    public List<String> getAllGroups(){
	return groups;
    }
}
