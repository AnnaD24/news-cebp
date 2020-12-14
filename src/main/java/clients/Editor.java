package clients;

import db.NewsDb;
import rabbit.QueueConsumer;
import rabbit.TopicPublisher;
import utils.New;

import java.io.IOException;
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
        readNewsQueue.listen();
    }

//    public int getReaders(){
//        //publisherService.getReaders()
//    }

    public void addNews(New n){
        newsDb.addNews(n,this.editorId);
        topicPublisher.publishEvent(n, "created");
    }

    public void modifyNewsTitle(New news, String title){
        if(newsDb.findNews(editorId,news.getNewsId()).isEmpty())
            return;

        news.setTitle(title);
    }

    public void modifyNewsDomain(New news, String domain){
        if(newsDb.findNews(editorId,news.getNewsId()).isEmpty())
            return;

        news.setTitle(domain);
    }

    public void deleteNews(New news){
        newsDb.deleteNews(this.editorId,news);
    }

}
