package rabbit;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;

public class ReaderCallback implements DeliverCallback {

    @Override
    public void handle(String s, Delivery delivery) throws IOException {
        String message = new String(delivery.getBody(), "UTF-8");
        String newsId = message.split(":")[1];
        String type = message.split(":")[0];
        String domain = delivery.getEnvelope().getRoutingKey().split("\\.")[0];
        System.out.println("**********\nDomain: " + domain + '\n' + "newsId" + ": " + newsId + '\n');
    }
}
