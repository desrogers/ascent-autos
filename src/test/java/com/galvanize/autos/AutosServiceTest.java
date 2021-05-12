package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutosServiceTest {

    AutosService autosService;

    @Mock
    AutosRepository autosRepository;

    @BeforeEach
    void setUp() {
        autosService = new AutosService(autosRepository);
    }

    @Test
    void getAllAutos_empty() {
        List<Automobiles> expected = new ArrayList<>();
        List<Automobiles> actual = autosService.getAllAutos();
        assertEquals(expected, actual, "Getting a list of all automobiles when first initializing should return an empty list");
    }

    @Test
    void getAllAutos() {
        Automobiles auto = new Automobiles(2025, "Make", "Model", "color", "Owner", "ABJD");
        List<Automobiles> expected = new ArrayList<>();
        expected.add(auto);
        when(autosRepository.findAll()).thenReturn(expected);
        List<Automobiles> actual = autosService.getAllAutos();
        assertEquals(expected, actual, "Getting all autos returns a list of all autos");
    }

    @Test
    void addAuto() {
        Automobiles auto = new Automobiles(2025, "Make", "Model", "color", "Owner", "ABJD");
        when(autosRepository.save(any(Automobiles.class))).thenReturn(auto);
        Automobiles actual = autosService.addAuto(auto);
        assertEquals(auto, actual, "Adding the auto will return the auto");
    }

    @Test
    void getByColor() {
        Automobiles auto = new Automobiles(2025, "Make", "Model", "color", "Owner", "ABJD");
        List<Automobiles> expected = new ArrayList<>();
        expected.add(auto);
        when(autosRepository.findByColor(anyString())).thenReturn(expected);
        List<Automobiles> actual = autosService.getByColor("color");
        assertThat(actual).isNotNull();
        assertThat(actual.size() > 0).isTrue();
    }

    @Test
    void getByMake() {
        Automobiles auto = new Automobiles(2025, "Make", "Model", "color", "Owner", "ABJD");
        List<Automobiles> expected = new ArrayList<>();
        expected.add(auto);
        when(autosRepository.findByMake(anyString())).thenReturn(expected);
        List<Automobiles> actual = autosService.getByMake("Make");
        assertThat(actual).isNotNull();
        assertThat(actual.size() > 0).isTrue();
    }

    @Test
    void getByColorAndMake() {
    }

    @Test
    void getByVin() {
    }

    @Test
    void updateAuto() {
    }

    @Test
    void deleteAuto() {
    }
}