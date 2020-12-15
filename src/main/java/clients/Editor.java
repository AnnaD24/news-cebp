package clients;

import db.NewsDb;
import rabbit.NewsReadCallback;
import rabbit.QueueConsumer;
import rabbit.TopicPublisher;
import utils.New;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Editor {
    private UUID editorId;
    private static NewsDb newsDb = new NewsDb();
    private TopicPublisher topicPublisher;
    private QueueConsumer readNewsQueue;

    public Editor() throws IOException, TimeoutException {
        this.editorId = UUID.randomUUID();
        topicPublisher = new TopicPublisher();
        readNewsQueue = new QueueConsumer();
        readNewsQueue.listen(new NewsReadCallback(newsDb, this.editorId));
    }

    public int getReadersForNews(UUID newsId) {
        New n = newsDb.findNews(this.editorId, newsId);
        if (n != null)
            return n.getReadCount();
        return -1;
    }

    public void addNews(New n){
        newsDb.addNews(n,this.editorId);
        topicPublisher.publishEvent(n, "Created");
    }

    public void modifyNewsTitle(UUID newsId, String title){
        New n = newsDb.findNews(this.editorId, newsId);
        if (n != null)
            n.setTitle(title);
    }

    public void modifyNewsDomain(UUID newsId, String domain){
        New n = newsDb.findNews(this.editorId, newsId);
        if (n != null)
            n.setDomain(domain);
    }


}
