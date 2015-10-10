package judge;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import components.xmltree.*;

public class AlchemyAccessor implements CommentJudge {
	private static String key = "a7237fba2dc27fb0a285626142515aa4f87e0bf2";
	@Override
	public double score(String comment) {
		return -1;
	}
	//TEST METHODS
	public String getXML(String comment){
		try {
			return new XMLTree1(this.getURL(comment)).toString();
		} catch (UnsupportedEncodingException e) {
			System.err.println("UTF-8 encoding not supported");
			return null;
		}
	}
	//PRIVATE METHODS
	private String getURL(String comment) throws UnsupportedEncodingException{
		return "http://gateway-a.watsonplatform.net/calls/"
				+ "text/TextGetTextSentiment?apikey=" + key + "&text="
	            + URLEncoder.encode(comment, "UTF-8");
	}
	private XMLTree child(XMLTree xml, String tag) {
        int index = -1;
        for (int i = xml.numberOfChildren() - 1; i >= 0; i--)
            if (tag.equals(xml.child(i).label()))
                index = i;
        return xml.child(index);
    }
}
