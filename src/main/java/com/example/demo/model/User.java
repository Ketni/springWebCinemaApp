package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames ="username"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String passwrod;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "film_user",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "film_id", referencedColumnName = "id"))
    private Set<Film> films;

    public Set<Film> getFilms() {
        if (films == null)
            return new HashSet<Film>();
        return films;
    }
    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public User(String username, String passwrod, Collection<Role> roles) {
        this.username = username;
        this.passwrod = passwrod;
        this.roles = roles;
    }
    public User(){}




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }


}
