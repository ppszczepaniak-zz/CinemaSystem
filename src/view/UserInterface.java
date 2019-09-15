package view;


import model.enums.SeatStatus;
import model.enums.TicketPrice;
import model.enums.TicketStatus;
import model.models.*;
import view.enums.UserActions;

import java.time.Month;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private int chosenIndex;
    private int iterator;
    private Scanner input = new Scanner(System.in);


    public UserActions printMenu() {
        do {
            chosenIndex = -1;
            System.out.println("===MAIN MENU===");
            System.out.println("[1] Buy ticket");
            System.out.println("[2] Pay for your reservation");
            System.out.println("[3] Cancel your reservation");
            System.out.println("[4] Generate report for cinema");
            System.out.println("[5] EXIT");

            try {
                chosenIndex = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR! Please type the number from the menu!");
                input.next(); // "eats" the wrong typed token, so it's not passed again to nextInt() which causes infinite loop
            }
        } while (chosenIndex > 5 || chosenIndex < 1);

        System.out.println("You've chosen [" + chosenIndex + "]");
        switch (chosenIndex) {
            case 1:
                return UserActions.MAKE_RESERVATION;
            case 2:
                return UserActions.PAY_FOR_TICKET;
            case 3:
                return UserActions.CANCEL_RESERVATION;
            case 4:
                return UserActions.MAKE_REPORT;
            case 5:
                return UserActions.QUIT;
        }
        return null;  //returns null if switch fails (impossible because of do-while loop)
    }

    public void quit() {
        System.out.println("Goodbye.");
    }

    public Cinema chooseCinemaMenu(List<Cinema> cinemasList) {
        Cinema chosenCinema;
        do {
            chosenIndex = -1;
            iterator = 0;
            System.out.println("Please choose the cinema...");
            for (Cinema cinema : cinemasList) {
                iterator += 1;
                System.out.println("[" + iterator + "] " + cinema);
            }

            try {
                chosenIndex = input.nextInt() - 1;
            } catch (InputMismatchException e) {
                System.out.println("ERROR! Please type the number from the menu!");
                input.next();
            }
        } while (chosenIndex > cinemasList.size() - 1 || chosenIndex < 0);

        chosenCinema = cinemasList.get(chosenIndex);
        System.out.println("You've chosen [" + chosenCinema + "]");
        return chosenCinema;
    }

    public Movie chooseMovieMenu(List<Movie> moviesList) {
        Movie chosenMovie;
        do {
            chosenIndex = -1;
            iterator = 0;
            System.out.println("Please choose the movie...");
            for (Movie movie : moviesList) {
                iterator += 1;
                System.out.println("[" + iterator + "] " + movie);
            }

            try {
                chosenIndex = input.nextInt() - 1;
            } catch (InputMismatchException e) {
                System.out.println("ERROR! Please type the number from the menu!");
                input.next();
            }

        } while (chosenIndex > moviesList.size() - 1 || chosenIndex < 0);

        chosenMovie = moviesList.get(chosenIndex);
        System.out.println("You've chosen [" + chosenMovie + "]");
        return chosenMovie;
    }

    public Screening chooseScreeningMenu(Movie chosenMovie, List<Screening> repertoire) {
        Screening chosenScreening;
        List<Screening> nextScreenings = new ArrayList<>(5);

        for (Screening screening : repertoire) {  //szuka w repertuarze danego kina seansów szukanego filmu
            if (screening.getMovie().equals(chosenMovie)) {
                nextScreenings.add(screening);
            }
        }

        do {
            chosenIndex = -1;
            System.out.println("Please choose the screening...");
            for (int i = 1; i <= 5; i++) {          //bierze pod uwagę najbliższych w czasie 5 seansów
                System.out.println("[" + i + "] " + nextScreenings.get(i - 1));
            }

            try {
                chosenIndex = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR! Please type the number from the menu!");
                input.next(); //
            }
        } while (chosenIndex > nextScreenings.size() || chosenIndex < 1);

        chosenScreening = nextScreenings.get(chosenIndex);
        System.out.println("You've chosen screening number [" + (nextScreenings.indexOf(chosenScreening)) + "]");
        return chosenScreening;
    }

    public Seat chooseSeatMenu(Screening chosenScreening) {
        // wyswietlam liste dostepnych
        int rowNumber;
        int seatNumber;
        Seat chosenSeat;

        do {
            System.out.println("Showing room...");
            showRoom(chosenScreening);
            Room chosenRoom = chosenScreening.getRoom();
            //wybieram rząd
            do {
                chosenIndex = -1;
                System.out.println("Please choose row number...");

                try {
                    chosenIndex = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ERROR! Please choose row number!");
                    input.next();
                }
            } while (chosenIndex > chosenRoom.getRows() || chosenIndex < 1);
            rowNumber = chosenIndex;

            //wybieram miejsce w rzędzie
            do {
                chosenIndex = -1;
                System.out.println("Please choose seat number...");

                try {
                    chosenIndex = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ERROR! Please choose the seat number!");
                    input.next();
                }
            } while (chosenIndex > chosenRoom.getSeatsInRow() || chosenIndex < 1);
            seatNumber = chosenIndex;
            chosenSeat = chosenScreening.getSeat(rowNumber, seatNumber);

            if (chosenSeat.getSeatStatus() != SeatStatus.FREE) {
                System.out.println("This seat is not available!");
            }

        } while (chosenSeat.getSeatStatus() != SeatStatus.FREE); //spr czy miejsce dostepne

        System.out.println("You've chosen: row [" + chosenSeat.getRowNumber() + "], seat number: [" + chosenSeat.getSeatNumber() + "]");
        return chosenSeat;
    }


    public void showRoom(Screening screening) {
        System.out.println("=============SCREEN================");
        for (int i = 1; i <= screening.getRoom().getRows(); i++) {
            System.out.print("ROW: " + i + " ");
            for (int j = 1; j <= screening.getRoom().getSeatsInRow(); j++) {
                switch (screening.getSeat(i, j).getSeatStatus()) {
                    case FREE:
                        System.out.print("[" + j + "]");
                        break;
                    case RESERVED:
                        System.out.print("[_]");
                        break;
                    case OCCUPIED:
                        System.out.print("[ ]");
                        break;
                }
            }
            System.out.println();
        }
    }

    public TicketPrice chooseTicketDiscount() {
        do {
            chosenIndex = -1;
            System.out.println("Choose ticket price...");
            System.out.println("[1] Normal");
            System.out.println("[2] Discount");

            try {
                chosenIndex = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR! Please type the number from the menu!");
                input.next();
            }

        } while (chosenIndex != 1 && chosenIndex != 2);

        System.out.println("You've chosen [" + chosenIndex + "]");
        System.out.println("Your ticket is now reserved. Please pay for your reservation or cancel it.");

        switch (chosenIndex) {
            case 1:
                return TicketPrice.NORMAL;
            case 2:
                return TicketPrice.DISCOUNT;
        }
        return null;  //returns null if switch fails (impossible because of do-while loop)
    }

    public Ticket payReservationMenu(List<Ticket> ticketList) {

        if (ticketList.isEmpty()) {
            return null;
        }

        Ticket chosenTicket = null; //przypisuję null, chyba że coś jest na liście rezerwacji
        List<Ticket> reservedTicketList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            if (ticket.getTicketStatus().equals(TicketStatus.RESERVED)) {
                reservedTicketList.add(ticket);
            }
        }

        if (reservedTicketList.isEmpty()) { //jeśli list pusta to błąd
            System.out.println("ERROR! There are no valid reservations!");
        } else {

            do {
                chosenIndex = -1;
                System.out.println("Your reservation list:");

                for (int i = 1; i <= reservedTicketList.size(); i++) {
                    System.out.println("[" + i + "] " + reservedTicketList.get(i - 1));  // wyswietla liste biletow z kina o statusie RESERVED
                }

                System.out.println("Please choose the reservation to pay for...");
                try {
                    chosenIndex = input.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("ERROR! Please type the number from the menu!");
                    input.next();
                }
            } while (chosenIndex > reservedTicketList.size() - 1 || chosenIndex < 0);

            chosenTicket = reservedTicketList.get(chosenIndex);
            System.out.println("This reservation is now paid.");
            return chosenTicket;
        }
        return chosenTicket;
    }

    public Ticket cancelReservationMenu(List<Ticket> ticketList) {

        if (ticketList.isEmpty()) {
            return null;
        }

        Ticket chosenTicket = null; //przypisuję null, chyba że coś jest na liście rezerwacji
        List<Ticket> reservedTicketList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            if (ticket.getTicketStatus().equals(TicketStatus.RESERVED)) {
                reservedTicketList.add(ticket);
            }
        }

        if (reservedTicketList.isEmpty()) { //jeśli list pusta to błąd
            System.out.println("ERROR! There are no valid reservations!");
        } else {

            do {
                chosenIndex = -1;
                System.out.println("Your reservation list:");

                for (int i = 1; i <= reservedTicketList.size(); i++) {
                    System.out.println("[" + i + "] " + reservedTicketList.get(i - 1));  // wyswietla liste biletow z kina o statusie RESERVED
                }

                System.out.println("Please choose the reservation that you wish to cancel...");
                try {
                    chosenIndex = input.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("ERROR! Please type the number from the menu!");
                    input.next();
                }

            } while (chosenIndex > reservedTicketList.size() - 1 || chosenIndex < 0);

            chosenTicket = reservedTicketList.get(chosenIndex);
            System.out.println("This reservation is now cancelled.");
            return chosenTicket;
        }
        return chosenTicket;
    }

    public void printNullError() {
        System.out.println("ERROR! Null!");
    }


    public void printReport(Cinema cinema, List<List<Ticket>> listOfTicketsPerMonth) {
        System.out.println("=> Report for cinema " + cinema + ":");
        List<Ticket> ticketsList;

        for (int i = 1; i < 12; i++) {
            ticketsList = listOfTicketsPerMonth.get(i - 1);
            System.out.println(Month.of(i) + " - tickets sold: " + ticketsList.size());
        }
    }
}



