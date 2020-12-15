package utils;

import clients.Editor;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class New {
    private String title;
    private String domain;
    private final UUID newsId;
    private ArrayList<String> subdomainList = new ArrayList<String>();
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime lastModifiedDate = LocalDateTime.now();
    private int readCount = 0;
    private Lock lock = new ReentrantLock();
    public New(String domain, ArrayList<String> subdomainList, UUID newsId, String title) {
        this.domain = domain;
        this.title = title;
        this.newsId = newsId;
        this.subdomainList = subdomainList;
    }

    public void incrementReadCount() {
        lock.lock();
        readCount++;
        lock.unlock();
    }

    public String getTitle() {
        return title;
    }

    public int getReadCount() {
        return readCount;
    }

    public String getDomain() {
        return domain;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setTitle(String title) {
        this.title = title;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void setDomain(String domain) {
        this.domain = domain;
        this.lastModifiedDate = LocalDateTime.now();

    }

    public UUID getNewsId() {
        return newsId;
    }

    public void setSubdomainList(ArrayList<String> subdomainList) {
        this.subdomainList = subdomainList;
        this.lastModifiedDate = LocalDateTime.now();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof New)) return false;
        New aNew = (New) o;
        return  Objects.equals(newsId, aNew.newsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, domain, newsId, subdomainList, createdDate, lastModifiedDate);
    }
}
