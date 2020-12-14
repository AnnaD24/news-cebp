package demo;

import clients.Editor;
import rabbit.TopicPublisher;
import utils.New;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Client2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        New news;
        Editor editor = new Editor();
        news = new New("auto", new ArrayList<String>(), editor, UUID.randomUUID(), "Schumaer wins");
        TopicPublisher tp = new TopicPublisher();
        tp.publishEvent(news, "created");
    }
}
