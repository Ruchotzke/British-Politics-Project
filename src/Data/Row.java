package Data;

import java.util.HashMap;
import java.util.Map;

/**
 * A row is a single row of data from a database.
 */
public class Row {

    private HashMap<String, String> row = new HashMap<>();

    public Row(){

    }

    /**
     * Edit the supplied field.
     * @param key The field being searched for.
     * @param value The value to be inserted.
     * @return The old value if present, else null.
     */
    public String editField(String key, String value){
        String temp = null;
        if(row.containsKey(key.toLowerCase())){
            temp = row.remove(key.toLowerCase());
        }

        row.put(key.toLowerCase(), value);

        return temp;
    }

    /**
     * If the field exists, return its value.
     * Else return null.
     * @param key The field being searched for.
     * @return Null if not found, else the value associated with the key.
     */
    public String getField(String key){
        if(row.containsKey(key.toLowerCase())){
            return row.get(key.toLowerCase());
        } else {
            return null;
        }
    }

    public int getIntegerField(String key){
        if(row.containsKey(key.toLowerCase())){
            return Integer.parseInt(row.get(key.toLowerCase()));
        } else {
            return -1;
        }
    }

    public double getDoubleField(String key){
        if(row.containsKey(key.toLowerCase())){
            return Double.parseDouble(row.get(key.toLowerCase()));
        } else {
            return -1.0;
        }
    }

    /**
     * Get the number of columns in this row.
     * @return An integer value of columns.
     */
    public int size(){
        return row.size();
    }

    @Override
    public String toString(){
        String ret = "[";
        for(Map.Entry mapElement : row.entrySet()){
            ret += "{" + mapElement.getKey() + "," + mapElement.getValue() + "},";
        }

        return ret + "]";
    }
}
