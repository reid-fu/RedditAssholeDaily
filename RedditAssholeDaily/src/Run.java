import javax.swing.JOptionPane;

import io.*;
import judge.*;
import judge.CommentJudge.Verdict;
import reddit.RedditParser;

public class Run {
	public static void main(String[] args) {
		IOManager io = new JIOManager();
		String username = io.inputUser();
		if(username == null)
			System.exit(0);
		RedditParser parser = new RedditParser(username);
		CommentJudge judge = new AlchemyJudge();
		String comment = null;
		while((comment = parser.nextComment()) != null){
			judge.score(comment);
		}
		if(judge.verdict() == Verdict.ASSHOLE){
			JOptionPane.showMessageDialog(null, "You're an Asshole");
		} else {
			JOptionPane.showMessageDialog(null, "You're not an Asshole");
		}
	}
}
