package reddit;
import components.xmltree.*;
import xml.XMLUtil;

public class FrontpageCrawler {
    private static final String REDDIT_RSS = "http://www.reddit.com/.rss";
    private static final int NUM_FEEDS = 10;
    public void initiateCrawling() throws AssertionError {
        XMLTree redditRss = new XMLTree1(REDDIT_RSS);
        int j = NUM_FEEDS;
        //get channel as root
        redditRss = redditRss.child(0);
        for (int i = 0; i < redditRss.numberOfChildren(); i++) {
            XMLTree child = redditRss.child(i);
            //find items
            if (child.label().equals("item")) {
                //find links
                int index = XMLUtil.indexOf(child, "link");
                UserNameFetcher fetch = new UserNameFetcher();
                fetch.addUserNames(child.child(index).child(0).label() + ".rss");
                if(j == 0){
                    return;
                } else{
                    j--;
                }
            }
        }
    }
}
