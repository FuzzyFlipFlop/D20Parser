package com.smiteworks.fantasygrounds.npc.spellset;

import com.smiteworks.fantasygrounds.FGElement;
import org.w3c.dom.Document;

/**
 * Created by Stimpyjc on 10/25/2015.
 */
public class FGSpellSetElement extends FGElement {

    private String concentrationCheck;
    private FGSpellDCElement spellDCElement;


    public FGSpellSetElement(Document document) {
        super(document);
    }

    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public String getReferenceName() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
