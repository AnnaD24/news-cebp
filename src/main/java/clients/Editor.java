package clients;

import db.NewsDb;
import utils.New;

import java.util.UUID;

public class Editor {
    private UUID editorId;
    private static NewsDb newsDb = new NewsDb();
    //PublisherService publisher;

    public Editor(){
        this.editorId = UUID.randomUUID();
    }

//    public int getReaders(){
//        //publisherService.getReaders()
//    }

    public void addNews(New n){
        newsDb.addNews(n,this.editorId);
    }

    public void modifyNewsTitle(New news, String title){
        if(newsDb.findNews(editorId,news.getNewsId()).isEmpty())
            return;

        news.setTitle(title);
    }

    public void modifyNewsDomain(New news, String domain){
        if(newsDb.findNews(editorId,news.getNewsId()).isEmpty())
            return;

        news.setTitle(domain);
    }

    public void deleteNews(New news){
        newsDb.deleteNews(this.editorId,news);
    }

}
