package rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.DateFormatter;
import utils.New;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeoutException;

public class TopicPublisher {
    private Channel channel;
    private String queueName;
    public TopicPublisher() throws IOException, TimeoutException {
        Connection connection = Config.connectionFactory.newConnection();
        this.channel = connection.createChannel();
        channel.exchangeDeclare(Config.EXCHANGE_NAME, "topic");
    }

    public void publishEvent (New news, String eventType) {
        String routingKey = getRoutingKey(news);
        System.out.println("Routing key for news " + news.getNewsId() + ": " + routingKey);
        String message = eventType + ":" + news.getNewsId();

        try {
            channel.basicPublish(Config.EXCHANGE_NAME, routingKey, null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRoutingKey (New news) {
        String routingKey = "";
        routingKey += news.getDomain() + "." + DateFormatter.format(news.getCreatedDate());
        return routingKey;
    }
}
