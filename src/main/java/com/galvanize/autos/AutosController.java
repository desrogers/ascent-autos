package com.galvanize.autos;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Automobiles>> getAllAutos(@RequestParam(required = false) String color) {
        if (color != null) {
            List<Automobiles> autosList = autosService.getByColor(color);
            if (autosList.size() > 0) {
                return new ResponseEntity<>(autosList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Automobiles> autosList = autosService.getAllAutos();
        if (autosList.size() > 0) {
            return new ResponseEntity<>(autosList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
