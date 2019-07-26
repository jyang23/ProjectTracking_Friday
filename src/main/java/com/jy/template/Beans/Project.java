package com.jy.template.Beans;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long issueid;
    private String projectname;

    public Project() {
    }

    public Project(String projectname) {
        this.projectname = projectname;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIssueid() {
        return issueid;
    }

    public void setIssueid(long issueid) {
        this.issueid = issueid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
}

