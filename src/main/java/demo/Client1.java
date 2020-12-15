package demo;

import clients.Client;
import clients.Editor;
import utils.New;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Client1 {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Client client = new Client();
        client.runClient();
    }
}
