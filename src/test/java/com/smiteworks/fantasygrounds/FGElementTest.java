package com.smiteworks.fantasygrounds;

import org.easymock.EasyMock;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlunit.matchers.CompareMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Created by Stimpyjc on 10/24/2015.
 */
public class FGElementTest {
    @Test
    public void testCreateLinkElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <librarylink type=\"windowreference\">\n" +
                "<class>referenceindexsorted</class>\n" +
                "<recordname>lists.npc@Test Module</recordname>\n" +
                "</librarylink>");

        Document document = FGElement.createDocument();
        FGElement element = new FakeFGElement(document);

        // Act
        Element linkElement = element.createLinkElement("librarylink", "windowreference", "referenceindexsorted", "lists.npc@Test Module");
        String result = FGElement.elementToString(linkElement);

        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }


    @Test
    public void testCreatePropertyElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <name type=\"string\">testmodule</name>");

        Document document = FGElement.createDocument();
        FGElement element = new FakeFGElement(document);

        // Act
        Element propertyElement = element.createPropertyElement("name", "string", "testmodule");

        String result = FGElement.elementToString(propertyElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    @Test
    public void testAddProperty() throws Exception {
        // Arrange
        Document document = FGElement.createDocument();
        Element rootElement = document.createElement("test");

        FGElement element = new FakeFGElement(document);

        // Act
       element.addProperty("name", "string", "testmodule");

        String result = FGElement.elementToString(rootElement);

        // Assert
        List<Integer> resultHashCodes = element.getPropertyList().stream().map(FGProperty::hashCode).collect(Collectors.toList());
        assertThat(resultHashCodes, contains((new FGProperty("name", "string", "testmodule")).hashCode()));
    }

    @Test
    public void testCreateElementNoProperties() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <fake/>");

        Document document = FGElement.createDocument();
        FakeFGElement element = new FakeFGElement(document);

        // Act
        Element resultElement = element.createElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    @Test
    public void testCreateElementWithProperty() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <fake>\n" +
                "<name type=\"type\">value</name>\n" +
                "</fake>");

        Document document = FGElement.createDocument();
        FakeFGElement element = new FakeFGElement(document);

        element.addProperty(new FGProperty("name", "type", "value"));

        // Act
        Element resultElement = element.createElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    @Test
    public void testCreateElementWithNullAndEmptyValueProperties() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <fake>\n" +
                "<name type=\"type\">value</name>\n" +
                "</fake>");

        Document document = FGElement.createDocument();
        FakeFGElement element = new FakeFGElement(document);

        element.addProperty(new FGProperty("name", "type", "value"));
        element.addProperty(new FGProperty("name", "type", null));
        element.addProperty(new FGProperty("name", "type", ""));

        // Act
        Element resultElement = element.createElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    class FakeFGElement extends FGElement {

        public FakeFGElement(Document document) {
            super(document);
        }

        @Override
        public String getClassName() {
            return "fake";
        }

        @Override
        public String getReferenceName() {
            return "fake";
        }

        @Override
        public String getName() {
            return "fake";
        }
    }
}