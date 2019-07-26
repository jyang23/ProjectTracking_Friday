package com.jy.template;

import com.jy.template.Configurations.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/")
    public String index(Model model)
    {
        if(userService.getUser() != null)
        {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "index";
    }
    //------------------------------------------------------------------------------------------------------------------
}
