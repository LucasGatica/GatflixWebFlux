package com.example.GatflixWebFlux.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Movie {
    @Id
    private  String id;
    private String name;
    private int year;
    private String description;
    private double rating;

    public Movie() {
        super();
    }


    public Movie(String id, String name, int year, String description, double rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id='" + id + '\'' +
                ", nome='" + name + '\'' +
                ", ano=" + year +
                ", sinopse='" + description + '\'' +
                ", nota=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year && Double.compare(movie.rating, rating) == 0 && Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(description, movie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, description, rating);
    }
}
