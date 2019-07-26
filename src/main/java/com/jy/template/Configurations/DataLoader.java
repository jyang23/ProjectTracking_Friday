package com.jy.template.Configurations;

import com.jy.template.Beans.Issue;
import com.jy.template.Beans.Project;
import com.jy.template.Beans.Role;
import com.jy.template.Beans.User;
import com.jy.template.Repository.IssueRepository;
import com.jy.template.Repository.ProjectRepository;
import com.jy.template.Repository.RoleRepository;
import com.jy.template.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    ProjectRepository projectRepository;

    //This is moved to the user class to keep it consistent
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Override
    public void run(String...strings) throws Exception{
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        //Constructors
        User user = new User("user@name.com", "password", "User", "Name", true, "user");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("system@admin.com", "password", "System", "Admin", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        //==============================================================================================================
        Project project = new Project("Pentagon");
        projectRepository.save(project);

        Issue issue = new Issue("Production Changes","Website Updates","High","Incomplete", "7/26/19","Bart Kwon");
        issue.setUser(user);
        issue.setProject(project);
        issueRepository.save(issue);
        System.out.println(issue.getId());

        project.setIssueid(issue.getId());
        projectRepository.save(project);

        //==============================================================================================================
        project = new Project("Amazon");
        projectRepository.save(project);

        issue = new Issue("Order Changes","Add SD Cards","low","Incomplete", "7/26/19","Michelle Kwon");
        issue.setUser(user);
        issue.setProject(project);
        issueRepository.save(issue);

        project.setIssueid(issue.getId());
        projectRepository.save(project);

        //==============================================================================================================
        project = new Project("Capital One");
        projectRepository.save(project);

        issue = new Issue("Bills","Payment","medium","Incomplete", "7/26/19","Andrew Kwon");
        issue.setUser(user);
        issue.setProject(project);
        issueRepository.save(issue);

        project.setIssueid(issue.getId());
        projectRepository.save(project);
        //==============================================================================================================
    }
}
