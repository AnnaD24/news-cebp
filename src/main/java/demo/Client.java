package demo;

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

    private static void readNews(String uuid) throws IOException, TimeoutException {
        System.out.println("Reading news with uuid: "+uuid);
        Reader r = new Reader();
        r.readNews(UUID.fromString(uuid));
    }

    public static void main(String args[]) throws IOException, TimeoutException, InterruptedException {
        boolean run = true;
        Editor editor = new Editor();

        while (run) {
            System.out.println("Options:\n1.Editor - Add news\n2.Reader - Subscribe news\n3.Reader - Read news\n4.Get the number of subscribers\nPress 9 to exit");

            Scanner in = new Scanner(System.in);
            String opt = in.nextLine();
            switch (opt) {

                case "1":
                    System.out.println("Adding a news...\nWrite domain:");
                    String domain = in.nextLine();
                    System.out.println("Write title:");
                    String title = in.nextLine();

                    addNew(editor,domain,title);
                    break;

                case "2":
                    System.out.println("Subscribing to news...\nWrite interested domain:");
                    String topic = in.nextLine();
                    System.out.println("Enter the period you are interested in:");
                    String period = in.nextLine();

                    createReader(topic,period);
                    break;

                case "3":
                    System.out.println("Reading news...\nInsert the uuid: ");
                    String newsId = in.nextLine();

                    readNews(newsId);
                    break;

                case "4":
                    System.out.println("Getting actual number of subscribers...\nInsert news id:");
                    String id = in.nextLine();
                    System.out.println("Current number of readers for news: " + id+" is " + editor.getReadersForNews(UUID.fromString(id)));
                    break;

                case "9":
                    run = false;
                    System.out.println("Exit...");
                    break;

            }
        }
    }
}
