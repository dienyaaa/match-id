package com.match.calculator.controllers;

import com.match.calculator.models.User;
import com.match.calculator.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String userMain(Model model) {
        return "register-user";
    }

    @GetMapping(path="/register")
    public String registerUser(Model model) {
        return "register-user";
    }

    @PostMapping(path="/register") // Map ONLY POST Requests
    public String addNewUser (@RequestParam String username, @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setUsername(username);
        n.setEmail(email);
        userRepository.save(n);
        return "redirect:/user/" + n.getId();
    }

    @GetMapping(path="/{id}")
    public String infoUser(@PathVariable(value = "id") Long id, Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/register";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-info";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}