package model.models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Repertoire {
    private List<Screening> repertoire;

    public Repertoire() {
        this.repertoire = new ArrayList<>();
    }

    public List<Screening> getRepertoire() {
        return repertoire;
    }

    public void addScreening(Room room, Movie movie, LocalDateTime startTime) {
        repertoire.add(new Screening(room, movie, startTime));
    }
}
