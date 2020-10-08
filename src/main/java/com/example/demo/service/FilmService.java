package com.example.demo.service;

import com.example.demo.model.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAllFilms();
    void saveFilm(Film film, String[] countryName, String[] genreName, String[] producerName);
    void deleteFilmById(long id);
    List<Film> findAllByOrderByNameAsc();
    List<Film> findAllByOrderByNameDesc();
    List<Film> searchFilm(String searchString);
}
