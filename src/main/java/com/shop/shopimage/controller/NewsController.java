package com.shop.shopimage.controller;

import com.shop.shopimage.model.News;
import com.shop.shopimage.model.User;
import com.shop.shopimage.repo.NewsRepo;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
public class NewsController {

    @Autowired
    NewsRepo newsRepo;

    @GetMapping("/news")
    public String newsList(Model model) {
        model.addAttribute("allNews", newsRepo.findAll());
        return "news";
    }
    @GetMapping("/addNews")
    public String addNews(Model model){
        model.addAttribute("newsForm", new News());

        return "addNews";
    }


    @PostMapping("/addNews")
    public String addNews(@Valid @ModelAttribute("newsForm") News news,
                          BindingResult bindingResult,
                          @AuthenticationPrincipal User user,
                          Model model){
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
        news.setUser(user);
        newsRepo.save(news);

        return "redirect:/news";
    }

    @GetMapping("/removeNews/{id}")
    public String removeNews(@PathVariable("id")Long id){
        newsRepo.deleteById(id);
        return "redirect:/news";
    }


}
