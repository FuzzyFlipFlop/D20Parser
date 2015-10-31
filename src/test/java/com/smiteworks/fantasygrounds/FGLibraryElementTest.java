package com.smiteworks.fantasygrounds;

import org.easymock.EasyMock;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlunit.matchers.CompareMatcher;

import java.util.ArrayList;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Stimpyjc on 10/25/2015.
 */
public class FGLibraryElementTest {
    @Test
    public void testCreateElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <library>\n" +
                "<libraryname static=\"true\">\n" +
                "<category type=\"string\">libraryCategory</category>\n" +
                "<name type=\"string\">libraryname</name>\n" +
                "<entries/>\n" +
                "</libraryname>\n" +
                "</library>");

        Document document = FGElement.createDocument();
        FGListsElement listsElement = EasyMock.createMock(FGListsElement.class);
        FGLibraryElement element = new FGLibraryElement(document, "libraryName", "libraryCategory", listsElement);

        expect(listsElement.getListElements()).andReturn(new ArrayList<>()).anyTimes();

        replay(listsElement);

        // Act
        Element resultElement = element.createElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    @Test
    public void testCreateModuleElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <libraryname static=\"true\">\n" +
                "<category type=\"string\">libraryCategory</category>\n" +
                "<name type=\"string\">libraryname</name>\n" +
                "<entries/>\n" +
                "</libraryname>");

        Document document = FGElement.createDocument();
        FGListsElement listsElement = EasyMock.createMock(FGListsElement.class);
        FGLibraryElement element = new FGLibraryElement(document, "libraryName", "libraryCategory", listsElement);

        expect(listsElement.getListElements()).andReturn(new ArrayList<>()).anyTimes();

        replay(listsElement);

        // Act
        Element moduleElement = element.createModuleElement();

        String result = FGElement.elementToString(moduleElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    @Test
    public void testCreateEntriesElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <entries/>");

        Document document = FGElement.createDocument();
        FGListsElement listsElement = EasyMock.createMock(FGListsElement.class);
        FGLibraryElement element = new FGLibraryElement(document, "libraryName", "libraryCategory", listsElement);

        expect(listsElement.getListElements()).andReturn(new ArrayList<>()).anyTimes();

        replay(listsElement);

        // Act
        Element entriesElement = element.createEntriesElement();

        String result = FGElement.elementToString(entriesElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    @Test
    public void testCreateLinkElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <referenceName>\n" +
                "<librarylink type=\"windowreference\">\n" +
                "<class>className</class>\n" +
                "<recordname>listsReferenceName.referenceName@libraryName</recordname>\n" +
                "</librarylink>\n" +
                "<name type=\"string\">name</name>\n" +
                "</referenceName>");

        Document document = FGElement.createDocument();
        FGListsElement listsElement = EasyMock.createMock(FGListsElement.class);
        FGListElement listElement = EasyMock.createMock(FGListElement.class);
        FGLibraryElement element = new FGLibraryElement(document, "libraryName", "libraryCategory", listsElement);

        expect(listElement.getReferenceName()).andReturn("referenceName").anyTimes();
        expect(listElement.getClassName()).andReturn("className").anyTimes();
        expect(listElement.getName()).andReturn("name").anyTimes();

        expect(listsElement.getReferenceName()).andReturn("listsReferenceName").anyTimes();

        replay(listsElement);
        replay(listElement);

        // Act
        Element linkElement = element.createLinkElement(listElement);

        String result = FGElement.elementToString(linkElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }
}