package graphics;

import Data.Database;
import Geography.Constituency;
import Geography.ElectoralMap;
import Simulation.Party;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Display extends Application {

    public static ElectoralMap map;

    public static void main(String[] args) {
        System.out.println("Starting database reading.");
        Database db = Database.getDatabase();

        map = new ElectoralMap();
        map.buildElectoralMap(db);
        map.buildParties();

        System.out.println(map.getSize() + " citizens being simulated.");
        System.out.println("Finished.");

        launch(args); //launch the fx application.

        //Election Time!
//        map.electionResults();
//        printElectoralResults();
//        for(int i = 0; i < 365; i++){
//            map.simulateOneDay();
//
//        }
//        map.electionResults();
//        printElectoralResults();


    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml_mainscreen.fxml"));
        JavaFXController controller = null;
        Parent root = null;
        try {
            root = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.electionResults(); //run the 2017 election.
        printElectoralResults();

        Scene scene = new Scene(root, 1200, 800);

        controller.vbox_Constituencies.setFillWidth(true);

        ArrayList<Constituency> list = map.getAllConstituencies();
        list.sort(new Comparator<Constituency>() {
            @Override
            public int compare(Constituency o1, Constituency o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        for(Constituency c : list){
            controller.insertConstituencyButton(c);
        }

        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void printElectoralResults(){
        HashMap<Party, Integer> results = map.electoral_seats;
        System.out.println("=====================================================================================");
        System.out.println("Election Results: " + map.getAverageIdeology() + ".");
        System.out.println("=====================================================================================");
        for(Party p : map.politicalParties){
            System.out.printf("%35s : %3d seats with %5.1f percent of the vote.%n", p.name, results.get(p), (double)map.individual_votes.get(p) / map.total_votes * 100.0);
        }
        System.out.println("=====================================================================================");
    }
}
