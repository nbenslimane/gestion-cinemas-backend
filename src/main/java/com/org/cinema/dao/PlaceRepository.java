package com.org.cinema.dao;

import com.org.cinema.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Long> {
}
