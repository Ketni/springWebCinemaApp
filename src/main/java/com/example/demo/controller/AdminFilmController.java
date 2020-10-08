package com.example.demo.controller;


import com.example.demo.model.Country;
import com.example.demo.model.Film;
import com.example.demo.model.Genre;
import com.example.demo.service.CountryService;
import com.example.demo.service.FilmService;
import com.example.demo.service.GenreService;
import com.example.demo.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdminFilmController {

    @Autowired
    FilmService filmService;
    @Autowired
    CountryService countryService;
    @Autowired
    GenreService genreService;
    @Autowired
    ProducerService producerService;

    @Secured("ADMIN")
    @GetMapping("/addFilmForm")
    public String addFilmForm(Model model) {
        Film film = new Film();
        model.addAttribute("countryList", countryService.getAllCountries());
        model.addAttribute("genreList", genreService.getAllGenres());
        model.addAttribute("producerList", producerService.getAllProducers());
        model.addAttribute("film", film);
        return "film_add";
    }

    @PostMapping("/saveFilm")
    public String saveFilm(@ModelAttribute("film") Film film, @RequestParam("countryName[]") String[] countryName,
                           @RequestParam("genreName[]") String[] genreName,
                           @RequestParam("producerName[]") String[] producerName) {
        filmService.saveFilm(film,countryName,genreName,producerName);
        return "redirect:/";
    }

    @GetMapping("/deleteFilm/{id}")
    public String deleteFilm(@PathVariable(value = "id") long id) {
        filmService.deleteFilmById(id);
        return "redirect:/";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }



}
