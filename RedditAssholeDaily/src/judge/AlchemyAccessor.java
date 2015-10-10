package judge;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import components.xmltree.XMLTree1;

public class AlchemyAccessor implements CommentJudge {
	private static String key = "a7237fba2dc27fb0a285626142515aa4f87e0bf2";
	@Override
	public double score(String comment) {
		return -1;
	}
	private static XMLTree getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";
        int index = -1;
        /*
         * Loops from last to first child and compares the root of that tree to
         * tag. Returns the lowest index whose root label matches the tag.
         */
        for (int i = xml.numberOfChildren() - 1; i >= 0; i--) {
            if (tag.equals(xml.child(i).label())) {
                index = i;
            }
        }
        return xml.child(index);
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
}
