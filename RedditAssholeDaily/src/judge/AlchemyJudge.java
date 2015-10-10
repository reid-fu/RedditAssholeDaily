package judge;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import components.xmltree.*;

/** judges comments using Alchemy API;
 * cumulative score calculated using exponential averaging
 * @author Reid Fu
 */
public class AlchemyJudge implements CommentJudge {
	private static String key = "a7237fba2dc27fb0a285626142515aa4f87e0bf2";
	private double cumScore = 0;
	@Override
	public double score(String comment) {
		try {
			XMLTree docSentiment = child(getXMLTree(getURL(comment)), "docSentiment");
			XMLTree scoreTag = child(docSentiment, "score");
			double score = Double.parseDouble(scoreTag.child(0).label());
			this.updateCumScore(score);
			return score;
		} catch (UnsupportedEncodingException e) {
			System.err.println("UTF-8 encoding not supported");
			return -1;
		}
	}
	@Override
	public double cumulativeScore() {
		return this.cumScore;
	}
	@Override
	public Verdict verdict() {
		if(this.cumScore < 0){
			return Verdict.ASSHOLE;
		} else {
			return Verdict.NONASSHOLE;
		}
	}
	//PRIVATE METHODS
	private String getURL(String comment) throws UnsupportedEncodingException{
		return "http://gateway-a.watsonplatform.net/calls/"
				+ "text/TextGetTextSentiment?apikey=" + key + "&text="
	            + URLEncoder.encode(comment, "UTF-8");
	}
	private XMLTree getXMLTree(String URL){
		return new XMLTree1(URL);
	}
	private XMLTree child(XMLTree xml, String tag) {
        int index = -1;
        for (int i = xml.numberOfChildren() - 1; i >= 0; i--)
            if (tag.equals(xml.child(i).label()))
                index = i;
        return xml.child(index);
    }
	private void updateCumScore(double score){
		double alpha = .5;
		this.cumScore = alpha*score + (1 - alpha)*this.cumScore;
	}
}
