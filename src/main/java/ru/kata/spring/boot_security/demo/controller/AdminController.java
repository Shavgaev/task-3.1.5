package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.Collections;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model, Principal principal) {
        model.addAttribute("admin", this.userService.findByEmail(principal.getName()));
        model.addAttribute("users", this.userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("user") User user) {
        user.setRoles(Collections.singleton(roleService.getRole(2L)));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.delete(user.getId());
        return "redirect:/admin";
    }
}