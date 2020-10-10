package com.example.demo.controller;


import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.service.CountryService;
import com.example.demo.service.FilmService;
import com.example.demo.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("filmList", filmService.sortByNameAsc());
        model.addAttribute("order",true);
        return "index";
    }

    @GetMapping("/sort/{order}")
    public String sortUsersByName(@PathVariable (value="order") boolean order,Model model){
        if (order) {
            model.addAttribute("filmList",filmService.sortByNameAsc());
            model.addAttribute("order",false);
            return "index";
        }else {
            model.addAttribute("filmList",filmService.sortByNameDesc());
            model.addAttribute("order",true);
            return "index";
        }
    }


    @GetMapping("/search")
    public String homePageSearch(Model model, @RequestParam("searchString") String name){
        User user = userService.findByName("timur");
        Film film = filmService.showFilmById(5L);
        Set<Film> films = new HashSet<>();
        films.add(film);
        user.setFilms(films);
        userService.updateUser(user);
        model.addAttribute("filmList",filmService.searchFilm(name));
        return "index";
    }

    @GetMapping("/filmDetails/{id}")
    public String showDetails(Model model, @PathVariable("id") Long id){
        model.addAttribute("film",filmService.showFilmById(id));
        return "film_details";
    }

    @GetMapping("/login")
    public String login(){
      return "login";
    }




}
