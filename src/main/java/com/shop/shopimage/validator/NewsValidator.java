package com.shop.shopimage.validator;

import com.shop.shopimage.model.News;
import com.shop.shopimage.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
public class NewsValidator implements ParamValidator<News>{


    @Override
    public Map<String, String> validateParams(News news, BindingResult bindingResult) {

        Map<String, String> errors = validateBindingResult(bindingResult);

        System.out.println("Validate castom scenaries for News");

        return errors;
    }


}
