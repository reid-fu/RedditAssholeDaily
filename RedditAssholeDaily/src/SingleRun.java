import javax.swing.JOptionPane;
import io.*;
import judge.*;
import judge.CommentJudge.Verdict;
import reddit.RedditParser;

public class SingleRun {
	public static void main(String[] args) {
		IOManager io = new JIOManager();
		String username = io.inputUser();
		if(username == null)
			System.exit(0);
		RedditParser parser = new RedditParser(username);
		CommentJudge judge = new AlchemyJudge();
		String comment = null;
		while((comment = parser.nextComment()) != null){
			System.out.println(judge.score(comment) + "\t" + comment);
		}
		if(judge.verdict() == Verdict.DOUCHEBAG){
			JOptionPane.showMessageDialog(null,
					"You're a Douchebag. Your score is " + judge.cumulativeScore());
		} else if(judge.verdict() == Verdict.ASSHOLE) {
			JOptionPane.showMessageDialog(null,
					"You're almost an Asshole. Your score is "
					+ judge.cumulativeScore());
		} else if(judge.verdict() == Verdict.NEUTRAL) {
			JOptionPane.showMessageDialog(null,
					"You're Neutral. Your score is " + judge.cumulativeScore());
		} else {
			JOptionPane.showMessageDialog(null,
					"You're a Non-asshole. Your score is "
					+ judge.cumulativeScore());
		}
	}
}
