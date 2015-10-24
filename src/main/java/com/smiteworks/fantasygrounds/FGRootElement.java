package com.smiteworks.fantasygrounds;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Stimpyjc on 10/24/2015.
 */
public class FGRootElement extends FGElement {
    private static final String ELEMENT_NAME = "root";
    private static final String VERSION = "3.1";
    private static final String RELEASE = "1|3.5E:15|CoreRPG:3";

    private FGLibraryElement library;
    private FGListsElement list;
    private FGNpcElement npc;

    public FGRootElement(FGLibraryElement library, FGListsElement list, FGNpcElement npc) {
        this.library = library;
        this.list = list;
        this.npc = npc;
    }

    public Element createElement(Document document) {
        Element element = document.createElement(ELEMENT_NAME);

        element.setAttribute("version", VERSION);
        element.setAttribute("release", RELEASE);

        Element libraryElement = library.createElement(document);
        element.appendChild(libraryElement);

        Element listElement = list.createElement(document);
        element.appendChild(listElement);

        Element npcElement = npc.createElement(document);
        element.appendChild(npcElement);

        return element;
    }
}
