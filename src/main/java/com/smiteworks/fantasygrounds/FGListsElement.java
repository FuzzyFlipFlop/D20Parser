package com.smiteworks.fantasygrounds;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Created by Stimpyjc on 10/24/2015.
 */
public class FGListsElement extends FGElement {
    private static final String REFERENCE_NAME = "lists";
    private static final String CLASS_NAME = "lists";
    private static final String NAME = "lists";

    private List<FGListElement> listElements;

    public FGListsElement(Document document, List<FGListElement> listElements) {
        super(document);

        this.listElements = listElements;
    }

    public List<FGListElement> getListElements() {
        return listElements;
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
        return element;
    }
}
