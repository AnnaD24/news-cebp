package rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueuePublisher {
    private Channel channel;
    public QueuePublisher () throws IOException, TimeoutException {
        Connection connection = Config.connectionFactory.newConnection();
        this.channel = connection.createChannel();
        channel.queueDeclare(Config.QUEUE_NAME, false, false, false, null);
    }

    public void publish (String message) {
        try {
            channel.basicPublish("", Config.QUEUE_NAME, null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
