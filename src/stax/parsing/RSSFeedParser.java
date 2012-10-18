/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stax.parsing;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import javax.swing.text.html.HTML;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author udit
 */
public class RSSFeedParser {
     static final String TITLE = "title";
  static final String DESCRIPTION = "description";
  static final String LINK = "link";
  static final String ITEM = "item";
  static final String PUB_DATE = "pubDate";
  static final String GUID = "guid";
  static final String CATEGORY="category";
  
  final URL url;

  public RSSFeedParser(String feedUrl) {
    try {
      this.url = new URL(feedUrl);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
  
  @SuppressWarnings("null")
  public Feed readFeed() {
    Feed feed = null;
    try {

      boolean isFeedHeader = true;
      // Set header values intial to the empty string
      String description = "";
      String title = "";
      String link = "";
      String pubdate = "";
      String guid = "";
      ArrayList<String> categories=new ArrayList<String>();
      

      // First create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = read();
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // Read the XML document
      while (eventReader.hasNext()) {

        XMLEvent event = eventReader.nextEvent();

        if (event.isStartElement()) {
          if (event.asStartElement().getName().getLocalPart() == (ITEM)) {
            if (isFeedHeader) {
              isFeedHeader = false;
              feed = new Feed(title, link,pubdate,guid,description);
            }
            event = eventReader.nextEvent();
            continue;
          }

          if (event.asStartElement().getName().getLocalPart() == (TITLE)) {
            event = eventReader.nextEvent();
            title = event.asCharacters().getData();
            continue;
          }
          if (event.asStartElement().getName().getLocalPart() == (DESCRIPTION)) {
            event = eventReader.nextEvent();
              
            description=Jsoup.clean(event.asCharacters().getData(), Whitelist.simpleText());
            //Used Jsoup library to clean the description tag for pure text.
            continue;
          }

          if (event.asStartElement().getName().getLocalPart() == (LINK)) {
            event = eventReader.nextEvent();
            //link = event.asCharacters().getData();
            link = event.toString();
            continue;
          }

          if (event.asStartElement().getName().getLocalPart() == (GUID)) {
            event = eventReader.nextEvent();
            guid = event.asCharacters().getData();
            continue;
          }
          if (event.asStartElement().getName().getLocalPart() == (PUB_DATE)) {
            event = eventReader.nextEvent();
            pubdate = event.asCharacters().getData();
            continue;
          }
          if (event.asStartElement().getName().getLocalPart() == (CATEGORY)) {
            event = eventReader.nextEvent();
            categories.add(event.asCharacters().getData());
            continue;
          }
        } else if (event.isEndElement()) {
          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
            FeedMessage message = new FeedMessage();
            message.setDescription(description);
            message.setGuid(guid);
            message.setLink(link);
            message.setTitle(title);
            message.setPubDate(pubdate);
            message.setCategories(categories);
            feed.getFeeds().add(message);
            event = eventReader.nextEvent();
            continue;
          }
        }else{
            continue;
        }
      }
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
    return feed;

  }
  
  private InputStream read() {
    try {
      return url.openStream();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
