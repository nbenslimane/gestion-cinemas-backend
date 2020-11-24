package com.org.cinema.dao;

import com.org.cinema.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville,Long> {
}
