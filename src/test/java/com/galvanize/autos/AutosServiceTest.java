package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;

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
    void getAllAutos() {

    }

    @Test
    void addAuto() {
    }

    @Test
    void getByColor() {
    }

    @Test
    void getByMake() {
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