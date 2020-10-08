package com.example.demo.service;

import com.example.demo.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();
    void saveGenre(Genre genre);
    Genre getGenreByName(String name);
}
