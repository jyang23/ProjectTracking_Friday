package com.jy.template;

import com.jy.template.Beans.User;
import com.jy.template.Configurations.CustomUserDetails;
import com.jy.template.Configurations.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class LoginController {

    @Autowired
    UserService userService;
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
    //------------------------------------------------------------------------------------------------------------------
//    @RequestMapping("/admin")
//    public String admin()
//    {
//        return "admin";
//    }
    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/register")
    public String showRegistrationPage(Model model)
    {
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          BindingResult result,
                                          Model model,
                                          @RequestParam("password") String pw)
    {
        System.out.println("pw: " + pw);
        if(result.hasErrors())
        {
            return "register";
        }
        else
        {
            user.setPassword(pw);
            userService.saveUser(user);
            model.addAttribute("message", "New User Account Created");
        }
        return "login";
    }
    //------------------------------------------------------------------------------------------------------------------
}
