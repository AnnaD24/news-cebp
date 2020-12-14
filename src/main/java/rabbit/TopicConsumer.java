package rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import utils.DateFormatter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class TopicConsumer {
    private Channel channel;
    private String queueName;
    public  TopicConsumer() throws IOException, TimeoutException {
        Connection connection = Config.connectionFactory.newConnection();
        this.channel = connection.createChannel();
        channel.exchangeDeclare(Config.EXCHANGE_NAME, "topic");
        this.queueName = channel.queueDeclare().getQueue();
     }

    public void addFilter(String domain, LocalDateTime date) {
        String routingKey = createRoutingKey(domain, date);

        System.out.println("Adding routing key for filter: " + routingKey);
        try {
            this.channel.queueBind(this.queueName, Config.EXCHANGE_NAME, routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String createRoutingKey(String domain, LocalDateTime date) {
        String routingKey ="";

        if (domain == null)
            routingKey += "*.";
        else
            routingKey += (domain + ".");

        if(date == null)
            routingKey += "*";
        else
            routingKey += DateFormatter.format(date);
        return routingKey;
    }

    public void run () {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        try {
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Astept dupa mesaj in clasa");
    }
}
