import Data.Database;
import Geography.Constituency;
import Geography.ElectoralMap;
import Simulation.Citizen;
import Simulation.Party;

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
        HashMap<Party, Integer> results = map.electoral_seats;
        System.out.println("Election Results:");
        for(Party p : map.politicalParties){
            System.out.println(p.name + " : " + results.get(p));
        }

//
//        for(int i = 0; i < 100; i++){
//            map.simulateOneDay();
//            System.out.println("[" + i + "]" + map.getAverageIdeology() + ", " + map.getAverageIdeology());
//            System.out.println("[" + i + "][left] " + map.getLeftmostConstituency().getAverage());
//            System.out.println("[" + i + "][right] " + map.getRightmostConstituency().getAverage());
//            System.out.println("======================================================");
//        }

    }
}
