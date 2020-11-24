package com.org.cinema.dao;

import com.org.cinema.model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeanceRepository extends JpaRepository<Seance,Long> {
}
