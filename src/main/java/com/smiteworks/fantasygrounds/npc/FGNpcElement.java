package com.smiteworks.fantasygrounds.npc;

import com.smiteworks.fantasygrounds.FGElement;
import com.smiteworks.fantasygrounds.npc.spellset.FGSpellSetElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Stimpyjc on 10/25/2015.
 */
public class FGNpcElement extends FGElement {
    private static final String CLASS_NAME = "npcItem";

    private String referenceName;
    private String name;

    private FGSpellSetElement spellSetElement;

    public FGNpcElement(Document document, String name, String referenceName, FGSpellSetElement spellSetElement) {
        super(document);

        this.name = name;
        this.referenceName = referenceName;
        this.spellSetElement = spellSetElement;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public String getReferenceName() {
        return referenceName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Element createElement() {
        Element element = super.createElement();

        element.appendChild(spellSetElement.createElement());

        return element;
    }
}
