package com.example.demo.service;

import com.example.demo.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    void saveCountry(Country country);
    Country getCountryByName(String name);
}
