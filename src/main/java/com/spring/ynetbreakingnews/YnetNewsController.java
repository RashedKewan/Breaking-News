package com.springboot.ynetnews_;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class YnetNewsController {

    private final  String URL = "http://www.ynet.co.il/Integration/StoryRss2.xml";
    private final String LINK = "link";
    private final String TITLE = "title";
    private final String DESCRIPTION = "description";
    private final String PUBDATE = "pubDate";
    ;

    /**
     *
     * @param model  It  used to transfer data between the view and controller of the Spring MVC application.
     * @return Html file with name of "index" that exists in "YnetNews_\src\main\resources\templates\index.html".
     */
    @GetMapping
    public String index(@NonNull Model model){
        model.addAttribute("news", this.getAllNews());
        return "index";
    }

    /**
     * @return All the news from xml file at given URL.
     */
    public ArrayList<ArrayList<String>> getAllNews() {
        ArrayList<ArrayList<String>> allNews = new ArrayList<>();

        try {
            /** request xml file from link and parse it to list of objects     **/
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            NodeList news = builder.parse(URL).getElementsByTagName("item");

            /** Save each item into variable as list & add it to all news list **/
            for (int i = 0; i < news.getLength(); i++) {
                Node item = news.item(i);
                NodeList children = item.getChildNodes();
                ArrayList<String> newsItem = getNewsItem(children);
                allNews.add(newsItem);
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return allNews;
    }

    /**
     *
     * @param children News item from the news list.
     * @return The children  as list of string.
     */
    private ArrayList<String> getNewsItem(NodeList children){
        ArrayList<String> newsItem = new ArrayList<>();
        /** For each child's content, add content as string to newsItem **/
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            switch (child.getNodeName()) {
                case TITLE:
                case LINK :
                    newsItem.add(child.getTextContent());
                    break;
                case DESCRIPTION:
                    String content = child.getTextContent();
                    boolean contentNotEmpty = content.length() > 0;
                    if (contentNotEmpty) {
                        /** find </div> substring, then get content after that for the image **/
                        newsItem.add(content.substring(content.indexOf("</div>") + 6));
                        newsItem.add(content.substring(content.indexOf("src") + 5, content.indexOf("' alt")));
                        break;
                    }
                    newsItem.add(" ");
                    break;

                case PUBDATE:
                    newsItem.add(child.getTextContent().substring(0, 25));
                    break;
            }
        }
        return newsItem;
    }



}
