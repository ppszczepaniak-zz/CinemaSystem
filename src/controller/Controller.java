package controller;

import controller.orders.OrderManager;
import controller.reports.ReportManager;
import model.CinemaSystem;
import model.enums.TicketPrice;
import model.models.*;
import view.UserInterface;
import view.enums.UserActions;
import java.util.List;

public class Controller {
    private CinemaSystem cinemaSystem;
    private UserInterface userInterface;
    private OrderManager orderManager;
    private ReportManager reportManager;
    private Cinema chosenCinema;
    private Movie chosenMovie;
    private Screening chosenScreening;
    private Seat chosenSeat;
    private Ticket chosenTicket = null;
    private List<List<Ticket>> cinemaReport;

    public Controller(CinemaSystem cinemaSystem, UserInterface userInterface, OrderManager orderManager, ReportManager reportManager) {
        this.cinemaSystem = cinemaSystem;
        this.userInterface = userInterface;
        this.orderManager = orderManager;
        this.reportManager = reportManager;
    }

    public void run() {
        UserActions actions = userInterface.printMenu();
        switch (actions) {
            case MAKE_RESERVATION:
                makeReservation();
                break;
            case PAY_FOR_TICKET:
                payForTicket();
                break;
            case CANCEL_RESERVATION:
                cancelReservation();
                break;
            case MAKE_REPORT:
                makeReport();
                break;
            case QUIT:
                quit();
                break;
        }
    }

    private void nullError() {
        userInterface.printNullError();
        run();
    }

    private void makeReservation() {
        //choose in 4 easy steps
        chosenCinema = userInterface.chooseCinemaMenu(cinemaSystem.getCinemasList()); //wybiera kino z listy
        chosenMovie = userInterface.chooseMovieMenu(cinemaSystem.getMoviesList());      // wybiera film z listy
        chosenScreening = userInterface.chooseScreeningMenu(chosenMovie, chosenCinema.getRepertoire()); //wybiera seans z najbliższych 5 dostępnych
        chosenSeat = userInterface.chooseSeatMenu(chosenScreening);

        //reserve & show room
        addTicket(userInterface.chooseTicketDiscount()); //tworze bilet o statusie zarezerwowany na podstawie wybranej znizki

        run(); //back to main menu

    }

    private void addTicket(TicketPrice ticketPrice) {
        switch (ticketPrice) {
            case NORMAL:
                orderManager.addReservation(chosenCinema, chosenScreening, chosenSeat, TicketPrice.NORMAL);
                break;
            case DISCOUNT:
                orderManager.addReservation(chosenCinema, chosenScreening, chosenSeat, TicketPrice.DISCOUNT);
                break;
        }
    }

    private void payForTicket() {
        chosenCinema = userInterface.chooseCinemaMenu(cinemaSystem.getCinemasList());

        try {
            chosenTicket = userInterface.payReservationMenu(chosenCinema.getTicketList());
        } catch (NullPointerException e) {
            nullError();
        }

        try {
            orderManager.payForTicket(chosenTicket);
        } catch (NullPointerException e) {
            nullError();
        }

        run();//back to main menu
    }

    private void cancelReservation() {
        chosenCinema = userInterface.chooseCinemaMenu(cinemaSystem.getCinemasList());

        try {
            chosenTicket = userInterface.cancelReservationMenu(chosenCinema.getTicketList());
        } catch (NullPointerException e) {
            nullError();
        }

        try {
            orderManager.cancelReservation(chosenCinema, chosenTicket);
        } catch (NullPointerException e) {
            nullError();
        }

        run(); //back to main menu
    }

    private void makeReport() {
        chosenCinema = userInterface.chooseCinemaMenu(cinemaSystem.getCinemasList());
        cinemaReport = reportManager.makeListOfPaidTickets(chosenCinema);
        userInterface.printReport(chosenCinema, cinemaReport);

        run(); //back to main menu
    }

    public void quit() {
        userInterface.quit();
    }
}
