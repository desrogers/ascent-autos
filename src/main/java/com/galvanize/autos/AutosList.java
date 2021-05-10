package com.galvanize.autos;

import java.util.ArrayList;
import java.util.List;

public class AutosList {
    List<Automobiles> autosList = new ArrayList<>();

    public AutosList() {}

    public void addAuto(Automobiles auto) {
        autosList.add(auto);
    }

    public List<Automobiles> getList() {
        return autosList;
    }
}
