package com.shop.shopimage.service;

import com.rabbitmq.client.*;
import com.shop.shopimage.model.News;
import com.shop.shopimage.model.User;
import com.shop.shopimage.model.rabbitMQ.Sender;
import com.shop.shopimage.model.rabbitMQ.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RabbitMQService{


    @Autowired
    private static ApplicationContext applicationContext;

    private static String queueName = "hello";
    private static String HOST_NAME = "localhost";

    //Отправка прямиком в очередь (через default обменник)
    public static void sendMessagesDefault(String messages) throws IOException, TimeoutException, InterruptedException {
        ++Sender.countSendMessages;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, messages.getBytes());

            System.out.println("Messages(" + Sender.countSendMessages + "): - send");
            Thread.sleep(1000);
        }
    }

    public static void getMessagesDefault(String threadName) throws IOException, TimeoutException, InterruptedException {
        Thread.sleep(2000);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
          String messages = new String(delivery.getBody(), "UTF-8");
          System.out.println(threadName + messages);

        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

    }




    public static void sendMessagesFanout(String messages) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("dev-fanout", "fanout");

            channel.queueDeclare("fanoutQueue", false, false, false, null);
            channel.queueDeclare("fanoutQueueB", false, false, false, null);
            channel.queueBind("fanoutQueue", "dev-fanout", "");
            channel.queueBind("fanoutQueueB", "dev-fanout", "");

            channel.basicPublish("dev-fanout", "", null, messages.getBytes("UTF-8"));

            System.out.println("Messages(" + messages + "): - send");
        }
    }
    public static void getMessagesFanout() throws IOException, TimeoutException, InterruptedException {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("dev-fanout", "fanout");

        String queueN= channel.queueDeclare().getQueue();


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

        };
        channel.basicConsume("fanoutQueue", true, deliverCallback, consumerTag -> { });

    }


    public static void sendMessagesTopic(String messages) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("dev-topic", "topic");

            channel.queueDeclare("topicQueueA", false, false, false, null);
            channel.queueDeclare("topicQueueB", false, false, false, null);

            channel.queueBind("topicQueueA", "dev-fanout", "red.green");
            channel.queueBind("fanoutQueueB", "dev-fanout", "orange");

            channel.basicPublish("dev-fanout", "green", null, messages.getBytes("UTF-8"));

            System.out.println("Messages(" + messages + "): - send");
        }
    }
    public static void getMessagesTopic() throws IOException, TimeoutException, InterruptedException {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("dev-topic", "topic");
        channel.queueBind("topicQueueA", "dev-fanout", "green");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

        };
        channel.basicConsume("topicQueueA", true, deliverCallback, consumerTag -> { });

    }

    public static void main(String[] args) {
        //        sendMessagesTopic("Message green");
//        getMessagesTopic();






//    Sender sender = new Sender();
//
//    sender.start();
//
//        Writer writera = new Writer();
//        writera.setNameThread("WHRITER_A");
//        writera.start();
//
//        Writer writerb = new Writer();
//        writerb.setNameThread("WHRITER_B");
//        writerb.start();


//        sendMessagesFanout("Messages fanout public!!!");
//        Thread.sleep(1000);
//        getMessagesFanout();
    }

}
