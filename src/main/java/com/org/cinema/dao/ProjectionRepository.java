package com.org.cinema.dao;

import com.org.cinema.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionRepository extends JpaRepository<Projection,Long> {
}
