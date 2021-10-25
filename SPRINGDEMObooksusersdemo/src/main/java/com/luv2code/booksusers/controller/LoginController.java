package com.luv2code.booksusers.controller;

import com.luv2code.booksusers.dao.UsersRepository;
import com.luv2code.booksusers.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/start")
    public String sayHello(Model theModel)
    {
        return "homepage";
    }

    @GetMapping("/login")
    public String fancyLogin(Model theModel)
    {
        return "fancy-login";
    }




    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());

        return "signup_form";
    }

    @Autowired
    private UsersRepository userRepo;

    @PostMapping("/process_register")
    public String processRegister(Users user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
       user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_sucess";
    }

    @GetMapping("/viewUsersPage")
    public String viewUserPage(Model model) {

        return "userpage";
    }
}
