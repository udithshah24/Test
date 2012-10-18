/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stax.parsing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author udit
 */
public class Feed {
    String title;
    String link;
    String pubDate;
    String guid;
    String description;
    List<FeedMessage> feeds=new ArrayList<FeedMessage>();

    public Feed(String title, String link, String pubDate, String guid, String description) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.guid = guid;
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FeedMessage> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<FeedMessage> feeds) {
        this.feeds = feeds;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
     public String toString() {
    return "Feed [ description=" + description
        + ", link=" + link + ", pubDate="
        + pubDate + ", title=" + title + "]";
  }
    
}
