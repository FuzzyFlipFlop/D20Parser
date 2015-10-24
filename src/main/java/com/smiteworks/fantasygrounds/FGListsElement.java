package com.smiteworks.fantasygrounds;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Created by Stimpyjc on 10/24/2015.
 */
public class FGListsElement extends FGElement {
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
        return "lists";
    }

    @Override
    public String getReferenceName() {
        return "lists";
    }

    @Override
    public String getName() {
        return "lists";
    }

    @Override
    public Element createElement() {
        return null;
    }
}
