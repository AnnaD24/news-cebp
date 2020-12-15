package demo;

import clients.Client;
import clients.Editor;
import clients.Reader;
import rabbit.TopicPublisher;
import utils.New;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Client2 {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Client c = new Client();
        c.runClient();
    }
}
