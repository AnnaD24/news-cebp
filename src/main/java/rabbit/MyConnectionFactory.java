package rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class MyConnectionFactory extends ConnectionFactory {
    private Connection connection = null;
    public MyConnectionFactory (String uri, String user, String secret) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        super();
        super.setUri(uri);
        super.setUsername(user);
        super.setPassword(secret);
    }

    @Override
    public Connection newConnection() throws IOException, TimeoutException {
        if (this.connection == null)
            this.connection = super.newConnection();
        return this.connection;
    }
}
