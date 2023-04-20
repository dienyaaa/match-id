package com.match.calculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Рассчитайте Ваш размер");
        return "home";
    }

    @GetMapping("/tables")
    public String tables(Model model) {
        model.addAttribute("title", "Таблица размеров одежды");
        return "tables";
    }

    @GetMapping("/measures")
    public String measures(Model model) {
        model.addAttribute("title", "Как определить свой размер одежды");
        return "measures";
    }
}
