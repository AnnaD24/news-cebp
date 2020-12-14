package demo;

import clients.Editor;
import rabbit.TopicPublisher;
import utils.New;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Client2 {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Editor editor = new Editor();
        New news = new New("auto", new ArrayList<String>(), editor, UUID.randomUUID(), "Schumaer wins");
        TopicPublisher tp = new TopicPublisher();
        tp.publishEvent(news, "created");
        TimeUnit.SECONDS.sleep(30);
        New news2 = new New("auto", new ArrayList<String>(),editor,UUID.randomUUID(), "test2");
        tp.publishEvent(news2, "created");
        TimeUnit.SECONDS.sleep(33);
        New news3 = new New("auto", new ArrayList<String>(),editor,UUID.randomUUID(), "test3");
        tp.publishEvent(news3, "created");
    }
}
