package com.project.animalface_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {


    @GetMapping("/search")
    public String showSearchPage() {
        return "search";
    }
}