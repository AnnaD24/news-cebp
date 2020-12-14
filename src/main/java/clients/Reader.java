package clients;

import rabbit.QueuePublisher;
import rabbit.TopicConsumer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Reader {
    private UUID readerId;
    private ArrayList<String> subscribedNewsList = new ArrayList<String>();
    private TopicConsumer topicConsumer;
    private QueuePublisher readNewsPublisher;

    public Reader() throws IOException, TimeoutException {
        this.readerId = UUID.randomUUID();
        topicConsumer = new TopicConsumer();
        readNewsPublisher = new QueuePublisher();
        topicConsumer.run();
    }

    public void subscribeNews(String topic, LocalDateTime dt){
        topicConsumer.addFilter(topic, dt);
    }

    public void readNews(UUID newsId) {
        readNewsPublisher.publish(newsId.toString());
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerId=" + readerId +
                ", subscribedNewsList=" + subscribedNewsList.toString() +
                '}';
    }

    public ArrayList<String> getSubscribedNewsList() {
        return this.subscribedNewsList;
    }
}
