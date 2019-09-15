package controller.reports;


import model.enums.TicketStatus;
import model.models.Ticket;
import model.models.Cinema;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {
    private List<List<Ticket>> listOfPaidTicketsPerMonths;         //list of lists (12 lists of tickets sold for each month)

    public List<List<Ticket>> makeListOfPaidTickets(Cinema cinema) {  //lista sprzedanych biletow w danym miesiacu
        listOfPaidTicketsPerMonths = new ArrayList<>(12);
        for (int i = 1; i < 12; i++) {
            listOfPaidTicketsPerMonths.add(i - 1, new ArrayList<>());
            for (Ticket ticket : cinema.getTicketList()) {
                if (ticket.getScreening().getStartOfMovie().getMonth().equals(Month.of(i))) {   //jeśli bilet jest z danego miesiąca
                    if (ticket.getTicketStatus().equals(TicketStatus.PAID)) {                   //i ma status PAID
                        listOfPaidTicketsPerMonths.get(i - 1).add(ticket);                      //dodaj go do listy danego miesiaca
                    }
                }
            }
        }
        return listOfPaidTicketsPerMonths;
    }
}
