package com.smiteworks.fantasygrounds;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stimpyjc on 10/31/2015.
 */
public class FGBasicElement {
    private String className;
    private String referenceName;
    private String name;

    private List<FGProperty> propertyList;
    private List<FGBasicElement> elementList;

    public FGBasicElement(String name, String referenceName, String className) {
        this.name = name;
        this.referenceName = referenceName;
        this.className = className;

        propertyList = new ArrayList<>();
        elementList = new ArrayList<>();
    }

    public String getClassName() {
        return className;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public String getName() {
        return name;
    }

    public List<FGProperty> getPropertyList() {
        return propertyList;
    }

    public FGBasicElement addProperty(String name, String type, String value) {

        propertyList.add(new FGProperty(name, type, value));

        return this;
    }

    public FGBasicElement addProperty(FGProperty property) {

        propertyList.add(property);

        return this;
    }

    public FGBasicElement addElement(FGBasicElement element) {
        elementList.add(element);

        return this;
    }

    public Element createElement(Document document) {
        Element element = document.createElement(getReferenceName());

        propertyList.stream().filter(property -> property.getValue() != null && !property.getValue().isEmpty()).forEach(property -> element.appendChild(createPropertyElement(document, property)));
        elementList.stream().forEach(childElement -> element.appendChild(childElement.createElement(document)));

        return element;
    }

    protected Element createPropertyElement(Document document, String name, String type, String value) {
        Element childElement = document.createElement(name);

        childElement.setAttribute("type", type);
        childElement.appendChild(document.createTextNode(value));

        return childElement;
    }

    protected Element createPropertyElement(Document document, FGProperty property) {
        Element childElement = document.createElement(property.getName());

        childElement.setAttribute("type", property.getType());
        childElement.appendChild(document.createTextNode(property.getValue()));

        return childElement;
    }

    protected Element createLinkElement(Document document, String name, String type, String className, String recordName) {
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

    public static Document createDocument() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

        return builder.newDocument();
    }

    public static String documentToString(Document document) throws Exception {
        OutputFormat format = new OutputFormat(document);
        format.setLineWidth(65);
        format.setIndenting(true);
        format.setIndent(2);
        Writer out = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(out, format);
        serializer.serialize(document);
        return out.toString();
    }

    public static String elementToString(Element element) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(element);
        transformer.transform(source, result);

        return result.getWriter().toString();
    }
}
