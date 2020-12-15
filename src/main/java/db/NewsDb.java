package db;

import utils.New;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class NewsDb {
    public HashMap<UUID, HashMap<UUID, New>> db = new HashMap<>();

    public void addNews(New n, UUID editorId){
        if(db.containsKey(editorId))
            db.get(editorId).put(n.getNewsId(), n);
        else {
            HashMap<UUID, New> map = new HashMap<>();
            map.put(n.getNewsId(), n);
            db.put(editorId, map);
        }
    }

    public New findNews(UUID editorId, UUID newsId){
        New n = db.get(editorId).get(newsId);
        return n;
    }



}
