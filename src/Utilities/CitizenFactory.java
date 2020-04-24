package Utilities;

import Geography.Constituency;
import Simulation.Citizen;

import java.util.ArrayList;

public class CitizenFactory {

    public static ArrayList<Citizen> generateConstituencyCitizens(Constituency constituency){
        ArrayList<Citizen> citizens = new ArrayList<>();

        int citizensToGenerate = (int)(constituency.constituencySize * Const.CITIZENS_PER_POPULATION);

        int conservativeVotes = (int)(constituency.voteResult17.get("Conservative").totalVotes * Const.CITIZENS_PER_POPULATION);
        int conservativeVotes = (int)(constituency.voteResult17.get("Conservative").totalVotes * Const.CITIZENS_PER_POPULATION);

        return citizens;
    }
}
