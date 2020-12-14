package demo;

import clients.Editor;
import rabbit.TopicConsumer;
import utils.New;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Client1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Editor editor = new Editor();
        New news = new New("auto", new ArrayList<String>(), UUID.randomUUID(), "Schumaer wins");
        System.out.println();
        editor.addNews(news);

    }
}
