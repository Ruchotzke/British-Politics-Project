import Data.Database;
import Geography.Constituency;
import Geography.ElectoralMap;
import Simulation.Citizen;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static ElectoralMap map;
    public static void main(String[] args) {

        System.out.println("Starting database reading.");
        Database db = Database.getDatabase();

        map = new ElectoralMap();
        map.buildElectoralMap(db);

        System.out.println(map.getSize() + " citizens being simulated.");
        System.out.println("Finished.");

        for(int i = 0; i < 100; i++){
            map.simulateOneDay();
            System.out.println("[" + i + "]" + map.getAverageIdeology() + ", " + map.getAverageIdeology());
            System.out.println("[" + i + "][left] " + map.getLeftmostConstituency().getAverage());
            System.out.println("[" + i + "][right] " + map.getRightmostConstituency().getAverage());
            System.out.println("======================================================");
        }

    }
}
