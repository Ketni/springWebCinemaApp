package com.example.demo.service;


import com.example.demo.model.Country;
import com.example.demo.model.Film;
import com.example.demo.model.Genre;
import com.example.demo.model.Producer;
import com.example.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CountryService countryService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private ProducerService producerService;

    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    @Override
    public void saveFilm(Film film, String[] countryName, String[] genreName, String[] producerName){
        Set<Country> countries = new HashSet<>();
        Set<Genre> genres = new HashSet<>();
        Set<Producer> producers = new HashSet<>();
        for (String name : countryName
        ) {
            if (countryService.getCountryByName(name)==null)
                countryService.saveCountry(new Country(name));
            Country country = countryService.getCountryByName(name);
            countries.add(country);
        }
        for (String name : genreName
        ) {
            if (genreService.getGenreByName(name)==null)
                genreService.saveGenre(new Genre(name));
            Genre genre = genreService.getGenreByName(name);
            genres.add(genre);
        }
        for (String name : producerName
        ){
            if (producerService.getProducerByName(name)==null)
                producerService.saveProducer(new Producer(name));
            Producer producer = producerService.getProducerByName(name);
            producers.add(producer);
        }
        film.setCountries(countries);
        film.setGenres(genres);
        film.setProducers(producers);
        filmRepository.save(film);
    }

    @Override
    public void deleteFilmById(long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public List<Film> findAllByOrderByNameAsc() {
        return filmRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Film> findAllByOrderByNameDesc() {
        return filmRepository.findAllByOrderByNameDesc();


    }

    @Override
    public List<Film> searchFilm(String searchString) {
        return filmRepository.findAllByName(searchString);
    }
}
