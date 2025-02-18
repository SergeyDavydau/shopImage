package com.shop.shopimage.controller;

import com.shop.shopimage.model.News;
import com.shop.shopimage.model.User;
import com.shop.shopimage.repo.NewsRepo;
import com.shop.shopimage.repo.UserRepo;
import com.shop.shopimage.service.NewsService;
import com.shop.shopimage.validator.ValidateBuilder;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;


@Controller
public class NewsController {

    @Autowired
    NewsRepo newsRepo;
    @Autowired
    NewsService newsService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    ValidateBuilder validateBuilder;

    @GetMapping("/news")
    public String newsList(Model model) {
        model.addAttribute("allNews", newsRepo.findAll());
        return "news";
    }

    @GetMapping("/addNews")
    public String addNews(Model model) {
        model.addAttribute("newsForm", new News());

        return "addNews";
    }


    @PostMapping("/addNews")
    public String addNews(@Valid @ModelAttribute("newsForm") News news,
                          BindingResult bindingResult,
                          @AuthenticationPrincipal User user,
                          Model model) {

        validateBuilder.runValidateFor(news, bindingResult);
        newsService.saveNews(news, user);

        return "redirect:/news";
    }


    @GetMapping("/removeNews/{id}")
    public String removeNews(@PathVariable("id") Long id) {
        newsRepo.deleteById(id);
        return "redirect:/news";
    }


    @PostMapping("/editNews")
    public String editNews(@Valid @ModelAttribute("newsForm") News news,
                           BindingResult bindingResult,
                           @AuthenticationPrincipal User user,
                           Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            model.addAttribute("errors", errors);
            return "addNews";
        }
        newsService.saveNews(news, user);

        return "redirect:/news";
    }


}
