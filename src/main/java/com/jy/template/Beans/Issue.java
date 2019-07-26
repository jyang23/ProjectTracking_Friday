package com.jy.template.Beans;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String issuename;
    private String description;
    private String priority;
    private String status;
    private String startdate;
    private String leaddeveloper;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;

    public Issue(){

    }

    public Issue(String issuename, String description, String priority,
                 String status, String startdate, String leaddeveloper)
    {
        this.issuename = issuename;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.startdate = startdate;
        this.leaddeveloper = leaddeveloper;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIssuename() {
        return issuename;
    }

    public void setIssuename(String issuename) {
        this.issuename = issuename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getLeaddeveloper() {
        return leaddeveloper;
    }

    public void setLeaddeveloper(String leaddeveloper) {
        this.leaddeveloper = leaddeveloper;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
