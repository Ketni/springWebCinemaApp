package com.example.demo.repository;

import com.example.demo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {
    List<Film> findAllByOrderByNameAsc();
    List<Film> findAllByOrderByNameDesc();
    List<Film> findAllByName(String searchString);
}
