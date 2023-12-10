package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class MetalsDataObject {

    public String[] metalsName = new String[]{"Steel", "Iron", "Tin", "Pewter", "Bronze", "Copper", "Zinc", "Brass"};
    public List<Metals> metals = new ArrayList<>();
    public MetalsDataObject() {
        for (int i = 0; i < 8; i++) {
            metals.add(new Metals(metalsName[i]));
        }
    }

}
