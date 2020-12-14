package demo;

import rabbit.TopicConsumer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class Client1 {
    public static void main(String[] args) {
        try {
            TopicConsumer consumer1 = new TopicConsumer();
//            consumer1.addFilter("auto", LocalDateTime.of(2020,12,10,12,0),7);
            consumer1.addFilter("auto", LocalDateTime.now(),2);
            consumer1.run();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
