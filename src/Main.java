import controller.Controller;
import controller.orders.OrderManager;
import controller.reports.ReportManager;
import dataCreators.Creator;
import model.CinemaSystem;
import view.UserInterface;

public class Main {
    public static void main(String[] args) {
        //tworzy liste filmow i kin z repertuarami
        Creator creator = new Creator();

        Controller controller = new Controller(
                new CinemaSystem(creator.getGeneratedCinemasList(), creator.getGeneratedMoviesList()), //dodaje wykreowane listy kin i filmow
                new UserInterface(),
                new OrderManager(),
                new ReportManager()
        ); //ładuje komponenty: View(UI), Model (cinema system), Controller: 2 parts - orders & reports
        controller.run();
    }
}


