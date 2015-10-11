package reddit;
import components.xmltree.*;

public class FrontpageCrawler {
    private static final String REDDIT_RSS = "http://www.reddit.com/.rss";

    public FrontpageCrawler() {

    }

    public void initiateCrawling() {
        XMLTree redditRss = new XMLTree1(REDDIT_RSS);
        //get channel as root
        redditRss = redditRss.child(0);
        for (int i = 0; i < redditRss.numberOfChildren(); i++) {
            XMLTree child = redditRss.child(i);
            //find items
            if (child.label().equals("item")) {
                //find links
                int index = getChildElement(child, "link");
                UserNameFetcher fetch = new UserNameFetcher();
                fetch.getUserNames(child.child(index).child(0).label() + ".rss");
                return;
            }
        }

    }

    public static int getChildElement(XMLTree xml, String string) {
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            if (xml.child(i).label().equals(string)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FrontpageCrawler fpc = new FrontpageCrawler();
        fpc.initiateCrawling();

    }

}
