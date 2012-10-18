/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stax.parsing;

import java.util.ArrayList;

/**
 *
 * @author udit
 */
public class FeedMessage {
    String title;
    String link;
    String pubDate;
    ArrayList<String> categories;

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }
    String guid;
    String description;
    
    public FeedMessage(){
        categories=new ArrayList<String>();
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        int count=0;
        String categoriesstring="";
        for(count=0;count<categories.size();count++){
            categoriesstring+="Category"+(count+1)+"="+categories.get(count)+",";
        }
        
    return "FeedMessage [title=" + title + ", description=" + description
        + ", link=" + link + ",  guid=" + guid+"Category=["+categoriesstring+"]]";
  }
    
}
