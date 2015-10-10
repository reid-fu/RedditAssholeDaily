import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Class that analyzes text.
 *
 * @author viral
 *
 */
public class TextAnalyzer {

    /**
     * API key.
     */
    private static final String key = "a7237fba2dc27fb0a285626142515aa4f87e0bf2";
    /**
     * Base address to make Calls.
     */
    private static final String BASE_ADDRESS = "http://gateway-a.watsonplatform.net/calls/text/TextGetTextSentiment";

    /**
     * Private constructor that noone cares about.
     */
    private TextAnalyzer() {

    }

    /**
     * Create encoded URL to call
     *
     * @param str
     *            - String of text to parse
     * @return Encoded URL
     */
    private static String makeURL(String str) {
        try {
            return BASE_ADDRESS + "?apikey=" + key + "&text="
                    + URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("You done goofed");
        }
    }

    /**
     * Retrieves XML from API call
     *
     * @param url
     *            - URL to make API Call
     * @return - XMLTree from call to API
     */
    private static XMLTree getXML(String url) {
        return new XMLTree1(url);
    }

    /**
     * Main method.
     *
     * @param args
     *            - Command line arguments
     */
    public static void main(String[] args) {
        XMLTree xml = getXML(makeURL("so how are you doing? I am doing really well. Thank you!"));
        System.out.println(xml);
    }
}
