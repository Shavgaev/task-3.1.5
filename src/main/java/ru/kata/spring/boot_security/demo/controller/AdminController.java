package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.Collections;

@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/ad")
    public String getUsers(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("admin", this.userService.findByEmail(principal.getName()));
        return "index";
    }

    @PostMapping("/ad")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "rolesId") Long rolesId) {
        if (rolesId == 2) {
            user.setRoles(Collections.singleton(roleService.getRole(1L)));
        } else if (rolesId == 1) {
            user.setRoles(Collections.singleton(roleService.getRole(2L)));
        } else {
            user.setRoles(roleService.getListRoles());
        }
        userService.saveUser(user);
        return "redirect:/ad";
    }
}
