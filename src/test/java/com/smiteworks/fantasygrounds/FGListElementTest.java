package com.smiteworks.fantasygrounds;

import org.easymock.EasyMock;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlunit.matchers.CompareMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Stimpyjc on 10/25/2015.
 */
public class FGListElementTest {

    @Test
    public void testCreateElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <listReferenceName>\n" +
                "<name type=\"string\">listName</name>\n" +
                "<index/>\n" +
                "</listReferenceName>");

        Document document = FGElement.createDocument();
        FGLibraryElement libraryElement = EasyMock.createMock(FGLibraryElement.class);
        List<FGElement> elementList = new ArrayList<>();
        FGListElement element = new FGListElement(document, "listName", "listReferenceName", elementList, libraryElement);

        replay(libraryElement);

        // Act
        Element resultElement = element.createElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    @Test
    public void testCreateIndexElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <index/>");

        Document document = FGElement.createDocument();
        FGLibraryElement libraryElement = EasyMock.createMock(FGLibraryElement.class);
        List<FGElement> elementList = new ArrayList<>();
        FGListElement element = new FGListElement(document, "listName", "listReferenceName", elementList, libraryElement);

        replay(libraryElement);

        // Act
        Element resultElement = element.createIndexElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    @Test
    public void testCreateIndexLinkElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <referenceName>\n" +
                "<listlink type=\"windowreference\">\n" +
                "<class>className</class>\n" +
                "<recordname>listReferenceName.referenceName@libraryName</recordname>\n" +
                "</listlink>\n" +
                "<name type=\"string\">name</name>\n" +
                "</referenceName>");

        Document document = FGElement.createDocument();
        FGLibraryElement libraryElement = EasyMock.createMock(FGLibraryElement.class);
        FGElement fgElement = EasyMock.createMock(FGElement.class);
        List<FGElement> elementList = new ArrayList<>();
        FGListElement element = new FGListElement(document, "listName", "listReferenceName", elementList, libraryElement);

        expect(fgElement.getReferenceName()).andReturn("referenceName").anyTimes();
        expect(fgElement.getClassName()).andReturn("className").anyTimes();
        expect(fgElement.getName()).andReturn("name").anyTimes();

        expect(libraryElement.getName()).andReturn("libraryName").anyTimes();

        replay(libraryElement);
        replay(fgElement);

        // Act
        Element resultElement = element.createIndexLinkElement(fgElement);

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }
}