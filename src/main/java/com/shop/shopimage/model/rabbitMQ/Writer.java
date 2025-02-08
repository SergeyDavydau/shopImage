package com.shop.shopimage.model.rabbitMQ;

import com.shop.shopimage.service.RabbitMQService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Data
public class Writer extends Thread{

    @Autowired
    RabbitMQService rabbitMQService;

    String nameThread;
    @Override
    public void run() {
        try {
            rabbitMQService.getMessagesDefault(nameThread + " /GET MESSAGES: ");


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
