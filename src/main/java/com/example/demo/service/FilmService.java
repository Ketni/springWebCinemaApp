package com.example.demo.service;

import com.example.demo.model.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAllFilms();
    void saveFilm(Film film, String[] countryName, String[] genreName, String[] producerName);
    void deleteFilmById(long id);
    List<Film> sortByNameAsc();
    List<Film> sortByNameDesc();
//    List<Film> findAllByOrderByLengthAsc();
//    List<Film> findAllByOrderByLengthDesc();
    List<Film> searchFilm(String searchString);
    Film showFilmById(Long id);
}
