import java.util.Set;
import reddit.*;

public class CrawlRun {
	public static void main(String[] args) {
		new FrontpageCrawler().initiateCrawling();
		Set<String> users = UserNameFetcher.getUserSet();
	}
}
