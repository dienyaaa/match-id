package com.match.calculator.controllers;

import com.match.calculator.models.Gender;
import com.match.calculator.models.User;
import com.match.calculator.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/login")
    public String userLogin(Model model) {
        return "login-user";
    }

    @PostMapping(path="/login")
    public String userLogin(@RequestParam String id) {
        if (id.equals("")) {
            Long firstId = userRepository.findAll().iterator().next().getId();
            return "redirect:/user/" + firstId;
        }
        long matchId = Long.parseLong(id);
        if (!userRepository.existsById(matchId)) {
            return "redirect:/user/register";
        }
        return "redirect:/user/" + matchId;
    }


    @GetMapping(path="/register")
    public String registerUser(Model model) {
        return "register-user";
    }

    @PostMapping(path="/register")
    public String addNewUser (@RequestParam String username, @RequestParam String email,
                              @RequestParam double height, @RequestParam double chest,
                              @RequestParam double waist, @RequestParam double hips,
                              @RequestParam double footLength, @RequestParam String gender) {
        User n = new User();
        n.setUsername(username);
        n.setEmail(email);
        n.setHeight(height);
        n.setChest(chest);
        n.setWaist(waist);
        n.setHips(hips);
        n.setFootLength(footLength);
        n.setGender(Gender.valueOf(gender));
        n.setShoeSize();
        n.setPantsSize();
        n.setShirtSize();
        userRepository.save(n);
        return "redirect:/user/" + n.getId();
    }

    @GetMapping(path="/{id}/edit")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/user/register";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "edit-user";
    }

    @PostMapping(path="/{id}/edit")
    public String editUser (@PathVariable(value = "id") Long id,
                            @RequestParam String username, @RequestParam String email,
                            @RequestParam double height, @RequestParam double chest,
                            @RequestParam double waist, @RequestParam double hips,
                            @RequestParam double footLength, @RequestParam String gender) throws SQLException, ClassNotFoundException {
        User n = userRepository.findById(id).orElseThrow();
        n.setUsername(username);
        n.setEmail(email);
        n.setHeight(height);
        n.setChest(chest);
        n.setWaist(waist);
        n.setHips(hips);
        n.setFootLength(footLength);
        n.setGender(Gender.valueOf(gender));
        n.setShoeSize();
        n.setPantsSize();
        n.setShirtSize();
        userRepository.save(n);
        return "redirect:/user/" + n.getId();
    }

    @GetMapping(path="/{id}")
    public String infoUser(@PathVariable(value = "id") Long id, Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/user/register";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "info-user";
    }

    @PostMapping(path="/{id}/delete")
    public String deleteUser(@PathVariable(value = "id") Long id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}