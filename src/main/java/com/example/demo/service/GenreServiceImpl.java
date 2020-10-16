package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.Genre;
import com.example.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public Genre getGenreByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public void deleteGenreById(Long id) {
        Genre genre = genreRepository.findById(id).get();
        Set<Film> films = genre.getFilms();
        for (Film film: films
             ) {
            film.getGenres().remove(genre);
        }
        genreRepository.deleteById(id);
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).get();
    }
}
