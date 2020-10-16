package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "/admin")
@Secured("ADMIN")
@Controller()
public class AdminFilmController {

    @Autowired
    FilmService filmService;
    @Autowired
    CountryService countryService;
    @Autowired
    GenreService genreService;
    @Autowired
    ProducerService producerService;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

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
        return "redirect:/admin/films";
    }



    @GetMapping("/mainPage")
    public String adminHomePage(){
        return "admin_page";
    }



    @GetMapping("/films")
    public String getFilmPage(Model model){
        model.addAttribute("filmList",filmService.getAllFilms());
        return "film_page";
    }

    @GetMapping("/deleteFilm/{id}")
    public String deleteFilm(@PathVariable(value = "id") long id) {
        filmService.deleteFilmById(id);
        return "redirect:/admin/films";
    }

    @GetMapping("/editFilm/{id}")
    public String editFilm(Model model,@PathVariable(value = "id") long id){
        model.addAttribute("film",filmService.showFilmById(id));
        model.addAttribute("countryList", countryService.getAllCountries());
        model.addAttribute("genreList", genreService.getAllGenres());
        model.addAttribute("producerList", producerService.getAllProducers());
        return "edit_film";
    }

    @PostMapping("/updateFilm")
    public String updateFilm(@ModelAttribute("film") Film film,@RequestParam("countryName[]") String[] countryName,
                             @RequestParam("genreName[]") String[] genreName,
                             @RequestParam("producerName[]") String[] producerName){
        filmService.saveFilm(film,countryName,genreName,producerName);
        return "redirect:/admin/films";
    }

    @GetMapping("/users")
    public String getUserPage(Model model){
        model.addAttribute("userList",userService.getAllUsers());
        return "user_page";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(Model model,@PathVariable(value = "id") long id){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roleList", roleRepository.findAll());
        return "edit_user";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("roleName[]") String [] roles){
        userService.updateUser(user,roles);
        return "redirect:/admin/users";
    }



    @GetMapping("/genres")
    public String getGenrePage(Model model){
        model.addAttribute("genreList",genreService.getAllGenres());
        return "genre_page";
    }

    @GetMapping("/deleteGenre/{id}")
    public String deleteGenre(@PathVariable(value = "id") long id) {
        genreService.deleteGenreById(id);
        return "redirect:/admin/genres";
    }

    @GetMapping("/editGenre/{id}")
    public String editGenre(Model model,@PathVariable(value = "id") long id){
        model.addAttribute("genre", genreService.getGenreById(id));
        return "edit_genre";
    }

    @PostMapping("/updateGenre")
    public String updateGenre(@ModelAttribute("genre") Genre genre){
        genreService.saveGenre(genre);
        return "redirect:/admin/genres";
    }



    @GetMapping("/countries")
    public String getCountryPage(Model model){
        model.addAttribute("countryList",countryService.getAllCountries());
        return "country_page";
    }

    @GetMapping("/deleteCountry/{id}")
    public String deleteCountry(@PathVariable(value = "id") long id) {
        countryService.deleteCountryById(id);
        return "redirect:/admin/countries";
    }

    @GetMapping("/editCountry/{id}")
    public String editCountry(Model model,@PathVariable(value = "id") long id){
        model.addAttribute("country", countryService.getCountryById(id));
        return "edit_country";
    }

    @PostMapping("/updateCountry")
    public String updateCountry(@ModelAttribute("country") Country country){
        countryService.saveCountry(country);
        return "redirect:/admin/countries";
    }



    @GetMapping("/producers")
    public String getProducerPage(Model model){
        model.addAttribute("producerList",producerService.getAllProducers());
        return "producer_page";
    }

    @GetMapping("/deleteProducer/{id}")
    public String deleteProducer(@PathVariable(value = "id") long id) {
        producerService.deleteProducerById(id);
        return "redirect:/admin/producers";
    }

    @GetMapping("/editProducer/{id}")
    public String editProducer(Model model,@PathVariable(value = "id") long id){
        model.addAttribute("producer", producerService.getProducerById(id));
        return "edit_producer";
    }

    @PostMapping("/updateProducer")
    public String updateProducer(@ModelAttribute("producer") Producer producer){
        producerService.saveProducer(producer);
        return "redirect:/admin/producers";
    }

    @GetMapping("/addCountryForm")
    public String addCountry(Model model){
        Country country = new Country("");
        model.addAttribute("country",country);
        return "new_country";
    }

    @PostMapping("/addCountry")
    public String addCountry(@ModelAttribute("country") Country country){
        countryService.saveCountry(country);
        return "redirect:/admin/countries";
    }


    @GetMapping("/addGenreForm")
    public String addGenre(Model model){
        Genre genre = new Genre("");
        model.addAttribute("genre",genre);
        return "new_genre";
    }

    @PostMapping("/addGenre")
    public String addGenre(@ModelAttribute("genre") Genre genre){
        genreService.saveGenre(genre);
        return "redirect:/admin/genres";
    }

    @GetMapping("/addProducerForm")
    public String addProducer(Model model){
        Producer producer = new Producer("");
        model.addAttribute("producer",producer);
        return "new_producer";
    }

    @PostMapping("/addProducer")
    public String addGenre(@ModelAttribute("producer") Producer producer){
        producerService.saveProducer(producer);
        return "redirect:/admin/producers";
    }



}
