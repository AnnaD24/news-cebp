package rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueConsumer {
    private Channel channel;
    public QueueConsumer () throws IOException, TimeoutException {
        Connection connection = Config.connectionFactory.newConnection();
        this.channel = connection.createChannel();
        channel.queueDeclare(Config.QUEUE_NAME, false, false, false, null);
    }

    public void listen(DeliverCallback callback) {
        try {
            channel.basicConsume(Config.QUEUE_NAME, true, callback, consumerTag -> { });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}