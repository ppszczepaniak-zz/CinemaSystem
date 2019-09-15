package model.models;


import model.enums.TicketPrice;
import model.enums.TicketStatus;

public class Ticket {
    private Screening screening;
    private Seat seat;
    private TicketPrice ticketPrice;
    private TicketStatus ticketStatus;

    public Ticket(Screening screening, Seat seat, TicketPrice ticketPrice) {
        this.screening = screening;
        this.seat = seat;
        this.ticketPrice = ticketPrice;     //normalny / ulgowy
        this.ticketStatus = TicketStatus.RESERVED; // przy tworzeniu biletu jest automatycznie rezerwowany
    }

    public Screening getScreening() {
        return screening;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    @Override
    public String toString() {
        return "Date:" + screening.getStartOfMovie() + ", movie " + screening.getMovie() + ", " + seat + ", ticketPrice: " + ticketPrice +
                ", ticketStatus: " + ticketStatus;

    }
}
