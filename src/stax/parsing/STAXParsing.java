/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stax.parsing;

/**
 *
 * @author udit
 */
public class STAXParsing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    RSSFeedParser parser = new RSSFeedParser("http://feeds.feedburner.com/Mobilecrunch");
    Feed feed = parser.readFeed();//Read all the feeds.
    DatabaseConnection db=new DatabaseConnection();//Database connection class
    System.out.println(feed);
    for (FeedMessage message : feed.getFeeds()) {//message will have one single item data.
        System.out.println(message);//Print feeds on console
        db.insertFeed(message);//Insert feed into database
    }
    

  }
}
