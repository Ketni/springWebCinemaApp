package com.example.demo.model;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int year;
    private int length;
    @Column(length = 2048)
    private String description;
    private String pictureUrl;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "film_country",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    Set<Country> countries;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    Set<Genre> genres;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "film_producer",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    Set<Producer> producers;
    @ManyToMany(mappedBy = "films",cascade = CascadeType.ALL)
    private Set<User> users;

    public Set<User> getUsers() {
        if (users == null)
            return new HashSet<User>();
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Producer> getProducers(){
        if (producers == null) {
            return new HashSet<>();
        }
        return producers;
    }

    public void setProducers(Set<Producer> producers) {
        this.producers = producers;
    }

    public Set<Genre> getGenres() {
        if (genres == null) {
            return new HashSet<>();
        }
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Set<Country> getCountries() {
        if (countries == null) {
            return new HashSet<>();
        }
        return countries;
    }
}
