package model.models;

public class Movie {
    String title;

    public Movie(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
