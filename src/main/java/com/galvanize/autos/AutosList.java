package com.galvanize.autos;

import java.util.ArrayList;
import java.util.List;

public class AutosList {
    List<Automobiles> autosList;

    public AutosList() {
        this.autosList = new ArrayList<>();
    }

    public AutosList(List<Automobiles> autosList) {
        this.autosList = autosList;
    }

    public Automobiles addAuto(Automobiles auto) {
        autosList.add(auto);
        return auto;
    }

    public List<Automobiles> getList() {
        return autosList;
    }
}
