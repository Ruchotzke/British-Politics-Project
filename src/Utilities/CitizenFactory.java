package Utilities;

import Geography.Constituency;
import Simulation.Citizen;

import java.util.ArrayList;

public class CitizenFactory {

    public static ArrayList<Citizen> generateConstituencyCitizens(Constituency constituency){
        ArrayList<Citizen> citizens = new ArrayList<>();

        int citizensToGenerate = (int)(constituency.constituencySize * Const.CITIZENS_PER_POPULATION);

        double[] ideologies = {
          Const.IDEOLOGY_CONSERVATIVE,
          Const.IDEOLOGY_LABOUR,
          Const.IDEOLOGY_SNP,
        };


        return citizens;
    }
}
