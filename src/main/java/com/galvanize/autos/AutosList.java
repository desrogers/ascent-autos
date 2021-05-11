package com.galvanize.autos;

import java.util.ArrayList;
import java.util.List;

public class AutosList {
    List<Automobiles> autosList = new ArrayList<>();

    public AutosList() {}

    public Automobiles addAuto(Automobiles auto) {
        autosList.add(auto);
        return auto;
    }

    public List<Automobiles> getList() {
        return autosList;
    }
}
