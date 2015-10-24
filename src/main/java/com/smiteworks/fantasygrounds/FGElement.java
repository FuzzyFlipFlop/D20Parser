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
public abstract class FGElement {
    public static Document createDocument() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

        return builder.newDocument();
    }

    public static final void prettyPrint(Document document) throws Exception {
        OutputFormat format = new OutputFormat(document);
        format.setLineWidth(65);
        format.setIndenting(true);
        format.setIndent(2);
        Writer out = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(out, format);
        serializer.serialize(document);
        System.out.println(out.toString());
    }

    private Document document;

    public FGElement(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    protected Element addElement(Element parent, String name, String type, String value) {
        if (value == null || value.isEmpty()) return null;

        Element element = createPropertyElement(name, type, value);
        parent.appendChild(element);

        return element;
    }

    protected Element createPropertyElement(String name, String type, String value) {
        Element childElement = document.createElement(name);

        childElement.setAttribute("type", type);
        childElement.appendChild(document.createTextNode(value));

        return childElement;
    }

    protected Element createLinkElement(String name, String type, String className, String recordName) {
        Element childElement = document.createElement(name);

        childElement.setAttribute("type", type);

        Element classElement = document.createElement("class");
        childElement.appendChild(classElement);
        classElement.appendChild(document.createTextNode(className));

        Element recordNameElement = document.createElement("recordname");
        childElement.appendChild(recordNameElement);
        recordNameElement.appendChild(document.createTextNode(recordName));

        return childElement;
    }

    abstract public String getClassName();

    abstract public String getReferenceName();

    abstract public String getName();

    abstract public Element createElement();
}
