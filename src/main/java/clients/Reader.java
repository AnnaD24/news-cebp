package clients;

import java.util.ArrayList;
import java.util.UUID;

public class Reader {
    private UUID readerId;
    private ArrayList<String> subscribedNewsList = new ArrayList<String>();

    public Reader(){
        this.readerId = UUID.randomUUID();
    }

    public void subscribeNews(String topic){

    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerId=" + readerId +
                ", subscribedNewsList=" + subscribedNewsList.toString() +
                '}';
    }

    public ArrayList<String> getSubscribedNewsList() {
        return this.subscribedNewsList;
    }
}
