package reddit;
import java.util.Set;
import java.util.TreeSet;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Gets User names from RSS feeds.
 *
 * @author viral
 *
 */
public class UserNameFetcher {
    private static final Set<String> USER_SET = new TreeSet<>();
    public void addUserNames(String url) {
        System.out.println(url);
        XMLTree rss = new XMLTree1(url);
        rss = rss.child(0);
        //get's first occurence of item tag which is NOT the tag that contains user info
        int i = FrontpageCrawler.getChildElement(rss, "item");
        for (i = i + 1; i < rss.numberOfChildren(); i++) {
            XMLTree child = rss.child(i);
            if (child.label().equals("item")) {
                int indexOfTitle = FrontpageCrawler.getChildElement(child,
                        "title");
                String title = child.child(indexOfTitle).child(0).label();
                String userName = title.substring(0, title.indexOf(" "));
                USER_SET.add(userName);
            }
        }
        System.out.println(USER_SET);
    }
    public static Set<String> getUserSet(){
        return USER_SET;
    }
}
