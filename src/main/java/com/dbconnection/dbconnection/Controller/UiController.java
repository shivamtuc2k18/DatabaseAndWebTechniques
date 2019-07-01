package com.dbconnection.dbconnection.Controller;


import com.dbconnection.dbconnection.Feed.Feed;
import com.dbconnection.dbconnection.Repository.FeedRepository;
import com.dbconnection.dbconnection.Service.UrlExtraction;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UiController extends HttpServlet {

    @Autowired
    FeedRepository feedRepository;
    @Autowired
    UrlExtraction urlExtraction;


    public  List<String> tempList = new ArrayList<String>();
    public List<String> titleList = new ArrayList<>();

    int pageIndex =0;
    List<Long> dataList = new ArrayList<Long>();
    static  int counter=0;
    long newRecordsfound;

    /**
     * when user click update button the this method will get called.
     * */

    @RequestMapping(value = "/getForm", method = RequestMethod.GET)
    public ModelAndView getForm() {

        long dbCount = feedRepository.count();
        pageIndex=1;

        ModelAndView modelAndView = new ModelAndView("/urlFormPage");
        modelAndView.addObject("count",dbCount);

        return modelAndView;
    }

    /**
     * when user click submit button the this method will get called.
     * */

    @RequestMapping(value = "/saveDetails", method = RequestMethod.POST)
    public ModelAndView saveDetails(@RequestParam("urlParam1") String url1,
                              @RequestParam("urlParam2") String url2,
                              @RequestParam("urlParam3") String url3,
                              @RequestParam("urlParam4") String url4,
                              @RequestParam("urlParam5") String url5) {

                setUserUrl(url1,url2,url3,url4,url5);
                urlExtraction.getUpdatedUrl();
                boolean urlValidation = urlExtraction.urlStatus;
                long noOfNewRecordsFound=0;
                String newProviderName="";

                for(int i=0;i<tempList.size();i++)
                {
                    noOfNewRecordsFound = noOfNewRecordsFound + toCountNewRecords(tempList.get(i));
                    if(tempList.size()>1){
                        newProviderName = newProviderName + "," + " "+providerName(tempList.get(i));
                    }else{
                        newProviderName = newProviderName + providerName(tempList.get(i));
                    }

                }

                tempList.clear();
                ModelAndView modelAndView = new ModelAndView("/index");
                //if(pageIndex==1)
                modelAndView.addObject("pagIndex",pageIndex);
                modelAndView.addObject("validVal",urlValidation);
                List<Feed> list = feedRepository.findAll();
                modelAndView.addObject("feedlist",list);

                //toCountNewRecords();
                System.out.println("rem val"+newRecordsfound);

                modelAndView.addObject("noOfRec",noOfNewRecordsFound);
                modelAndView.addObject("newProvider",newProviderName);

                return modelAndView;
    }

    /**
     * Deleting All Records.
     **/


    @RequestMapping(value ="/deleteData", method = RequestMethod.POST)
    public ModelAndView deleteData() {

        System.out.println("called Delete Data");
        feedRepository.deleteAll();
        ModelAndView modelAndView = new ModelAndView("delete");

        //modelAndView.addObject("Status", "sucess");
        return modelAndView;
    }

    /**
     * Get the updated List after Delete.
     * */
    @RequestMapping(value ="/updatedList", method = RequestMethod.GET)
    public ModelAndView getUpdatedList(){

        System.out.println("Data Deleted");
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Feed> updated = feedRepository.findAll();
        modelAndView.addObject("feedlist",updated);
        return modelAndView;
    }

    /**
     * Get the updated List after Delete.
     * */

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ModelAndView getDBData(){
        if(tempList.isEmpty()==true)

        urlExtraction.getURL();
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Feed> list1 = feedRepository.findAll();
        pageIndex=0;
        modelAndView.addObject("feedlist",list1);
        modelAndView.addObject("pagIndex",pageIndex);
        return modelAndView;
    }


    /**
     * Delete Particular Records.
     * */
    @DeleteMapping(value ="Feed/{id}/delete")
        @ResponseStatus(value = HttpStatus.OK)
    public String deleteFeed(@PathVariable("id") int idx, final RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("msg","the feed is deleted");

        feedRepository.deleteById(idx);

        return "redirect:/Feed/";
    }


    /**
     * Checking the number of url is given by user while updating
     * */
    public List<String> setUserUrl(String val1, String val2, String val3, String val4, String val5){

        if(val1!="" && (val2=="" && val3=="" && val4=="" && val5=="")){

            tempList.add(val1);
            return tempList;
        }
        else if((val2!="" && val1!="") && (val3=="" && val4=="" && val5==""))
        {
            tempList.add(val1);
            tempList.add(val2);
            return tempList;
        }
        else if((val2!="" && val1!="" && val3!="") && (val4=="" && val5==""))
        {
            tempList.add(val1);
            tempList.add(val2);
            tempList.add(val3);
            return tempList;
        }
        else if((val2!="" && val1!="" && val3!="" && val4!="") && (val5==""))
        {
            tempList.add(val1);
            tempList.add(val2);
            tempList.add(val3);
            tempList.add(val4);
            return tempList;
        }
        else{
            tempList.add(val1);
            tempList.add(val2);
            tempList.add(val3);
            tempList.add(val4);
            tempList.add(val5);
            return tempList;
        }


    }

    /**
     * To count the newly added records
     * */

    public long toCountNewRecords(String str)
    {

        titleList.clear();

        try
        {
            URL url = new URL(str);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed1 = input.build(new XmlReader(url));
            String SourceTitle = feed1.getTitle();

            for(SyndEntry entry : (List<SyndEntry>) feed1.getEntries())
            {
                if(entry.getUpdatedDate().after(urlExtraction.result) || entry.getPublishedDate().after(urlExtraction.result))
                titleList.add(entry.getTitle());

            }


        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return titleList.size();
    }

    /**
     * To get the newly added provider name.
     * */

    public String providerName(String str){

        titleList.clear();
        String SourceTitle="";
        try
        {
            URL url = new URL(str);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed1 = input.build(new XmlReader(url));
            SourceTitle = feed1.getTitle();

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return SourceTitle;
    }

}
