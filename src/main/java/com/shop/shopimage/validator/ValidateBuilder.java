package com.shop.shopimage.validator;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ValidateBuilder {

    @Autowired
    private Map<String, ParamValidator> validateMap;

    // заполнение мапы с помощью рефлексии
//    @PostConstruct
//    public void ValidateBuilderStart() {
//
//        Reflections reflections = new Reflections();
//        Set<Class<? extends ParamValidator>> classes = reflections.getSubTypesOf(ParamValidator.class);
//
//        classes.forEach(clazz -> {
//            String modelClass = clazz.getGenericInterfaces()[0].getTypeName()
//                    .replaceAll("(\\D+<)", "")
//                    .replaceAll(">", "");
//            validateMap.put(modelClass, (ParamValidator) applicationContext.getBean(clazz.getName()));
//
//        });
//
//    }

    //Переписывает map с валидаторами. Вставляе значение ключей как тип дженерика реализованный для каждого валидатора
    // Например NewsValidator implements ParamValidator<News> - тут дженерик News, он будет ключем для значения NewsValidator
    @PostConstruct
    public void ValidateBuilderStart() {
        Map<String, ParamValidator> updateValidateMap = new HashMap<>();
        validateMap.entrySet().stream().forEach(entry -> {

            String modelClass = entry.getValue().getClass().getGenericInterfaces()[0].getTypeName()
                    .replaceAll("(\\D+<)", "")
                    .replaceAll(">", "");

            updateValidateMap.put(modelClass, entry.getValue());
            System.out.println(modelClass + " : " + entry.getValue() + "\n");
        });

        validateMap = updateValidateMap;
    }

    public void runValidateFor(Object record, BindingResult bindingResult) {
        validateMap.get(record.getClass().getTypeName()).validateParams(record, bindingResult);
    }
}
