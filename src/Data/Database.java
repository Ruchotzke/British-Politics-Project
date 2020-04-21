package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * An in-memory database used to store the information
 * from the electoral information document.
 */
public class Database {

    private static Database database = null;

    public static final int MAX_COLUMN = 52;

    public HashMap<String, Row> data = new HashMap<>();
    public ArrayList<String> headings = new ArrayList<>();

    private Database(){
        try {
            populateDatabase("Data/UK GE 2010_2015_2017 V1_2 (+Brexit vote).csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Constituencies read: " + getNumEntries());
    }

    /**
     * Get a reference to this in-memory database.
     * This is a singleton class.
     * @return
     */
    public static Database getDatabase() {
        if(database == null){
            database = new Database();
        }

        return database;
    }

    private void populateDatabase(String address) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(address));
        int index = 0;

        //the first line contains the headers. Grab them.
        Scanner headScanner = new Scanner(scanner.nextLine());
        headScanner.useDelimiter(",");
        while(headScanner.hasNext() && index < MAX_COLUMN){
            headings.add(headScanner.next().trim().toLowerCase());
            index += 1;
        }

        //we are running through a comma delimited file, and we only want 2017(and swing) data.
        while(scanner.hasNextLine()){
            Row line = parseLine(scanner.nextLine().trim());
            data.put(line.getField("constituency_name").toLowerCase(), line);
//            System.out.println("Read data for: " + line.getField("constituency_name"));
        }
    }

    private Row parseLine(String line){
        Row row = new Row();
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",");

        int index = 0;

        while(scanner.hasNext() && index < MAX_COLUMN){
            String ret = row.editField(headings.get(index), scanner.next().trim().toLowerCase());
            if(ret != null) throw new IllegalStateException(ret);
            index += 1;
        }

        return row;
    }

    public int getNumEntries(){
        return data.size();
    }

    /**
     * Get the row of information associated with a supplied constituency.
     * @param constituency The constituency being searched for.
     * @return null if no constituency data found.
     */
    public Row getRow(String constituency){
        Row row = data.get(constituency.toLowerCase());
        if(row == null) System.out.println("[ERROR] Constituency data not found for: " + constituency);
        return row;
    }

    public String getData(String constituency, String field){
        return getRow(constituency.toLowerCase()).getField(field);
    }

    public int getIntegerData(String constituency, String field){
        return Integer.parseInt(getData(constituency, field));
    }

    public double getDoubleData(String constituency, String field){
        return Double.parseDouble(getData(constituency, field));
    }
}
