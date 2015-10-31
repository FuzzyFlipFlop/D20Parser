package com.smiteworks.fantasygrounds;

import org.easymock.EasyMock;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlunit.matchers.CompareMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.replay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Stimpyjc on 10/25/2015.
 */
public class FGListsElementTest {

    @Test
    public void testCreateElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <lists/>");

        Document document = FGElement.createDocument();
        List<FGListElement> listElements = new ArrayList<>();
        FGListsElement element = new FGListsElement(document, listElements);

        // Act
        Element resultElement = element.createElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }
}