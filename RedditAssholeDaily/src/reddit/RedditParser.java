package reddit;
import components.xmltree.*;

public class RedditParser {
    /** base address to fetch RSS feed */
    private static final String BASE = "https://www.reddit.com/user/";
    XMLTree rep;
    int currentIndex;
    //returns URL to fetch RSS feed from using userName
    private static String makeURL(String userName) {
        return BASE + userName + ".rss";
    }
    private static XMLTree child(XMLTree xml, String tag) {
        int index = -1;
        for (int i = xml.numberOfChildren() - 1; i >= 0; i--){
            if (tag.equals(xml.child(i).label())){
                index = i;
            }
        }
        return (index == -1) ? null : xml.child(index);
    }
   public RedditParser(String userName){
       this.rep = new XMLTree1(makeURL(userName)).child(0);
       this.currentIndex = 0;
   }
   //Returns int of next "item" tag's index
   private int nextItemIndex(){
       int i = this.currentIndex;
       do {
           i++;
       } while(i < rep.numberOfChildren() && !rep.child(i).label().equals("item"));
       return i < rep.numberOfChildren() ? i : -1;
   }
   //returns string version of comment
   public String nextComment(){
       this.currentIndex = nextItemIndex();
       return this.currentIndex == -1 ? null
    		   : child(rep.child(currentIndex), "description").child(0).label();
   }
}
