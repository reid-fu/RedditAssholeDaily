import io.*;
import reddit.RedditParser;

public class Run {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		IOManager io = new JIOManager();
		String username = io.inputUser();
		if(username == null)
			System.exit(0);
		RedditParser parser = new RedditParser(username);
	}
}
