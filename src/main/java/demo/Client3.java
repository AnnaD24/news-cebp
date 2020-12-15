package demo;

import clients.Reader;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Client3 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Reader r = new Reader();
        r.readNews(UUID.fromString("99066988-fc1a-4b25-94e2-072364b97e3e"));
    }
}
