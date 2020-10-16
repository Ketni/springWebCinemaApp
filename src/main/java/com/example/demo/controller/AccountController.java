package com.example.demo.controller;


import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.service.FilmService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/account")
@Controller
public class AccountController {

    @Autowired
    UserService userService;
    @Autowired
    FilmService filmService;

    @GetMapping("/{user}")
    public String showAccount(Model model, @PathVariable("user") String name){
        model.addAttribute("user",userService.findByName(name));
        model.addAttribute("filmList",userService.findByName(name).getFilms());
        model.addAttribute("order", true);
        return "account";
    }

    @GetMapping("/clearFilm")
    public String clearFilm(@RequestParam(value = "filmId") long filmId,
                            @RequestParam("username") String username,
                            HttpServletRequest httpServletRequest) {
        userService.removeFilmById(username,filmId);
        String referer = httpServletRequest.getHeader("Referer");
        return "redirect:"+referer;
    }

    @GetMapping("/sortByName")
    public String sortByName(Model model,@RequestParam("order") boolean order,
                             @RequestParam("username") String username){
        User user = userService.findByName(username);
        model.addAttribute("filmList",filmService.sortByNameAcc(user.getFilms(),order));
        model.addAttribute("user",userService.findByName(username));
        if (order){
            order = false;
            model.addAttribute("order",order);
        }
        else{
            order = true;
            model.addAttribute("order",order);
        }
        return "account";
    }

    @GetMapping("/sortByYear")
    public String sortByYear(Model model,@RequestParam("order") boolean order,
                             @RequestParam("username") String username){
        User user = userService.findByName(username);
        model.addAttribute("filmList",filmService.sortByYear(user.getFilms(),order));
        model.addAttribute("user",user);
        if (order){
            order = false;
            model.addAttribute("order",order);
        }
        else{
            order = true;
            model.addAttribute("order",order);
        }
        return "account";
    }

    @GetMapping("/sortByLength")
    public String sortByLength(Model model,@RequestParam("order") boolean order,
                             @RequestParam("username") String username){
        User user = userService.findByName(username);
        model.addAttribute("filmList",filmService.sortByLength(user.getFilms(),order));
        model.addAttribute("user",user);
        if (order){
            order = false;
            model.addAttribute("order",order);
        }
        else{
            order = true;
            model.addAttribute("order",order);
        }
        return "account";
    }



}
