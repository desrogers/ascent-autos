package com.galvanize.autos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<Automobiles> autos = autosRepository.findByColor(color);
        if (autos.size() > 0) return autos;
        return null;
    }

    public List<Automobiles> getByMake(String make) {
        List<Automobiles> autos = autosRepository.findByMake(make);
        if (autos.size() > 0) return autos;
        return null;
    }

    public List<Automobiles> getByColorAndMake(String color, String make) {
        List<Automobiles> autos = autosRepository.findByColorAndMake(color, make);
        if (autos.size() > 0) return autos;
        return null;
    }

    public Automobiles getByVin(String vin) {
        return autosRepository.findByVin(vin).orElse(null);
    }

    public Automobiles updateAuto(String vin, UpdateAuto info) throws Exception {
        Optional<Automobiles> oAuto = autosRepository.findByVin(vin);
        if (oAuto.isPresent()) {
            oAuto.get().setColor(info.getColor());
            oAuto.get().setOwner(info.getOwner());
            return autosRepository.save(oAuto.get());
        }
        return null;
    }

    public void deleteAuto(String vin) {
        Optional<Automobiles> oAuto = autosRepository.findByVin(vin);
        if (oAuto.isPresent()) {
            autosRepository.delete(oAuto.get());
        } else {
            throw new RuntimeException();
        }
    }
}
