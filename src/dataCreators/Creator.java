package dataCreators;


import model.models.Movie;
import model.models.Cinema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Creator {
    private List<Movie> generatedMoviesList;
    private List<Cinema> generatedCinemasList;

    public Creator() {
        //tworze liste filmow i kin
        generatedMoviesList =  createMovies();
        generatedCinemasList = createCinemas();

        //dodaje repertuar do kin
        generateRepertoir();

    }

    private List<Movie> createMovies() {
        generatedMoviesList = new ArrayList<>();

        generatedMoviesList.add(new Movie("Forrest Gump"));
        generatedMoviesList.add(new Movie("Matrix"));
        generatedMoviesList.add(new Movie("The Shawshank Redemption"));
        generatedMoviesList.add(new Movie("Inception"));
        generatedMoviesList.add(new Movie("Apocalypse Now"));
        generatedMoviesList.add(new Movie("The Good, the Bad and the Ugly"));

        return generatedMoviesList;
    }

    private List<Cinema> createCinemas() {
        generatedCinemasList = new ArrayList<>();

        Cinema cinema1 = new Cinema("CinemaWarsaw", 1);
        Cinema cinema2 = new Cinema("CinemaCracow", 1);
        Cinema cinema3 = new Cinema("CinemaPoznan", 1);
        Cinema cinema4 = new Cinema("CinemaWroclaw", 1);
        Cinema cinema5 = new Cinema("CinemaGdansk", 1);

        return generatedCinemasList = Arrays.asList(cinema1,cinema2,cinema3,cinema4,cinema5);
    }

    private void generateRepertoir() {

        Cinema cinema1 = generatedCinemasList.get(0);
        Cinema cinema2 = generatedCinemasList.get(1);
        Cinema cinema3 = generatedCinemasList.get(2);
        Cinema cinema4 = generatedCinemasList.get(3);
        Cinema cinema5 = generatedCinemasList.get(4);

        //generate repertoir
        RepertoirCreator repertoir1 = new RepertoirCreator(cinema1, generatedMoviesList, LocalDateTime.parse("2019-06-01T08:00:00"), 3);
        RepertoirCreator repertoir2 = new RepertoirCreator(cinema2, generatedMoviesList, LocalDateTime.parse("2019-06-01T09:00:00"), 3);
        RepertoirCreator repertoir3 = new RepertoirCreator(cinema3, generatedMoviesList, LocalDateTime.parse("2019-06-01T08:30:00"), 3);
        RepertoirCreator repertoir4 = new RepertoirCreator(cinema4, generatedMoviesList, LocalDateTime.parse("2019-06-01T09:30:00"), 3);
        RepertoirCreator repertoir5 = new RepertoirCreator(cinema5, generatedMoviesList, LocalDateTime.parse("2019-06-01T10:00:00"), 3);

        //adds repertoir to cinemas
        cinema1.addRepertoir(repertoir1.generateRepertoir());
        cinema2.addRepertoir(repertoir2.generateRepertoir());
        cinema3.addRepertoir(repertoir3.generateRepertoir());
        cinema4.addRepertoir(repertoir4.generateRepertoir());
        cinema5.addRepertoir(repertoir5.generateRepertoir());
    }

    public List<Movie> getGeneratedMoviesList() {
        return generatedMoviesList;
    }

    public List<Cinema> getGeneratedCinemasList() {
        return generatedCinemasList;
    }
}
