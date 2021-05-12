package com.galvanize.autos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutosRepository extends JpaRepository<Automobiles, Long> {
    List<Automobiles> findByColor(String color);
    List<Automobiles> findByMake(String make);
}
