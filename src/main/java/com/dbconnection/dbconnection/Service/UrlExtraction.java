package com.dbconnection.dbconnection.Service;

import com.dbconnection.dbconnection.Controller.UiController;
import com.dbconnection.dbconnection.Feed.Feed;
import com.dbconnection.dbconnection.Repository.FeedRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.apache.tomcat.jni.Time.now;

@Service
public class UrlExtraction {

    /**
     * Initilaizing the object
     * */

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    UiController uiController;

    /**
     * Global variable Declaration.
     */

    public List<String> linkList = new ArrayList<String>();
    public List<String> titleList = new ArrayList<String>();
    public List<Date> dateList = new ArrayList<Date>();
    public List<String> providerList = new ArrayList<String>();
    //public List<String> descriptionlist = new ArrayList<String>();
    public List<String> updatedlinkList = new ArrayList<String>();
    public List<String> updatedtitleList = new ArrayList<String>();
    public List<Date> updateddateList = new ArrayList<Date>();
    public List<String> updatedproviderList = new ArrayList<String>();
    public List<String> urlLinks = new ArrayList<String>();
    public List<String> tempUrlLinks = new ArrayList<String>();
    //DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
    int counterForUrlHit =0;

    public boolean urlStatus = false;
    public Date result;


    /**
     * Passing all the five URL to the readData function to traverse through all the rss.
     */

    public void getURL()
    {
        date30DaysOld();
            urlLinks.add("https://www.nasa.gov/rss/dyn/chandra_images.rss");
            urlLinks.add("https://www.nasa.gov/rss/dyn/breaking_news.rss");
            urlLinks.add("https://www.techworld.com/news/rss");
            urlLinks.add("https://www.nasa.gov/rss/dyn/aeronautics.rss");
            urlLinks.add("https://www.nasa.gov/rss/dyn/onthestation_rss.rss");

            if(counterForUrlHit==0 && (urlLinks.equals(tempUrlLinks)==false)) {
                for(int i=0; i<urlLinks.size();i++){

                    if(isValidURL(urlLinks.get(i))==true)
                    {
                        readData(urlLinks.get(i));
                        tempUrlLinks.add(urlLinks.get(i));
                    }
                }

            }
            else
                {
                    System.out.println("Second Hit" + counterForUrlHit);
                }


        counterForUrlHit++;
    }

    /**
     * To validate the url
     * */
    public boolean isValidURL(String urlString)
    {
        try
        {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception exception)
        {
            return false;
        }
    }

    /**
     * User Given URL's will be handled here.
     * */

    public void getUpdatedUrl()
    {
        date30DaysOld();
        urlStatus = false;
        for(int i=0; i<uiController.tempList.size();i++)
        {
            /// checks to see if the given url by user is correct or not.

            if(isValidURL(uiController.tempList.get(i))==true)
            {
                updatedReadData(uiController.tempList.get(i));

                if(uiController.tempList.size()==1){

                    urlStatus = isValidURL(uiController.tempList.get(i));

                }
                if(uiController.tempList.size()==2){

                    if((isValidURL(uiController.tempList.get(0))) && (isValidURL(uiController.tempList.get(1)))){

                        urlStatus = isValidURL(uiController.tempList.get(i));
                    }else{
                        urlStatus=false;
                    }
                }
                if(uiController.tempList.size()==3)
                {
                    if((isValidURL(uiController.tempList.get(0))) && (isValidURL(uiController.tempList.get(1)) && (isValidURL(uiController.tempList.get(2))))){

                        urlStatus = isValidURL(uiController.tempList.get(i));
                    }else{
                        urlStatus=false;
                    }
                }
                if(uiController.tempList.size()==4)
                {
                    if((isValidURL(uiController.tempList.get(0))) && (isValidURL(uiController.tempList.get(1))) && (isValidURL(uiController.tempList.get(2))) &&((isValidURL(uiController.tempList.get(3))))){

                        urlStatus = isValidURL(uiController.tempList.get(i));
                    }else{
                        urlStatus=false;
                    }
                }
                if(uiController.tempList.size()==5)
                {
                    if((isValidURL(uiController.tempList.get(0))) && (isValidURL(uiController.tempList.get(1))) && (isValidURL(uiController.tempList.get(2))) && ((isValidURL(uiController.tempList.get(3)))) && (((isValidURL(uiController.tempList.get(4)))))){

                        urlStatus = isValidURL(uiController.tempList.get(i));
                    }else{
                        urlStatus=false;
                    }
                }

            }
        }
    }

    /**
     * This will take out the "Title", "Link", "PubDate" field from the RSS link.
     */

    public void updatedReadData(String feed)
    {
        try
        {
            URL url = new URL(feed);
            SyndFeedInput input1 = new SyndFeedInput();
            SyndFeed feed2 = input1.build(new XmlReader(url));
            String SourceTitle1 = feed2.getTitle();

            for(SyndEntry entry : (List<SyndEntry>) feed2.getEntries())
            {
                if(entry.getUpdatedDate().after(result) || entry.getPublishedDate().after(result))
                {
                    updatedtitleList.add(entry.getTitle());
                    updatedlinkList.add(entry.getLink());

                    if(entry.getPublishedDate()!=null){

                        updateddateList.add(entry.getPublishedDate());
                    }
                    else {
                        updateddateList.add(entry.getUpdatedDate());
                    }

                    updatedproviderList.add(SourceTitle1);
                }


            }

            getUpdatedFinalList();

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * read the url given in the code
     * */

    public void readData(String feed)
    {

        try
        {
            URL url = new URL(feed);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed1 = input.build(new XmlReader(url));
            String SourceTitle = feed1.getTitle();

            for(SyndEntry entry : (List<SyndEntry>) feed1.getEntries())
            {
                if(entry.getPublishedDate().after(result)){

                    titleList.add(entry.getTitle());
                    linkList.add(entry.getLink());
                    if(entry.getPublishedDate()!=null){
                        dateList.add(entry.getPublishedDate());

                    }
                    else {

                        dateList.add(entry.getUpdatedDate());

                    }

                    providerList.add(SourceTitle);
                }


            }

            getfinalList();

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    /**
     * Add all the list values to the DB.
     */

    public void getfinalList()
    {

        for(int m=0; m<titleList.size();m++){


            Feed feedDb = new Feed();
            feedDb.setId(m);
            feedDb.setTitle(titleList.get(m));
            feedDb.setLink(linkList.get(m));
            feedDb.setDate(dateList.get(m));
            feedDb.setProvider(providerList.get(m));
            feedDb.setInserteddate(LocalDateTime.now());

            feedRepository.save(feedDb);

        }

        //date30DaysOld();

    }

    /**
     * set the newly url updated value to the DB
     * */
    public void getUpdatedFinalList()
    {
        int previousCount = (int) feedRepository.count();

        for(int m=0; m<updatedtitleList.size();m++){
            Feed feedDb = new Feed();
            feedDb.setId(previousCount);
            feedDb.setTitle(updatedtitleList.get(m));
            feedDb.setLink(updatedlinkList.get(m));
            feedDb.setDate(updateddateList.get(m));
            feedDb.setProvider(updatedproviderList.get(m));
            feedDb.setInserteddate(LocalDateTime.now());

            feedRepository.save(feedDb);
            previousCount++;

        }
        clearListData();


    }

    /**
     * to get the last month date.
     * */

    public void date30DaysOld(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();

        System.out.println("30 day older time"+result);
    }

    /**
     * after all the operation clearing all the data.
     * */

    public void clearListData(){
        updatedtitleList.clear();
        updatedlinkList.clear();
        updateddateList.clear();
        updatedproviderList.clear();

    }


}
