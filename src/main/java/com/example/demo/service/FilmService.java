package com.example.demo.service;

import com.example.demo.model.Film;

import java.util.List;
import java.util.Set;

public interface FilmService {
    List<Film> getAllFilms();
    void saveFilm(Film film, String[] countryName, String[] genreName, String[] producerName);
    void deleteFilmById(long id);
    List<Film> sortAndSearch(String searchString, boolean order);
    List<Film> sortAndSearchByLength(String searchString, boolean order);
    List<Film> sortByYear(Set<Film> films, boolean order);
    List<Film> sortByLength(Set<Film> films, boolean order);
    List<Film> sortByNameAcc(Set<Film> films, boolean order);
    List<Film> searchFilm(String searchString);
    Film showFilmById(Long id);
}
