package Simulation;

import Geography.Constituency;

import java.util.ArrayList;

/**
 * A citizen is a participant in the simulated political system.
 */
public class Citizen {

    public final Constituency constituency;

    private double ideology;            /* The ideology rating for this citizen [0,2]. */
    private double activation;          /* How activated is this citizen? [0,1] */
    private double interactivity;       /* How interactive is this citizen? [0,1] */
    private double confidence;          /* How confident is this citizen in their views? [0,1] */

    private ArrayList memory;           /* The memory of interactions this citizen has */

    public Citizen(Constituency home, double ideology, double activation){
        this.constituency = home;
        this.ideology = ideology;
        this.activation = activation;
    }

}
