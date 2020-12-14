package demo;

import rabbit.TopicConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Client1 {
    public static void main(String[] args) {
        try {
            TopicConsumer consumer1 = new TopicConsumer();
            consumer1.addFilter("auto", null);
            consumer1.run();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }


    }
}
