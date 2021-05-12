package com.galvanize.autos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutosRepository extends JpaRepository<Automobiles, Long> {
    List<Automobiles> findByColor(String color);
    List<Automobiles> findByMake(String make);
    List<Automobiles> findByColorAndMake(String color, String make);
    Optional<Automobiles> findByVin(String vin);
}
