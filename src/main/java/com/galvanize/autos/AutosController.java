package com.galvanize.autos;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutosController {
    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService =  autosService;
    }

    @GetMapping
    public ResponseEntity<List<Automobiles>> getAllAutos(@RequestParam(required = false) String color, @RequestParam(required = false) String make) {
        List<Automobiles> autosList;

        if (color != null && make != null) {
            autosList = autosService.getByColorAndMake(color, make);
        } else if (color != null) {
            autosList = autosService.getByColor(color);
        } else if (make != null) {
            autosList = autosService.getByMake(make);
        } else {
            autosList = autosService.getAllAutos();
        }

        if (autosList.size() > 0) {
            return new ResponseEntity<>(autosList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Automobiles> getAutoByVin(@PathVariable String vin) {
        Automobiles auto = autosService.getByVin(vin);
        if (auto != null) {
            return new ResponseEntity<>(auto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Automobiles> postAuto(@RequestBody Automobiles auto) {
        Automobiles newAuto = autosService.addAuto(auto);
        if (newAuto != null) {
            return new ResponseEntity<>(newAuto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{vin}")
    public ResponseEntity<Automobiles> updateAuto(@RequestBody UpdateAuto info, @PathVariable String vin) {
        Automobiles auto;
        try {
            auto = autosService.updateAuto(vin, info);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (auto != null) {
            return new ResponseEntity<>(auto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
