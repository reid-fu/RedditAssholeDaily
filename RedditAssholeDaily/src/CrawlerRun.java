import java.util.*;
import javax.swing.JOptionPane;
import judge.AlchemyJudge;
import judge.CommentJudge;
import reddit.*;

public class CrawlerRun {

    public static void main(String[] args) {
        crawl();
        Set<String> users = UserNameFetcher.getUserSet();
        Map<String, Double> map = new TreeMap<>();
        for (String str : users) {
            RedditParser parser = new RedditParser(str);
            CommentJudge judge = new AlchemyJudge();
            String comment;
            while ((comment = parser.nextComment()) != null) {
                judge.score(comment);
            }
            map.put(str, judge.cumulativeScore());
        }
        System.out.println(map);
    }
    public static void crawl(){
    	try {
			new FrontpageCrawler().initiateCrawling();
		} catch (AssertionError e) {
			JOptionPane.showMessageDialog(null, "Unable to process Reddit");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.exit(1);
		}
    }
}
