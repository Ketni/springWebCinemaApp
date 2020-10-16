package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.model.Film;
import com.example.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void saveCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    public Country getCountryByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public void deleteCountryById(Long id) {
        Country country = countryRepository.findById(id).get();
        Set<Film> films = country.getFilms();
        for (Film film: films
        ) {
            film.getCountries().remove(country);
        }
        countryRepository.deleteById(id);
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).get();
    }
}
