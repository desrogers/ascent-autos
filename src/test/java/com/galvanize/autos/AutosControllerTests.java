package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AutosController.class)
public class AutosControllerTests {
    AutosList testList;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutosService autosService;

    @BeforeEach
    void setUp() {
        AutosService autosService = new AutosService();
        AutosController autosController = new AutosController(autosService);
        testList = new AutosList();
        for (int i = 0; i < 5; i++) {
            testList.addAuto(new Automobiles(2000 + i, "Make" + i, "Model" + i, "color" + i, "Owner" + i, "vin" + i));
        }
    }

    @Test
    void testGetAllAutos_NoExistingAutos() throws Exception {
        when(autosService.getAllAutos()).thenReturn(new AutosList().getList());

        mockMvc.perform(get("/api/autos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAllAutos_HasExistingAutos() throws Exception {

        when(autosService.getAllAutos()).thenReturn(testList.getList());

        mockMvc.perform(get("/api/autos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    /*
        GET /api/autos
            /api/autos - Returns list of all autos in the database
            /api/autos - 204 if no autos exist in db

            /api/autos?color={var} - returns all cars with var color
            /api/autos?color={var} - 204 if no autos exist in db with var color

            /api/autos?make={var} - returns all cars with var make
            /api/autos?make={var} - 204 if no autos exist in db with var make

            api/autos?color={var1}&make={var2} - returns all cars with var1 color and var2 make
            api/autos?color={var1}&make={var2} - 204 if no autos exist in db with var1 color and var2 make

        POST /api/autos
            /api/autos - adds an auto to the database and returns the car's details
            /api/autos - 400 bad request

        GET /api/autos/{vin}
            /api/autos/{vin} - returns the car's detail with the matching vin
            /api/autos/{vin} - 204 if no car with matching vin exists


        PATCH /api/autos/{vin}
            /api/autos/{vin} - updates and returns car's detail with new information
            /api/autos/{vin} - 204 if no car with matching vin exists
            /api/autos/{vin} - 400 bad request returns error message

        DELETE /api/autos/{vin}
            /api/autos/{vin} - 202 if car successfully deleted
            /api/autos/{vin} - 204 if no car with matching vin exists

     */



}
