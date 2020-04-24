package Simulation;

import Geography.Constituency;
import Geography.Country;
import Utilities.Const;
import Utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A citizen is a participant in the simulated political system.
 */
public class Citizen {

    public final Constituency constituency;

    public HashMap<Party, Double> partyOpinions;  /* What parties does this citizen support? */
    public double activation;                                       /* How activated is this citizen? [0,1] */
    public double interactivity;                                    /* How interactive is this citizen? [0,1] */
    public double confidence;                                       /* How confident is this citizen in their views? [0,1] */

    private ArrayList memory;           /* The memory of interactions this citizen has */

    public Citizen(Constituency home, HashMap<Party, Double> support, double confidence, double activation){
        this.constituency = home;
        this.partyOpinions = support;
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

                loser.ideology += (winner.ideology - loser.ideology) * trust * Const.MAJOR_IDEOLOGICAL_DRAG;
            }
        }

        Utilities.clamp(this.ideology, 0, 2);
        Utilities.clamp(this.confidence, 0, 1);
        Utilities.clamp(other.ideology, 0, 2);
        Utilities.clamp(other.confidence, 0, 1);

    }

    /**
     * Rest this citizen, allowing their confidence
     * to increase (over time).
     */
    public void rest(){
        confidence = Utilities.clamp(confidence + Const.RESTING_CONFIDENCE_INCREASE, 0, 1);
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

    public void takeBroadcast(IBroadcast broadcast){
        double dist = broadcast.getIdeology() - this.ideology;
        double impact = (1-confidence) * broadcast.getStrength();
        this.ideology += dist * impact * Const.BROADCAST_IMPACT;
        this.confidence += Const.RESTING_CONFIDENCE_INCREASE; //bolster a little bit.

        this.ideology = Utilities.clamp(this.ideology, 0, 2);
        this.confidence = Utilities.clamp(this.confidence, 0, 1);
    }

    /**
     * Vote, given a list of candidates.
     * @param options The party options listed on the ballot.
     * @return
     */
    public Party vote(ArrayList<Party> options){
        //simple proximity voting
        Party closest = options.get(0);
        for(Party p : options){
            if(Math.abs(p.ideology - ideology) < Math.abs(closest.ideology - ideology)){
                closest = p;
            }
        }
        //if the found party is a close match, use it
        if(closest.ideology - ideology < Const.PROXIMITY_VOTING_THRESHOLD) return closest;

        return closest;
    }

    @Override
    public String toString(){
        return "[Citizen] Confidence = " + confidence + ", Ideology = " + ideology + ", Interactivity: " + interactivity + ", Activation: "
                + activation + ". Constituency: " + constituency.name + ".";
    }

}
