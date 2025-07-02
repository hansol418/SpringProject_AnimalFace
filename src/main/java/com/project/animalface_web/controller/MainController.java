package com.project.animalface_web.controller;

import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main(Model model) {
        addLoginStatusToModel(model);
        return "main";
    }

    @GetMapping("/main2")
    public String main2(Model model) {
        addLoginStatusToModel(model);
        return "main";
    }


    private void addLoginStatusToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = (authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof String && "anonymousUser".equals(authentication.getPrincipal())));
        model.addAttribute("isLoggedIn", isLoggedIn);
    }
}