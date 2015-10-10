package reddit;
import components.xmltree.*;

public class RedditParser {
    //base address to fetch RSS feed
    private static final String BASE = "https://www.reddit.com/user/";

    //returns URL to fetch RSS feed from using userName
    private static String makeURL(String userName) {
        return BASE + userName + ".rss";
    }
    private static XMLTree child(XMLTree xml, String tag) {
        int index = -1;
        for (int i = xml.numberOfChildren() - 1; i >= 0; i--)
            if (tag.equals(xml.child(i).label()))
                index = i;
        return xml.child(index);
    }
    
   XMLTree rep;
   int currentIndex;
   
   public RedditParser(String userName){
       this.rep = new XMLTree1(makeURL(userName));
       this.currentIndex = 0;
   }
   
   
   //Returns int of next "item" tag's index
   private int getNextCommentItemIndex(int currentIndex){
       int i = currentIndex;
       while(!this.rep.child(i).label().equals("item")){
           i++;
       }
       return i;
   }
   
   //returns string version of comment
   public String getNextComment(int indexOfCurrentItem){
       this.currentIndex = getNextCommentItemIndex(indexOfCurrentItem);
       return child(this.rep.child(currentIndex), "description").child(0).label();
   }

}
