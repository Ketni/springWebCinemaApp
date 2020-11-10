package com.example.demo.controller;


import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.service.CountryService;
import com.example.demo.service.FilmService;
import com.example.demo.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    private FilmService filmService;
    private UserService userService;

    @Autowired
    public MainController(FilmService filmService, UserService userService){
        this.filmService = filmService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("filmList", filmService.getAllFilms());
        model.addAttribute("searchString","");
        model.addAttribute("order",true);
        return "index";
    }


    @GetMapping("/searchAndSort")
    public String searchAndSort(Model model, @RequestParam("searchString") String searchString,
                                @RequestParam("order") boolean order){
        model.addAttribute("filmList",filmService.sortAndSearch(searchString,order));
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("order", getNewOrder(order));
        return "index";
    }


    @GetMapping("/searchAndSortByLength")
    public String searchAndSortByLength(Model model, @RequestParam("searchString") String searchString,
                                @RequestParam("order") boolean order){
        model.addAttribute("filmList",filmService.sortAndSearchByLength(searchString,order));
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("order", getNewOrder(order));
        return "index";
    }



    @GetMapping("/filmDetails/{id}")
    public String showDetails(Model model, @PathVariable("id") Long id){
        model.addAttribute("film",filmService.showFilmById(id));
        model.addAttribute("userList",userService.getAllUsers());
        return "film_details";
    }

    @GetMapping("/login")
    public String login(){
      return "login";
    }

    @PostMapping("/addFilm/{user}")
    public String addFilm(@PathVariable("user") String name,@RequestParam("filmId") Long filmId) {
        userService.addFilmToUser(filmId, name);
        return "redirect:/";
    }

    @PostMapping("/addFilmFromDetails/{user}")
    public String addFilmFromDetails(@PathVariable("user") String name,@RequestParam("filmId") Long filmId){
        userService.addFilmToUser(filmId,name);
        return "redirect:/filmDetails/"+filmId;
    }

    static boolean getNewOrder(boolean order){
        if (order)
            return false;
        return true;
    }


}
