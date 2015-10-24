package com.smiteworks.fantasygrounds;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Created by Stimpyjc on 10/24/2015.
 */
public class FGListElement extends FGElement {
    private static final String LINK_NAME = "listlink";
    private static final String LINK_TYPE = "windowreference";

    private String referenceName;
    private String name;

    private List<FGElement> elementList;
    private FGLibraryElement libraryElement;

    public FGListElement(Document document, String name, String referenceName, List<FGElement> elementList, FGLibraryElement libraryElement) {
        super(document);

        this.name = name;
        this.referenceName = referenceName;

        this.elementList = elementList;
        this.libraryElement = libraryElement;
    }

    public String getClassName() {
        return "referenceindexsorted";
    }

    public String getReferenceName() {
        return referenceName;
    }

    public String getName() {
        return name;
    }

    @Override
    public Element createElement() {
        Element element = getDocument().createElement(referenceName);

        element.appendChild(createPropertyElement("name", "string", name));
        element.appendChild(createIndexElement());

        return element;
    }

    protected Element createIndexElement() {
        Element element = getDocument().createElement("index");

        for (FGElement listElement : elementList) {
            element.appendChild(createIndexElement(listElement));
        }

        return element;
    }

    protected Element createIndexElement(FGElement element) {
        Element indexElement = getDocument().createElement(element.getReferenceName());

        indexElement.appendChild(createLinkElement(LINK_NAME, LINK_TYPE, element.getClassName(), String.format("%s.%s@%s", referenceName, element.getReferenceName(), libraryElement.getName())));
        indexElement.appendChild(createPropertyElement("name", "string", element.getName()));

        return indexElement;
    }
}
