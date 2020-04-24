package Utilities;

import Simulation.Party;

import java.util.ArrayList;

/**
 * A utility class for holding simulation settings.
 */
public class Const {

    /* References to the big boy parties */
    public static ArrayList<Party> simulatedParties;

    public static Party getPartyByName(String s){
        s = s.toLowerCase();
        switch(s){
            case "conservative":
                return simulatedParties.get(0);
            case "labour":
                return simulatedParties.get(1);
            case "liberal democrats":
                return simulatedParties.get(2);
            case "snp":
                return simulatedParties.get(3);
            case "ukip":
                return simulatedParties.get(4);
            case "green":
                return simulatedParties.get(5);
            case "plaid cymru":
                return simulatedParties.get(6);
            case "dup":
                return simulatedParties.get(7);
            case "uup":
                return simulatedParties.get(8);
            case "sinn fein":
                return simulatedParties.get(9);
            case "sdlp":
                return simulatedParties.get(10);
            case "alliance":
                return simulatedParties.get(11);
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * The number of citizens simulated per actual
     * person.
     */
    public static final double CITIZENS_PER_POPULATION = .01;

    /**
     * What percentage of the confidence difference should be
     * made up in a booster interpersonal interaction.
     */
    public static final double BOOSTER_CONFIDENCE_DRAG = 0.2;

    /**
     * How much should ideology change in a minor confrontation
     * for the loser.
     */
    public static final double BOOSTER_IDEOLOGICAL_DRAG = 0.07;

    /**
     * How much should ideology change in a major confrontation
     * for the loser.
     */
    public static final double MAJOR_IDEOLOGICAL_DRAG = 0.15;

    /**
     * How much should the winner and loser change their confidence
     * after a minor confrontation.
     */
    public static final double MINOR_WINNER_LOSER_CONF_CHANGE = 0.03;

    /**
     * How much should the winner and loser change their confidence
     * after a major confrontation.
     */
    public static final double MAJOR_WINNER_LOSER_CONF_CHANGE = 0.08;

    /**
     * If trust falls below this level, the message being communicated
     * will be ignored as rhetoric.
     */
    public static final double TRUST_LISTENING_CAP = 0.25;

    /**
     * The amount of confidence gained every turn when <code>Citizen.Rest()</code> is called.
     */
    public static final double RESTING_CONFIDENCE_INCREASE = 0.05;

    /**
     * How close to a party does a citizen need to be to vote for it.
     */
    public static final double PROXIMITY_VOTING_THRESHOLD = 0.15;

    /* Communications systems parameters */
    public static final int AVERAGE_COMMUNICATIONS = 2;
    public static final double BROADCAST_IMPACT = 0.15;

    /* Political Party Ideology Values */
    public static final double IDEOLOGY_CONSERVATIVE = 1.4;
    public static final double IDEOLOGY_LABOUR = .8;
    public static final double IDEOLOGY_LIBDEM = 1.1;
    public static final double IDEOLOGY_UKIP = 1.6;
    public static final double IDEOLOGY_GREEN = .55;
    public static final double IDEOLOGY_SNP = .7;
    public static final double IDEOLOGY_PC = .6;
    public static final double IDEOLOGY_DUP = 1.5;
    public static final double IDEOLOGY_SINNFEIN = .75;
    public static final double IDEOLOGY_SDLP = .6;
    public static final double IDEOLOGY_UUP = 1.1;
    public static final double IDEOLOGY_ALLIANCE = .9;
    public static final double IDEOLOGY_UNALIGNED = 1;

    /* Political Party 2017 Vote Percentages */
    public static final double POPULAR_CONSERVATIVE = .42;
    public static final double POPULAR_LABOUR = .40;
    public static final double POPULAR_LIBDEM = .07;
    public static final double POPULAR_UKIP = .01;
    public static final double POPULAR_GREEN = .01;
    public static final double POPULAR_SNP = .05;
    public static final double POPULAR_PC = .01;
    public static final double POPULAR_DUP = .02;
    public static final double POPULAR_SINNFEIN = .02;
    public static final double POPULAR_SDLP = .01;
    public static final double POPULAR_UUP = .01;
    public static final double POPULAR_ALLIANCE = .01;
    public static final double POPULAR_UNALIGNED = 0;

}
