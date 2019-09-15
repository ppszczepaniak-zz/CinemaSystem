package dataCreators;


import model.models.Movie;
import model.models.Repertoire;
import model.models.Screening;
import model.models.Cinema;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RepertoirCreator {
    private Repertoire generatedRepertoire;
    private Cinema cinema;
    private List<Movie> moviesList;
    private LocalDateTime startTime;
    private long delay; //long required by LocalDateTime

    public RepertoirCreator(Cinema cinema, List<Movie> moviesList, LocalDateTime startTime, long delay) {
        this.generatedRepertoire = new Repertoire();
        this.cinema = cinema;
        this.moviesList = moviesList;
        this.startTime = startTime;
        this.delay = delay;
    }


    public Repertoire generateRepertoir() {
        //robi automatyczny repertuar - kolejne filmy z listy co "delay" godzin (pt sob, niedziela, 52 razy (kazdy weekend))
        for (int i = 1; i < 52; i++) {
            for (Movie movie : moviesList) {
                generatedRepertoire.addScreening(cinema.getRoom(1), movie, startTime); //piątek
                generatedRepertoire.addScreening(cinema.getRoom(1), movie, startTime.plusHours(24));//sobota
                generatedRepertoire.addScreening(cinema.getRoom(1), movie, startTime.plusHours(48)); //niedziela
                startTime = startTime.plusHours(delay);
            }
            startTime = startTime.plusWeeks(i);
        }

        //sortuje liste filmow w kolejnosci chronologicznej (tak jak w prawdziwym repertuarze)
        Collections.sort(generatedRepertoire.getRepertoire(), new Comparator<Screening>() {
            @Override
            public int compare(Screening screening1, Screening screening2) {
                if (screening1.getStartOfMovie().isAfter(screening2.getStartOfMovie())) {
                    return 1;
                } else {
                    return -1; //nie moze byc zero bo tak zrobiłem automat tworzacy ze dodaje X godzin zawsze do kolejnego filmu
                }
            }
        });
        return generatedRepertoire;
    }

}





