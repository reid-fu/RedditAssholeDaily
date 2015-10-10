package judge;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import components.xmltree.XMLTree1;

public class AlchemyAccessor {
	private static String key = "a7237fba2dc27fb0a285626142515aa4f87e0bf2";
	public String getXML(String comment){
		try {
			return new XMLTree1(this.getURL(comment)).toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
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
