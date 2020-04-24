import Data.Database;
import Geography.Constituency;
import Geography.ElectoralMap;
import Simulation.Citizen;
import Simulation.Party;
import Utilities.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {

    public static ElectoralMap map;
    public static void main(String[] args) {

        System.out.println("Starting database reading.");
        Database db = Database.getDatabase();

        map = new ElectoralMap();
        map.buildElectoralMap(db);
        map.buildParties();

        System.out.println(map.getSize() + " citizens being simulated.");
        System.out.println("Finished.");

        //Election Time!
        map.electionResults();
        printElectoralResults();
        for(int i = 0; i < 365; i++){
            map.simulateOneDay();

        }
        map.electionResults();
        printElectoralResults();

    }

    public static void printElectoralResults(){
        HashMap<Party, Integer> results = map.electoral_seats;
        System.out.println("=====================================================================================");
        System.out.println("Election Results: " + map.getAverageIdeology() + ".");
        System.out.println("=====================================================================================");
        for(Party p : Const.simulatedParties){
            System.out.printf("%35s : %3d seats with %5.1f percent of the vote.%n", p.name, results.get(p), (double)map.individual_votes.get(p) / map.total_votes * 100.0);
        }
        System.out.println("=====================================================================================");
    }
}
