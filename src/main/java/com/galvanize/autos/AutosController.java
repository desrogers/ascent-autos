package com.galvanize.autos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutosController {
    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService =  autosService;
    }

    @GetMapping
    public ResponseEntity<List<Automobiles>> getAllAutos() {
        List<Automobiles> autosList = autosService.getAllAutos();
        if (autosList.size() > 0) {
            return new ResponseEntity<>(autosList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
