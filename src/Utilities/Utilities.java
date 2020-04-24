package Utilities;

public class Utilities {

    public static double clamp(double value, double min, double max){
        return Math.max(min, Math.min(max, value));
    }
}
