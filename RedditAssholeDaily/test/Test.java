import judge.AlchemyJudge;
import reddit.RedditParser;

public class Test {
	public static void main(String[] args){
//		RedditParser parser = new RedditParser("cricketorcrickets");
		testAlchemy();
	}
	public static void testAlchemy(){
		AlchemyJudge alAcc = new AlchemyJudge();
		System.out.println(alAcc.score("socialism"));
	}
}
