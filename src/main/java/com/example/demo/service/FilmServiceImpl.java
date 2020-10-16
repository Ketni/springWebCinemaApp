package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public void saveFilm(Film film, String[] countryName, String[] genreName, String[] producerName) {
        Set<Country> countries = new HashSet<>();
        Set<Genre> genres = new HashSet<>();
        Set<Producer> producers = new HashSet<>();
        for (String name : countryName
        ) {
            if (countryService.getCountryByName(name) == null)
                countryService.saveCountry(new Country(name));
            Country country = countryService.getCountryByName(name);
            countries.add(country);
        }
        for (String name : genreName
        ) {
            if (genreService.getGenreByName(name) == null)
                genreService.saveGenre(new Genre(name));
            Genre genre = genreService.getGenreByName(name);
            genres.add(genre);
        }
        for (String name : producerName
        ) {
            if (producerService.getProducerByName(name) == null)
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
        Film film = filmRepository.findById(id).get();
        Set<User> users = film.getUsers();
        for (User user : users
        ) {
            user.getFilms().clear();
        }
        film.getUsers().clear();
        film.getCountries().clear();
        film.getGenres().clear();
        film.getProducers().clear();
        filmRepository.deleteById(id);
    }


    @Override
    public List<Film> sortAndSearch(String searchString, boolean order) {
        List<Film> filmList = searchFilm(searchString);
        List<Film> sortedFilmList = filmList.stream().sorted((Comparator.comparing(Film::getName))).collect(Collectors.toList());
        if (!order)
            Collections.reverse(sortedFilmList);
        return sortedFilmList;
    }

    @Override
    public List<Film> sortAndSearchByLength(String searchString, boolean order) {
        List<Film> filmList = searchFilm(searchString);
        List<Film> sortedList = filmList.stream().sorted(Comparator.comparing(Film::getLength)).collect(Collectors.toList());
        if (!order)
            Collections.reverse(sortedList);
        return sortedList;
    }

    @Override
    public List<Film> sortByYear(Set<Film> films, boolean order) {
        List<Film> filmList = films.stream().sorted(Comparator.comparing(Film::getYear)).collect(Collectors.toList());
        if (!order)
            Collections.reverse(filmList);
        return filmList;
    }

    @Override
    public List<Film> sortByLength(Set<Film> films, boolean order) {
        List<Film> filmList = films.stream().sorted(Comparator.comparing(Film::getLength)).collect(Collectors.toList());
        if (!order)
            Collections.reverse(filmList);
        return filmList;
    }

    @Override
    public List<Film> sortByNameAcc(Set<Film> films, boolean order) {
        List<Film> filmList = films.stream().sorted(Comparator.comparing(Film::getName)).collect(Collectors.toList());
        if (!order)
            Collections.reverse((filmList));
        return filmList;
    }


    @Override
    public List<Film> searchFilm(String searchString) {
        return filmRepository.findByNameContains(searchString);
    }

    @Override
    public Film showFilmById(Long id) {
        return filmRepository.findById(id).get();
    }
}
