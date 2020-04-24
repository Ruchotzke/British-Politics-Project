package Utilities;

import Geography.Constituency;
import Simulation.Citizen;
import Simulation.Party;

import java.util.ArrayList;
import java.util.HashMap;

public class CitizenFactory {

    /**
     * Generate an array of citizens fit around a given constituency.
     * @param constituency The constituency to generate citizens for.
     * @return An array of citizens.
     */
    public static ArrayList<Citizen> generateConstituencyCitizens(Constituency constituency){
        ArrayList<Citizen> citizens = new ArrayList<>();

        int citizensToGenerate = (int)(constituency.constituencySize * Const.CITIZENS_PER_POPULATION);

        int conservativeVotes = (int)(constituency.voteResult17.get("Conservative").totalVotes * Const.CITIZENS_PER_POPULATION);
        int labourVotes = (int)(constituency.voteResult17.get("Labour").totalVotes * Const.CITIZENS_PER_POPULATION);
        int libDemVotes = (int)(constituency.voteResult17.get("Liberal Democrats").totalVotes * Const.CITIZENS_PER_POPULATION);
        int UKIPVotes = (int)(constituency.voteResult17.get("UKIP").totalVotes * Const.CITIZENS_PER_POPULATION);
        int greenVotes = (int)(constituency.voteResult17.get("Green").totalVotes * Const.CITIZENS_PER_POPULATION);
        int SNPVotes = (int)(constituency.voteResult17.get("SNP").totalVotes * Const.CITIZENS_PER_POPULATION);
        int plaidVotes = (int)(constituency.voteResult17.get("Plaid Cymru").totalVotes * Const.CITIZENS_PER_POPULATION);
        int dupVotes = (int)(constituency.voteResult17.get("DUP").totalVotes * Const.CITIZENS_PER_POPULATION);
        int sfVotes = (int)(constituency.voteResult17.get("Sinn Fein").totalVotes * Const.CITIZENS_PER_POPULATION);
        int uupVotes = (int)(constituency.voteResult17.get("UUP").totalVotes * Const.CITIZENS_PER_POPULATION);
        int sdlpVotes = (int)(constituency.voteResult17.get("SDLP").totalVotes * Const.CITIZENS_PER_POPULATION);
        int allianceVotes = (int)(constituency.voteResult17.get("Alliance").totalVotes * Const.CITIZENS_PER_POPULATION);

        //now generate the correct citizens for each party

        return citizens;
    }

    /**
     * Generate preferences for the supplied party.
     * @param cons s
     * @param lab s
     * @param libdem s
     * @param ukip s
     * @param green s
     * @param snp s
     * @param pc s
     * @param dup s
     * @param sf s
     * @param uup s
     * @param sdlp s
     * @param alliance s
     * @return s
     */
    private static HashMap<Party, Double> generatePreferences(double cons, double lab, double libdem, double ukip, double green
        , double snp, double pc, double dup, double sf, double uup, double sdlp, double alliance){
        HashMap<Party, Double> preference = new HashMap<>();

        preference.put(Const.getPartyByName("Conservative"), .75);
        preference.put(Const.getPartyByName("Labour"), .75);
        preference.put(Const.getPartyByName("liberal democrats"), .75);
        preference.put(Const.getPartyByName("UKIP"), .75);
        preference.put(Const.getPartyByName("Green"), .75);
        preference.put(Const.getPartyByName("SNP"), .75);
        preference.put(Const.getPartyByName("plaid cymru"), .75);
        preference.put(Const.getPartyByName("dup"), .75);
        preference.put(Const.getPartyByName("sinn fein"), .75);
        preference.put(Const.getPartyByName("uup"), .75);
        preference.put(Const.getPartyByName("sdlp"), .75);
        preference.put(Const.getPartyByName("alliance"), .75);

        return preference;
    }

    private static Citizen generateConservative(Constituency constituency){
        HashMap<Party, Double> preference = generatePreferences()

    }
}
