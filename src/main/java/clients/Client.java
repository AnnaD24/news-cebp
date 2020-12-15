package clients;

import clients.Editor;
import clients.Reader;
import utils.New;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Client {

    private Scanner scanner = new Scanner(System.in);
    private static Editor addNew(Editor editor,String domain, String title) throws IOException, TimeoutException {
        System.out.println("Creating news with domain " + domain + " and title "+title);
        New news = new New(domain, new ArrayList<String>(), UUID.randomUUID(), title);
        System.out.println();
        editor.addNews(news);
        return editor;
    }

    private static void createReader(String topic, String period) throws IOException, TimeoutException {
        System.out.println("Subscribing to topic " + topic + " for period of " + period + " days" );
        Reader r = new Reader();
        r.subscribeNews(topic, LocalDateTime.now(), Integer.parseInt(period));
    }


    private void readerInterface () {
        Reader r = null;
        try {
            r = new Reader();
        } catch (IOException | TimeoutException e) {
            System.out.println("Failed to initialize client");
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Logged in as reader");
        while (true) {
            System.out.println("Options\n1.Subscribe news\n2.Read news\n3.Exit");
            String opt = this.scanner.nextLine();
            switch (opt) {
                case "1":
                    System.out.println("Subscribing to news...\nWrite interested domain:");
                    String topic = this.scanner.nextLine();
                    System.out.println("Enter the period you are interested in (number of days):");
                    String period = this.scanner.nextLine();
                    r.subscribeNews(topic, LocalDateTime.now(), Integer.parseInt(period));
                    break;
                case "2":
                    System.out.println("Reading news...\nInsert the uuid: ");
                    String newsId = this.scanner.nextLine();
                    r.readNews(UUID.fromString(newsId));
                    break;
                case "3":
                    System.out.println("Exiting! Good bye!");
                    System.exit(0);
            }
        }
    }

    public void runClient () {
        while (true) {
            System.out.println("Options:\n1.Editor\n2.Reader\n3.Exit");
            int opt = this.scanner.nextInt();
            switch(opt) {
                case 1:
                    break;
                case 2:
                    this.readerInterface();
                case 3:
                    System.exit(0);
            }
        }
    }

}
