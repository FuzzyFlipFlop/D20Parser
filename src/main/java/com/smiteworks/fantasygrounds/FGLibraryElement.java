package com.smiteworks.fantasygrounds;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Stimpyjc on 10/24/2015.
 */
public class FGLibraryElement extends FGElement {
    private static final String ELEMENT_NAME = "library";
    private static final String ENTRIES_ELEMENT_NAME = "entries";

    private static final String LINK_NAME = "librarylink";
    private static final String LINK_TYPE = "windowreference";

    private String name;
    private String category;
    private FGListsElement listsElement;

    public FGLibraryElement(Document document, String name, String category, FGListsElement listsElement) {
        super(document);

        this.name = name;
        this.category = category;
        this.listsElement = listsElement;
    }

    @Override
    public String getClassName() {
        return "library";
    }

    @Override
    public String getReferenceName() {
        return "library";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Element createElement() {
        Element element = getDocument().createElement(ELEMENT_NAME);

        element.appendChild(createModuleElement());

        return element;
    }

    protected Element createModuleElement() {
        String lowerName = name.replaceAll("\\s+", "").toLowerCase();
        Element element = getDocument().createElement(lowerName);
        element.setAttribute("static", "true");

        element.appendChild(createPropertyElement("category", "string", category));
        element.appendChild(createPropertyElement("name", "string", lowerName));

        element.appendChild(createEntriesElement());

        return element;
    }

    protected Element createEntriesElement() {
        Element element = getDocument().createElement(ENTRIES_ELEMENT_NAME);

        for (FGListElement listElement : listsElement.getListElements()) {
            element.appendChild(createlinkElement(listElement));
        }

        return element;
    }

    protected Element createlinkElement(FGListElement element) {
        Element linkElement = getDocument().createElement(element.getReferenceName());

        linkElement.appendChild(createLinkElement(LINK_NAME, LINK_TYPE, element.getClassName(), String.format("%s.%s@%s", listsElement.getReferenceName(), element.getReferenceName(), getName())));
        linkElement.appendChild(createPropertyElement("name", "string", element.getName()));


        return linkElement;
    }
}
