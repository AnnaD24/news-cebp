package rabbit;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import db.NewsDb;
import utils.New;

import java.io.IOException;
import java.util.UUID;

public class NewsReadCallback implements DeliverCallback {
    private NewsDb newsDb;
    private UUID editorId;
    public NewsReadCallback(NewsDb newsDb, UUID editorId) {
        this.newsDb = newsDb;
        this.editorId = editorId;
    }

    @Override
    public void handle(String s, Delivery delivery) throws IOException {
        String message = new String (delivery.getBody(), "UTF-8");
        UUID newsId = UUID.fromString(message);
        New n = newsDb.findNews(editorId, newsId);
        if (n != null)
            n.incrementReadCount();
    }
}
