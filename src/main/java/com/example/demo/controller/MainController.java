package com.example.demo.controller;


import com.example.demo.model.Film;
import com.example.demo.service.CountryService;
import com.example.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("filmList", filmService.getAllFilms());
        model.addAttribute("order",true);
        return "index";
    }

    @GetMapping("/search")
    public String homePageSearch(Model model, @RequestParam("searchString") String name){
        model.addAttribute("filmList",filmService.searchFilm(name));
        return "index";
    }



















//    @GetMapping("/sort/{order}")
//    public String sortUsersByName(@PathVariable(value="order") boolean order, Model model){
//        if (order) {
//            model.addAttribute("userList",this.filmService.sortAllFilmsByNameAsc());
//            model.addAttribute("order",false);
//            return "index";
//        }else {
//            model.addAttribute("userList", this.filmService.sortAllFilmsByNameDesc());
//            model.addAttribute("order",true);
//            return "index";
//        }
//    }

    @GetMapping("/login")
    public String login(){
      return "login";
    }




}
