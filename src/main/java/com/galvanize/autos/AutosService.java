package com.galvanize.autos;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutosService{
    AutosRepository autosRepository;

    AutosList autosList = new AutosList();

    AutosService(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    AutosService() {

    }

    public List<Automobiles> getAllAutos() {
        return new AutosList(autosRepository.findAll()).getList();
    }

    public Automobiles addAuto(Automobiles auto) {
        return autosRepository.save(auto);
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

    public void deleteAuto(String anyString) {
    }
}
