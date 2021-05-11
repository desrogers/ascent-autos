package com.galvanize.autos;

import java.util.List;

public class AutosService {

    AutosList autosList = new AutosList();

    AutosService() {

    }

    public List<Automobiles> getAllAutos() {
        return autosList.getList();
    }

    public Automobiles addAuto(Automobiles auto) {
        return autosList.addAuto(auto);
    }

    public List<Automobiles> getByColor(String color) {
        return null;
    }

    public List<Automobiles> getByMake(String make) {
        return null;
    }

    public List<Automobiles> getByColorAndMake(String color, String make) {
        return null;
    }

    public Automobiles getByVin(String vin) {
        return null;
    }

    public Automobiles updateAuto(String vin, UpdateAuto info) throws Exception{
        return null;
    }
}
