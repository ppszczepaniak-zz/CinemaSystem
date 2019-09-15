package model;


import model.models.Movie;
import model.models.Cinema;

import java.util.List;

public class CinemaSystem {
    private List<Cinema> cinemasList;
    private List<Movie> moviesList;

    public CinemaSystem(List<Cinema> cinemasList, List<Movie> moviesList) {
        this.cinemasList = cinemasList;
        this.moviesList = moviesList;
    }

    public List<Cinema> getCinemasList() {
        return cinemasList;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }
}




