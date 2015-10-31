package com.smiteworks.fantasygrounds;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Created by Stimpyjc on 10/24/2015.
 */
public class FGRootElement extends FGElement {
    private static final String REFERENCE_NAME = "root";
    private static final String CLASS_NAME = "root";
    private static final String NAME = "root";
    private static final String VERSION = "3.1";
    private static final String RELEASE = "1|3.5E:15|CoreRPG:3";

    private FGLibraryElement library;
    private FGListsElement list;
    private List<FGElement> elementList;

    public FGRootElement(Document document, FGLibraryElement library, FGListsElement list, List<FGElement> elementList) {
        super(document);
        this.library = library;
        this.list = list;
        this.elementList = elementList;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public String getReferenceName() {
        return REFERENCE_NAME;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Element createElement() {
        Element element = super.createElement();

        element.setAttribute("version", VERSION);
        element.setAttribute("release", RELEASE);

        element.appendChild(library.createElement());

        element.appendChild(list.createElement());

        for (FGElement item : elementList) {
            element.appendChild(item.createElement());
        }

        return element;
    }
}
