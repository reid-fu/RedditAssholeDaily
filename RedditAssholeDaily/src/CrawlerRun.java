import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;
import io.HTMLGenerator;
import judge.AlchemyJudge;
import judge.CommentJudge;
import reddit.*;

public class CrawlerRun {
	private static int NUM_COMMENTS = 10;
    public static void main(String[] args) {
        crawl();
        Set<String> users = UserNameFetcher.getUserSet();
        Map<String, Double> map = new TreeMap<>();
        for (String user : users) {
            double score = scoreUser(user);
            System.out.println("User " + user + " got score " + score);
            map.put(user, score);
        }
        HTMLGenerator writer = new HTMLGenerator(map, "output.html");
        try {
			writer.generateHTML();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can't write HTML file");
		}
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
    public static double scoreUser(String user){
    	RedditParser parser = new RedditParser(user);
        CommentJudge judge = new AlchemyJudge();
        String comment = null;
        int numComments = 0;
        while (numComments < NUM_COMMENTS &&
        		(comment = parser.nextComment()) != null) {
            judge.score(comment);
        }
    	return judge.cumulativeScore();
    }
}
