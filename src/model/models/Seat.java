package model.models;


import model.enums.SeatStatus;

public class Seat {
    private int rowNumber;
    private int seatNumber;
    private SeatStatus seatStatus;

    public Seat(int rowNumber, int seatNumber) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.seatStatus = SeatStatus.FREE; //przy tworzeniu jest z automatu wolne
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Ticket ticket) {  //ustawia status w zaleznosci od statusu biletu (opłacony/zarezerwowany/wolny).
        switch (ticket.getTicketStatus()) {
            case AVAILABLE:
                seatStatus = SeatStatus.FREE;
                break;
            case RESERVED:
                seatStatus = SeatStatus.RESERVED;
                break;
             case PAID:
                seatStatus = SeatStatus.OCCUPIED;
                break;
        }
    }

    @Override
    public String toString() {
        return "seat: " + seatNumber + ", row: " + rowNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}


