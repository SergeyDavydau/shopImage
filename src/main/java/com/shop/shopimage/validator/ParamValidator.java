package com.shop.shopimage.validator;

import com.shop.shopimage.model.News;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public interface ParamValidator<T> {

    Map<String, String> validateParams(T clas, BindingResult bindingResult);

    default Map<String, String> validateBindingResult(BindingResult bindingResult){

        Map<String, String> errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        }

        return errors;
    }

}
