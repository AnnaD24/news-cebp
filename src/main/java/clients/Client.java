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
            System.out.println("Options\n1.Subscribe news\n2.Read news\n3.Exit\n");
            int opt = this.scanner.nextInt();
            switch (opt) {
                case 1:
                    System.out.println("Subscribing to news...\nWrite interested domain:");
                    String topic = this.scanner.next();
                    System.out.println("Enter the period you are interested in (number of minutes):");
                    String period = this.scanner.next();
                    r.subscribeNews(topic, LocalDateTime.now(), Integer.parseInt(period));
                    break;
                case 2:
                    System.out.println("Reading news...\nInsert the uuid: ");
                    String newsId = this.scanner.next();
                    r.readNews(UUID.fromString(newsId));
                    break;
                case 3:
                    System.out.println("Exiting! Good bye!");
                    System.exit(0);
            }
        }
    }

    private void editorInterface() {
        Editor e = null;
        try {
            e = new Editor();
        } catch (IOException | TimeoutException exception) {
            System.out.println("Failed to initialize client");
            exception.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Logged in as editor");
        while (true) {
            System.out.println("Options\n1.Publish news\n2.Modify news domain\n3.Modify news title\n4.Get number of readers\n5.Exit");
            int opt = this.scanner.nextInt();
            switch (opt) {
                case 1:
                    System.out.println("Adding news...\nWrite news domain:");
                    String topic = this.scanner.next();
                    System.out.println("\nWrite news title:");
                    String title1 = this.scanner.next();
                    e.addNews(new New(topic,new ArrayList<>(), UUID.randomUUID() , title1));
                    break;
                case 2:
                    System.out.println("Modifying news domain...\nInsert the newsId: ");
                    String newsId1 = this.scanner.next();
                    System.out.println("\nWrite new domain: ");
                    String domain = this.scanner.next();
                    e.modifyNewsDomain(UUID.fromString(newsId1), domain);
                    break;
                case 3:
                    System.out.println("Modifying news title...\nInsert the newsId: ");
                    String newsId3 = this.scanner.next();
                    System.out.println("\nWrite new title: ");
                    String title3 = this.scanner.next();
                    e.modifyNewsTitle(UUID.fromString(newsId3), title3);
                    break;
                case 4:
                    System.out.println("Querying current number of readers...\nInsert the newsId: ");
                    String newsId4 = this.scanner.next();
                    System.out.println("Readers number=" + e.getReadersForNews(UUID.fromString(newsId4)) + "\n");
                    break;
                case 5:
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
                    this.editorInterface();
                case 2:
                    this.readerInterface();
                case 3:
                    System.exit(0);
            }
        }
    }



}
