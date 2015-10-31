package com.smiteworks.fantasygrounds.npc;

import com.smiteworks.fantasygrounds.FGElement;
import com.smiteworks.fantasygrounds.npc.spellset.FGSpellSetElement;
import org.easymock.EasyMock;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlunit.matchers.CompareMatcher;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Stimpyjc on 10/25/2015.
 */
public class FGNpcElementTest {

    @Test
    public void testCreateElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <referenceName>\n" +
                "<spellset/>\n" +
                "</referenceName>");

        Document document = FGElement.createDocument();
        FGSpellSetElement spellSetElement = EasyMock.createMock(FGSpellSetElement.class);
        FGNpcElement element = new FGNpcElement(document, "name", "referenceName", spellSetElement);

        expect(spellSetElement.createElement()).andReturn(document.createElement("spellset")).anyTimes();

        replay(spellSetElement);

        // Act
        Element resultElement = element.createElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }
}