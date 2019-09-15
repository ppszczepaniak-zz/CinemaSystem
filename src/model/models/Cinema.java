package model.models;


import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private Repertoire repertoire;
    private String name;
    private int numberOfRooms;
    private List<Room> roomList;
    private List<Ticket> ticketList;

    public Cinema(String name, int numberOfRooms) {  // numberOfRooms musi być >0 !)
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.roomList = new ArrayList<>();
        for (int i = 1; i <= numberOfRooms; i++) {  //tworze liste sal
            this.roomList.add(new Room(i, 6, 8));   //np. 6 rzedow, 8 miejsc
        }
        this.ticketList = new ArrayList<>();  //przy tworzeniu kina lista jest pusta
    }

    @Override
    public String toString() {
        return name;
    }

    public Room getRoom(int roomNumber) {
        return roomList.get(roomNumber - 1);
    }

    public List<Screening> getRepertoire() {
        return repertoire.getRepertoire();
    }

    public void addRepertoir(Repertoire repertoire) {
        this.repertoire = repertoire;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
