import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import judge.AlchemyJudge;
import judge.CommentJudge;
import reddit.FrontpageCrawler;
import reddit.RedditParser;
import reddit.UserNameFetcher;

public class CrawlerRun {

    public static void main(String[] args) {
        try {
			new FrontpageCrawler().initiateCrawling();
		} catch (AssertionError e) {
			JOptionPane.showMessageDialog(null, "Unable to process Reddit");
		}
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
}
