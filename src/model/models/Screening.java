package model.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Screening {

    private Room room;
    private Movie movie;
    private LocalDateTime startOfMovie;
    private List<List<Seat>> listOfSeats; //lista rzedow, z lista miejsc

    public Screening(Room room, Movie movie, LocalDateTime startOfMovie) {
        this.room = room;
        this.movie = movie;
        this.startOfMovie = startOfMovie;

        this.listOfSeats = new ArrayList<>();  // przy tworzeniu seansu tworzy miejsca dla danej sali (auto status miejsc: wolne)
        for (int i = 1; i <= room.getRows(); i++) {
            listOfSeats.add(i-1,new ArrayList<>());  //lista dla kazdego rzedu
            for (int j = 1; j <= room.getSeatsInRow(); j++) {
                listOfSeats.get(i-1).add(new Seat(i, j));  //dodaje miejsca do kazdego rzedu
            }
        }
    }

    public LocalDateTime getStartOfMovie() {
        return startOfMovie;
    }

    @Override
    public String toString() {
        return "Movie = " + movie + ", Date/time = " + startOfMovie + ", Room = " + room;
    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public Seat getSeat(int row, int seatNumber) {
        return listOfSeats.get(row - 1).get(seatNumber - 1); //zwraca szukane miejsce
    }

}
