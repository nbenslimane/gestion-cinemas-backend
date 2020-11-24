package com.org.cinema.service;

import com.org.cinema.dao.*;
import com.org.cinema.model.Cinema;
import com.org.cinema.model.Place;
import com.org.cinema.model.Salle;
import com.org.cinema.model.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CinemaInitServiceImpl implements IinterfaceInitCinema {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public void initVilles() {
        Stream.of("Bizerte","Tunis","Sousse","Sfax").forEach(v->{
            Ville ville =new Ville();
            ville.setName(v);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
     villeRepository.findAll().forEach(v->{
         Stream.of("Collize","CinemaPalace","BizerteCinema","SfaxCinema").forEach(c->{
             Cinema cinema =new Cinema();
             cinema.setName(c);
             cinema.setNombreSalles((int) (3+Math.random()*7));
             cinemaRepository.save(cinema);
         });
     });
    }

    @Override
    public void initSalles() {
     cinemaRepository.findAll().forEach(c->{
         for (int i=0;i<c.getNombreSalles();i++){
             Salle salle=new Salle();
             salle.setName("Salle"+(i+1));
             salle.setCinema(c);
             salle.setNombrePlace((int) (20+Math.random()*10));
             salleRepository.save(salle);
         }
     });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(s->{
          for (int i=0;i<s.getNombrePlace();i++){
              Place place =new Place();
              place.setNumero(i+1);
              place.setSalle(s);
              placeRepository.save(place);
          }
        });

    }

    @Override
    public void initSeances() {

    }

    @Override
    public void initCategories() {

    }

    @Override
    public void initFilms() {

    }

    @Override
    public void initProjections() {

    }

    @Override
    public void initTickets() {

    }
}
