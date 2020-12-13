package db;

import utils.New;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class NewsDb {
    public HashMap<UUID, ArrayList<New>> db = new HashMap<>();

    public void addNews(New n,UUID editorId){
        if(db.containsKey(editorId))
            db.get(editorId).add(n);
        else {
            ArrayList<New> newList = new ArrayList<>();
            newList.add(n);
            db.put(editorId, newList);
        }
    }

    public Optional<New> findNews(UUID editorId, UUID newsId){
        ArrayList<New> news = db.get(editorId);
        return news.stream().filter(n -> n.getNewsId().equals(newsId)).findFirst();
    }

    public void deleteNews(UUID editorId, New news){
        Optional<New> newsOptional = this.findNews(editorId,news.getNewsId());
        if(newsOptional.isEmpty())
            return;
        db.get(editorId).remove(newsOptional.get());
    }


}
