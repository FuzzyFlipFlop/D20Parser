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
import static org.junit.Assert.*;

/**
 * Created by Stimpyjc on 10/25/2015.
 */
public class FGRootElementTest {

    @Test
    public void testCreateElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <root release=\"1|3.5E:15|CoreRPG:3\" version=\"3.1\">\n" +
                "<library/>\n" +
                "<lists/>\n" +
                "</root>");

        Document document = FGElement.createDocument();
        FGLibraryElement libraryElement = EasyMock.createMock(FGLibraryElement.class);
        FGListsElement listsElement = EasyMock.createMock(FGListsElement.class);
        List<FGElement> elementList = new ArrayList<>();
        FGRootElement element = new FGRootElement(document, libraryElement, listsElement, elementList);

        expect(libraryElement.createElement()).andReturn(document.createElement("library")).anyTimes();
        expect(listsElement.createElement()).andReturn(document.createElement("lists")).anyTimes();

        replay(libraryElement);
        replay(listsElement);

        // Act
        Element resultElement = element.createElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }
}