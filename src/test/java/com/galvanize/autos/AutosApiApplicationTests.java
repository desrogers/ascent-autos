package com.galvanize.autos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AutosApiApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    AutosRepository autosRepository;

    Random r;
    List<Automobiles> testAutos;
    @BeforeEach
    void setUp(){
        r = new Random();
        testAutos = new ArrayList<>();

        String[] colors = {"red", "blue", "green", "yellow"};

        for (int i = 0; i < 30; i++) {
            if (i % 3 == 0) {
                testAutos.add(new Automobiles(2000, "Toyota", "Camry", colors[r.nextInt(4)], "Bob", "ABC" + (i * 5)));
            } else if (i % 2 == 0) {
                testAutos.add(new Automobiles(2020, "Ford", "Pinto", colors[r.nextInt(4)], "Rob", "XYZ" + (i * 7)));
            } else {
                testAutos.add(new Automobiles(1995, "BMW", "i3", colors[r.nextInt(4)], "Tim", "MNO" + (i * 11)));
            }
        }
        autosRepository.saveAll(testAutos);
    }

    @AfterEach
    void tearDown(){
        autosRepository.deleteAll();
    }

	@Test
	void contextLoads() {
	}

	@Test
    void getAutos_exists_returnsList() {
        ResponseEntity<List> response = testRestTemplate.getForEntity("/api/autos", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isEmpty()).isFalse();
    }

    @Test
    void getAutos_notExists_returnsEmpty() {
        autosRepository.deleteAll();
        ResponseEntity<List> response = testRestTemplate.getForEntity("/api/autos", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void getByColor_exists_returnsList() {
        int randomNumber = r.nextInt(30);
        String color = testAutos.get(randomNumber).getColor();
        ResponseEntity<List> response = testRestTemplate.getForEntity(
                String.format("/api/autos?color=%s", color), List.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isEmpty()).isFalse();
        assertThat(response.getBody().size()).isGreaterThanOrEqualTo(1);
    }


    @Test
    void getByMake_exists_returnsList() {
        int randomNumber = r.nextInt(30);
        String make = testAutos.get(randomNumber).getMake();
        ResponseEntity<List> response = testRestTemplate.getForEntity(
                String.format("/api/autos?make=%s", make), List.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isEmpty()).isFalse();
        assertThat(response.getBody().size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void getByMake_notExists_returnsNull() {
        ResponseEntity<List> response = testRestTemplate.getForEntity("/api/autos?make=orange", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void getByColorAndMake_exists_returnsList() {
        int randomNumber = r.nextInt(30);
        String color = testAutos.get(randomNumber).getColor();
        String make = testAutos.get(randomNumber).getMake();
        ResponseEntity<List> response = testRestTemplate.getForEntity(
                String.format("/api/autos?color=%s&make=%s", color, make), List.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isEmpty()).isFalse();
        assertThat(response.getBody().size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void getByColorAndMake_notExists_returnsEmpty() {
        ResponseEntity<List> response = testRestTemplate.getForEntity("/api/autos?color=orange&make=corolla", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void postAuto_valid_returnsAuto() {
        Automobiles auto = new Automobiles(1995, "BMW", "i3", "green", "Tim", "MNO951");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Automobiles> request = new HttpEntity<>(auto, headers);
        ResponseEntity<Automobiles> response = testRestTemplate.postForEntity("/api/autos", request, Automobiles.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getVin()).isEqualTo(auto.getVin());
    }

    @Test
    void postAuto_invalid_returnsEmpty() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<>("{\"speed\":\"green\",\"type\":\"Me\"}", headers);

        ResponseEntity<Automobiles> response = testRestTemplate.postForEntity("/api/autos", request, Automobiles.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertNull(response.getBody());

    }
}
