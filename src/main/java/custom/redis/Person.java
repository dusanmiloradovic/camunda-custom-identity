package custom.redis;

import org.camunda.bpm.engine.identity.User;

public class Person  implements User {
    String personid;
    String firstname;
    String lastname;

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personid='" + personid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", displayname='" + displayname + '\'' +
                ", designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    String displayname;
    String designation;
    String department;
    String location;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone;
    String jobdesc;
    String division;

    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String getId() {
        return personid;
    }

    @Override
    public void setId(String s) {
        setPersonid(s);
    }

    @Override
    public String getFirstName() {
        return getFirstname();
    }

    @Override
    public void setFirstName(String s) {
        setFirstname(s);
    }

    @Override
    public void setLastName(String s) {
        setLastname(s);
    }

    @Override
    public String getLastName() {
        return getLastname();
    }

    @Override
    public void setEmail(String s) {

    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPassword() {
        return "no password";
    }

    @Override
    public void setPassword(String s) {

    }
}
