package cn.az.code.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

/**
 * The type Properties xml util.
 *
 * @author az
 * @date 2020 -03-06
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
            System.out.println(pe.toString());
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
