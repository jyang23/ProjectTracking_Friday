package com.jy.template.Repository;

import com.jy.template.Beans.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProjectRepository extends CrudRepository<Project, Long>
{
    Project findById(long id);
    Project findByProjectname(String name);
}
