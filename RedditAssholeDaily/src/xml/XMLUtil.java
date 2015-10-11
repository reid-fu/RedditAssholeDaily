package xml;
import components.xmltree.XMLTree;

public class XMLUtil {
	public static int indexOf(XMLTree xml, String string) {
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            if (xml.child(i).label().equals(string)) {
                return i;
            }
        }
        return -1;
    }
}
