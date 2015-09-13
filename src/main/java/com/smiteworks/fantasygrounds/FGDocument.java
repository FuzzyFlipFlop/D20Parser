package com.smiteworks.fantasygrounds;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Stimpyjc on 9/13/2015.
 */
public class FGDocument {
    public static Document createDocument() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

        return builder.newDocument();
    }

    protected static Element addElement(Element parent, String name, String type, String body) {
        if (body == null || body.isEmpty()) return null;

        Document doc = parent.getOwnerDocument();
        Element childElement = doc.createElement(name);
        parent.appendChild(childElement);

        childElement.setAttribute("type", type);
        childElement.appendChild(doc.createTextNode(body));

        return childElement;
    }

    protected static Element addLibraryLinkElement(Element parent, String type, String className, String recordName) {
        Document doc = parent.getOwnerDocument();
        Element childElement = doc.createElement("librarylink");
        parent.appendChild(childElement);

        childElement.setAttribute("type", type);

        Element classElement = doc.createElement("class");
        childElement.appendChild(classElement);
        classElement.appendChild(doc.createTextNode(className));

        Element recordNameElement = doc.createElement("recordname");
        childElement.appendChild(recordNameElement);
        recordNameElement.appendChild(doc.createTextNode(recordName));

        return childElement;
    }

    public static final void prettyPrint(Document xml) throws Exception {
        OutputFormat format = new OutputFormat(xml);
        format.setLineWidth(65);
        format.setIndenting(true);
        format.setIndent(2);
        Writer out = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(out, format);
        serializer.serialize(xml);
        System.out.println(out.toString());
    }
}
