import judge.AlchemyJudge;
import reddit.RedditParser;

public class Test {
	public static void main(String[] args){
//		testAlchemy();
		testParser();
	}
	public static void testAlchemy(){
		AlchemyJudge alAcc = new AlchemyJudge();
		System.out.println(alAcc.score("socialism"));
	}
	public static void testParser(){
		RedditParser parser = new RedditParser("cricketorcrickets");
		
	}
}
