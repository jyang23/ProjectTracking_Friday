package com.jy.template.Repository;

import com.jy.template.Beans.Issue;
import com.jy.template.Beans.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IssueRepository extends CrudRepository<Issue, Long>
{
    ArrayList<Issue>findByProject(Project p);
}
