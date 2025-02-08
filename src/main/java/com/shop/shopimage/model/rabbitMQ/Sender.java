package com.shop.shopimage.model.rabbitMQ;

import com.shop.shopimage.service.RabbitMQService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Data
public class Sender extends Thread{

    @Autowired
    RabbitMQService rabbitMQService;

    public static int countSendMessages;



    @Override
    public void run() {
        try {
            setMethodsd();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setMethodsd() throws IOException, TimeoutException, InterruptedException {
        rabbitMQService.sendMessagesDefault("1 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("2 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("3 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("4 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("5 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("6 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("7 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("8 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("9 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("10 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("11 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("12 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("13 MESSAGES FOR RABBIT");
        rabbitMQService.sendMessagesDefault("14 MESSAGES FOR RABBIT");
    }
}
