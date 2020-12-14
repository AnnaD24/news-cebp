package demo;

import clients.Editor;
import clients.Reader;
import rabbit.TopicPublisher;
import utils.New;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Client2 {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Reader  r = new Reader();
        r.subscribeNews("auto", null);
        Thread.sleep(15);
        r.readNews(UUID.randomUUID());
    }
}
