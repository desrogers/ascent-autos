package com.galvanize.autos;

import java.util.List;

public class AutosService {

    AutosList autosList = new AutosList();

    AutosService() {

    }

    public List<Automobiles> getAllAutos() {
        return autosList.getList();
    }

    public void addAuto(Automobiles auto) {
        autosList.addAuto(auto);
    }
}
