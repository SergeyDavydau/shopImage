package com.shop.shopimage;

import com.shop.shopimage.model.News;
import com.shop.shopimage.model.User;
import com.shop.shopimage.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ShopImageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopImageApplication.class, args);
    }


}
