package Simulation;

import Geography.Constituency;
import Utilities.Const;

import java.util.ArrayList;

/**
 * A citizen is a participant in the simulated political system.
 */
public class Citizen {

    public final Constituency constituency;

    public double ideology;            /* The ideology rating for this citizen [0,2]. */
    public double activation;          /* How activated is this citizen? [0,1] */
    public double interactivity;       /* How interactive is this citizen? [0,1] */
    public double confidence;          /* How confident is this citizen in their views? [0,1] */

    private ArrayList memory;           /* The memory of interactions this citizen has */

    public Citizen(Constituency home, double ideology, double confidence, double activation){
        this.constituency = home;
        this.ideology = ideology;
        this.confidence = confidence;
        this.activation = activation;
    }

    /**
     * Initiate a communication with another citizen.
     * @param other The other person to talk to.
     */
    public void communicateWith(Citizen other){
        other.takeCommunication(this);
    }

    /**
     * Use another citizen to takeCommunication with this citizen.
     * @param other The other citizen talking to this one.
     */
    public void takeCommunication(Citizen other){

        if(other == this) return;

        //find the appropriate interaction
        double dist = Math.abs(other.ideology - ideology);
        double conf = Math.abs(other.confidence - confidence);

        if(dist < 0.25){
            //booster interaction. Lower confidence gains some confidence.
            Citizen lessConfident = other.confidence > confidence ? this : other;
            lessConfident.confidence = Math.min(lessConfident.confidence + conf * Const.BOOSTER_CONFIDENCE_DRAG, 1);
        }
        else if(dist < 0.5)
        {
            //ideological drag (small) and booster
            Citizen lessConfident = other.confidence > confidence ? this : other;
            Citizen moreConfident = other.confidence <= confidence ? this : other;

            //ideological swing to the winner's side.
            lessConfident.ideology += (moreConfident.ideology - lessConfident.ideology) * Const.BOOSTER_IDEOLOGICAL_DRAG;

            //slight confidence changes (down loser, up winner)
            lessConfident.confidence -= Const.MINOR_WINNER_LOSER_CONF_CHANGE;
            moreConfident.confidence += Const.MINOR_WINNER_LOSER_CONF_CHANGE;
            lessConfident.confidence = Math.min(1, lessConfident.confidence);
            moreConfident.confidence = Math.min(1, moreConfident.confidence);
        }
        else
        {
            //winner - loser interaction or low trust
            double trust = -0.5 * dist + 1;
            if(trust < Const.TRUST_LISTENING_CAP){
                //low trust interaction, the message is ignored.
            } else {
                //winner, loser interaction
                Citizen winner = confidence >= other.confidence ? this : other;
                Citizen loser = confidence < other.confidence ? this : other;

                winner.confidence += Const.MAJOR_WINNER_LOSER_CONF_CHANGE;
                loser.confidence -= Const.MAJOR_WINNER_LOSER_CONF_CHANGE;

                winner.confidence = Math.min(1, winner.confidence);
                loser.confidence = Math.min(1, loser.confidence);

                loser.ideology += (winner.ideology - loser.ideology) * trust * Const.MAJOR_IDEOLOGICAL_DRAG;
            }
        }

    }

    /**
     * Rest this citizen, allowing their confidence
     * to increase (over time).
     */
    public void rest(){
        confidence += Const.RESTING_CONFIDENCE_INCREASE;
    }

    /**
     * Get the effective ideology of this citizen.
     * That is, a modulated value of ideology and confidence.
     * A low-confidence partisan is effectively a centrist.
     * @return
     */
    public double getEffectiveIdeology(){
        return 1 + confidence * (ideology - 1);
    }


    @Override
    public String toString(){
        return "[Citizen] Confidence = " + confidence + ", Ideology = " + ideology + ", Interactivity: " + interactivity + ", Activation: "
                + activation + ". Constituency: " + constituency.name + ".";
    }

}
