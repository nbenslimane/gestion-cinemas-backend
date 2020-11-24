package com.org.cinema;

import com.org.cinema.service.IinterfaceInitCinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
	@Autowired
	private IinterfaceInitCinema iinterfaceInitCinema;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		iinterfaceInitCinema.initVilles();
		iinterfaceInitCinema.initCinemas();
		iinterfaceInitCinema.initSalles();
		iinterfaceInitCinema.initPlaces();
		iinterfaceInitCinema.initSeances();
		iinterfaceInitCinema.initCategories();
		iinterfaceInitCinema.initFilms();
		iinterfaceInitCinema.initProjections();
		iinterfaceInitCinema.initTickets();
	}
}
