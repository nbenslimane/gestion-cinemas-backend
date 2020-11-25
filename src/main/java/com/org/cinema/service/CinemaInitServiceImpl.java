package com.org.cinema.service;

import com.org.cinema.dao.*;
import com.org.cinema.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
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
         Stream.of("Cinema ABC","Cinéma Théâtre le RIO","Cinéma Le Colisée","Cinéma Amilcar Hannibal").forEach(c->{
             Cinema cinema =new Cinema();
             cinema.setName(c);
             cinema.setNombreSalles((int) (3+Math.random()*7));
             cinema.setVille(v);
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
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
     Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(se->{
         Seance seance =new Seance();
         try {
             seance.setHeureDebut(dateFormat.parse(se));
             seanceRepository.save(seance);
         } catch (ParseException e) {
             e.printStackTrace();
         }

     });
    }

    @Override
    public void initCategories() {
     Stream.of("Histoire","Drama","Action","Fiction").forEach(ca->{
         Categorie categorie =new Categorie();
         categorie.setName(ca);
         categorieRepository.save(categorie);
     });
    }

    @Override
    public void initFilms() {
        double[] durees= new double[]{1,1.5,2,2.5,3};
        List<Categorie> categories=categorieRepository.findAll();
      Stream.of("Game of Thrones","Ace of Age","Spiderman","Paranormal Activity", "Green Book", "le seigneur des anneaux")
              .forEach(f->{
          Film film =new Film();
          film.setTitre(f);
          film.setDuree(durees[new Random().nextInt(durees.length)]);
          film.setPhotoName(f.replaceAll(" ",""));
          film.setCategorie(categories.get(new Random().nextInt(categories.size())));
          filmRepository.save(film);
      });
    }

    @Override
    public void initProjections() {
        double prix []= new double[]{5,10,15,20};
        villeRepository.findAll().forEach(v->{
            v.getCinemas().forEach(c->{
                c.getSalles().forEach(s->{
                 filmRepository.findAll().forEach(f->{
                     seanceRepository.findAll().forEach(se->{
                      Projection projection=new Projection();
                      projection.setDateProjection(new Date());
                      projection.setFilm(f);
                      projection.setPrix(prix[new Random().nextInt(prix.length)]);
                      projection.setSalle(s);
                      projection.setSeance(se);
                      projectionRepository.save(projection);
                       });
                    });
                });
            });
        });

    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(plc->{
                Ticket ticket=new Ticket();
                ticket.setPlace(plc);
                ticket.setPrix(p.getPrix());
                ticket.setProjection(p);
                ticket.setReserved(false);
                ticketRepository.save(ticket);
            });
        });
    }
}
