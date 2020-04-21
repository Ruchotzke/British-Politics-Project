import Data.Database;
import Geography.Constituency;
import Geography.ElectoralMap;

public class Main {

    /* Simulation Parameters are in Utilities/Const.java */


    public static void main(String[] args) {

        System.out.println("Starting database reading.");
        Database db = Database.getDatabase();

        ElectoralMap map = new ElectoralMap();
        map.buildElectoralMap(db);

        System.out.println(map.getSize() + " citizens being simulated.");
        System.out.println("Finished.");
    }
}
