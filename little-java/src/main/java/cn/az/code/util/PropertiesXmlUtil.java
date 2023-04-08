package cn.az.code.util;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * The type Properties xml util.
 *
 * @author az
 */
public class PropertiesXmlUtil {

    /**
     * Document for XML.
     */
    private static Document document;

    static {
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("filePath");
        } catch (Exception pe) {
            System.out.println(pe);
        }
    }

    /**
     * Gets xml attribute.
     *
     * @param id the id
     * @return the xml attribute
     */
    public static String getXmlAttribute(String id) {
        Element element = document.getElementById(id);
        return (element == null) ? null : element.getAttribute("value");
    }
}
