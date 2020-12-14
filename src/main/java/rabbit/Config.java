package rabbit;

import com.rabbitmq.client.ConnectionFactory;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Config {
    public static String EXCHANGE_NAME = "news_exchange";
    public static String URI = "amqps://b-ef7ef197-6a25-4a45-92f1-5fc10b17481a.mq.us-east-2.amazonaws.com:5671";
    public static String USER = "rabbit";
    public static String secret = "123P@ssw0rd123";
    public static ConnectionFactory connectionFactory;

    static {
        try {
            connectionFactory = new MyConnectionFactory(URI, USER, secret);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
