package judge;

public interface CommentJudge {
	public enum Verdict {ASSHOLE, NONASSHOLE}
	/** @param comment comment to judge
	 * @return score for comment
	 */
	public double score(String comment);
	public double cumulativeScore();
	public Verdict verdict();
}
