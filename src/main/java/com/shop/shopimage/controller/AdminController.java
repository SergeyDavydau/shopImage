package com.shop.shopimage.controller;

import com.shop.shopimage.model.User;
import com.shop.shopimage.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model, HttpSession session) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("\n USER-"+ principal.getUsername()
                + " visit admin panel | session - " +session.getId() + "  create - "+ new Timestamp(session.getCreationTime()));
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }
}