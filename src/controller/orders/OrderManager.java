package controller.orders;


import model.enums.TicketPrice;
import model.enums.TicketStatus;
import model.models.Screening;
import model.models.Seat;
import model.models.Ticket;
import model.models.Cinema;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    public void addReservation(Cinema cinema, Screening screening, Seat seat, TicketPrice ticketType) {
        Ticket newTicket = new Ticket(screening, seat, ticketType);//tworzy bilet (o statusie RESERVED)
        cinema.getTicketList().add(newTicket); //dodaje do listy biletow danego kina
        seat.setSeatStatus(newTicket); //zmienia status miejsca na RESERVED
    }

    public void payForTicket(Ticket chosenTicket) {
        chosenTicket.setTicketStatus(TicketStatus.PAID); //zmienia status biletu na PAID,
        chosenTicket.getSeat().setSeatStatus(chosenTicket); //zmienia status miejsc NA OCCUPIED
    }

    public void cancelReservation(Cinema cinema, Ticket badTicket) {
        badTicket.setTicketStatus(TicketStatus.AVAILABLE);  //resetuje status biletu na AVAILABLE
        badTicket.getSeat().setSeatStatus(badTicket); //zmienia status miejsca na FREE

        List<Ticket> correctedTicketList = new ArrayList<>(); //tworzy liste biletow bez tego, ktory usuwamy
        for (Ticket goodTicket : cinema.getTicketList()) {
            if (!goodTicket.equals(badTicket)) {
                correctedTicketList.add(goodTicket);
            }
        }
        cinema.setTicketList(correctedTicketList); //przesyla do kina poprawiona liste biletow
    }

}


