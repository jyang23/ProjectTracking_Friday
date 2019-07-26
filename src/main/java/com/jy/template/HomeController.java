package com.jy.template;

import com.jy.template.Beans.Issue;
import com.jy.template.Beans.Project;
import com.jy.template.Configurations.UserService;
import com.jy.template.Repository.IssueRepository;
import com.jy.template.Repository.ProjectRepository;
import com.jy.template.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("projects",projectRepository.findAll());
        model.addAttribute("issues", issueRepository.findAll());

        if(userService.getUser() != null)
        {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "index";
    }
    //-Issue------------------------------------------------------------------------------------------------------------
    @GetMapping("/add")
    public String issueForm(Model model)
    {
        model.addAttribute("issue",new Issue());
        model.addAttribute("projects",projectRepository.findAll());
        return "issueform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Issue issue, BindingResult result, @RequestParam("projectname") String projectname)
    {
        if(result.hasErrors())
        {
            return "issueform";
        }
        System.out.println(projectname);
        Project p = projectRepository.findByProjectname(projectname);
        issue.setUser(userService.getUser());
        issue.setProject(p);
        issueRepository.save(issue);
        return "redirect:/";
    }
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/show")
    public String show(Model model)
    {
        model.addAttribute("issues", issueRepository.findAll());
        model.addAttribute("projects", projectRepository.findAll());

        if(userService.getUser() != null)
        {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "show";
    }
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("details/{id}")
    public String showEmployee(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("issue", issueRepository.findById(id).get());
        return "details";
    }

    @RequestMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("issue", issueRepository.findById(id).get());
        return "issueform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id)
    {
        issueRepository.deleteById(id);
        return "redirect:/";
    }
    //-Project------------------------------------------------------------------------------------------------------------
    @GetMapping("/addproject")
    public String projectForm(Model model)
    {
        model.addAttribute("project",new Project());
        return "projectform";
    }

    @PostMapping("/processproject")
    public String processForm(@Valid Project project, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "projectform";
        }
        projectRepository.save(project);
        return "redirect:/";
    }
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/loadfilter")
    public String loadFormPage() {
        return "show";
    }

    @RequestMapping("/processfilter/{value}")
    public String processForm(@PathVariable("value") long id, Model model){
        Project p = projectRepository.findById(id);
        model.addAttribute("issues", issueRepository.findByProject(p));
        return "show";
    }
}
