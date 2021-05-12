package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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

    @Test
    void testGetAutosByColor_IfColorExists() throws Exception {
        List<Automobiles> expected = new ArrayList<>();
        expected.add(testList.getList().get(0));
        when(autosService.getByColor(anyString())).thenReturn(expected);

        mockMvc.perform(get("/api/autos?color=color0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].color", is("color0")));
    }

    @Test
    void testGetAutosByColor_IfColorNotExists() throws Exception {
        List<Automobiles> expected = new ArrayList<>();
        when(autosService.getByColor(anyString())).thenReturn(expected);

        mockMvc.perform(get("/api/autos?color=color50"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAutosByMake_IfMakeExists() throws Exception {
        List<Automobiles> expected = new ArrayList<>();
        expected.add(testList.getList().get(0));
        when(autosService.getByMake(anyString())).thenReturn(expected);

        mockMvc.perform(get("/api/autos?make=Make0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].make", is("Make0")));
    }

    @Test
    void testGetAutosByMake_IfMakeNotExists() throws Exception {
        List<Automobiles> expected = new ArrayList<>();
        when(autosService.getByMake(anyString())).thenReturn(expected);

        mockMvc.perform(get("/api/autos?make=Make50"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAutosByColorAndMake_IfBothExist() throws Exception {
        List<Automobiles> expected = new ArrayList<>();
        expected.add(testList.getList().get(2));
        when(autosService.getByColorAndMake(anyString(), anyString())).thenReturn(expected);

        mockMvc.perform(get("/api/autos?color=color2&make=Make2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].color", is("color2")))
                .andExpect(jsonPath("$[0].make", is("Make2")));
    }

    @Test
    void testGetAutosByColorAndMake_IfBothNotExist() throws Exception {
        List<Automobiles> expected = new ArrayList<>();
        when(autosService.getByColorAndMake(anyString(), anyString())).thenReturn(expected);

        mockMvc.perform(get("/api/autos?color=color25&make=Make25"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAutosByColorAndMake_IfOnlyColorExists() throws Exception {
        List<Automobiles> expected = new ArrayList<>();
        when(autosService.getByColorAndMake(anyString(), anyString())).thenReturn(expected);

        mockMvc.perform(get("/api/autos?color=color2&make=Make25"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAutosByColorAndMake_IfOnlyMakeExists() throws Exception {
        List<Automobiles> expected = new ArrayList<>();
        when(autosService.getByColorAndMake(anyString(), anyString())).thenReturn(expected);

        mockMvc.perform(get("/api/autos?color=color25&make=Make2"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAutosByVin_IfVinExists() throws Exception {
        when(autosService.getByVin(anyString())).thenReturn(testList.getList().get(1));

        mockMvc.perform(get("/api/autos/vin1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vin", is("vin1")));
    }

    @Test
    void testGetAutosByVin_IfVinNotExists() throws Exception {
        when(autosService.getByVin(anyString())).thenReturn(null);

        mockMvc.perform(get("/api/autos/vin12"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testPostAuto_Valid() throws Exception {
        Automobiles expected = new Automobiles(2000, "Toyota", "Camry", "blue", "Me", "123abc");
        when(autosService.addAuto(any(Automobiles.class))).thenReturn(expected);

        mockMvc.perform(post("/api/autos")
                    .content("{\"year\":\"2000\",\"make\":\"Toyota\",\"model\":\"Camry\",\"color\":\"Blue\",\"owner\":\"Me\",\"vin\":\"123abc\"}")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vin", is("123abc")));
    }

    @Test
    void testPostAuto_Invalid() throws Exception {
        when(autosService.addAuto(any(Automobiles.class))).thenReturn(null);

        mockMvc.perform(post("/api/autos")
                .content("{\"year\":\"2000\",\"make\":\"Toyota\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateAuto_VinExists() throws Exception {
        Automobiles expected = new Automobiles(1997, "Toyota", "MR2", "green", "Me", "123abc");
        when(autosService.updateAuto(anyString(),any(UpdateAuto.class))).thenReturn(expected);

        mockMvc.perform(patch("/api/autos/123abc")
                    .content("{\"color\":\"green\",\"owner\":\"Me\"}")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color", is("green")))
                .andExpect(jsonPath("$.owner", is("Me")));
    }

    @Test
    void testUpdateAuto_VinDoesntExist() throws Exception {
        when(autosService.updateAuto(anyString(),any(UpdateAuto.class))).thenReturn(null);

        mockMvc.perform(patch("/api/autos/ANB")
            .content("{\"color\":\"green\",\"owner\":\"Me\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateAuto_BadRequest() throws Exception {
        when(autosService.updateAuto(anyString(),any(UpdateAuto.class))).thenThrow(new Exception());

        mockMvc.perform(patch("/api/autos/123abc")
                .content("{\"color\":\"green\",\"speed\":\"aet34e\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteAuto_VinExists() throws Exception {
        doNothing().when(autosService).deleteAuto(anyString());
        mockMvc.perform(delete("/api/autos/123abc"))
                .andExpect(status().isAccepted());
    }

    @Test
    void testDeleteAuto_VinNotExist() throws Exception {
        doThrow(new RuntimeException()).when(autosService).deleteAuto(anyString());
        mockMvc.perform(delete("/api/autos/123abc78962"))
                .andExpect(status().isNoContent());
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
