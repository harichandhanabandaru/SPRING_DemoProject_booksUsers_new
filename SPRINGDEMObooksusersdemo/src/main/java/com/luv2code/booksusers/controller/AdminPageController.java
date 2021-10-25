package com.luv2code.booksusers.controller;

import com.luv2code.booksusers.dao.UsersRepository;
import com.luv2code.booksusers.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    private UsersRepository userRepo;

    @GetMapping("/adminpage")
//    public String adminpage(Model theModel)
    public String adminpage()

    {
        return "adminpage" ;
    }

    @GetMapping("/viewUsers")
    public String viewUsers(Model model) {
        List<Users> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

}

