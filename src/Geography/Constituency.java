package Geography;

import Simulation.Citizen;
import Utilities.Const;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * A constituency is a geographic location with demographic data.
 */
public class Constituency {

    public final String name;                                   /* The name of this constituency */

    public final int constituencySize;                          /* The size of this constituency in # of people */
    public final double turnout;                                /* The turnout percentage for this constituency */

    public final Country country;                               /* This constituencies geographic nation */
    public final Region region;                                 /* The region for this constituency */

    public final HashMap<String, PartyVote> voteResult17;       /* The results of the vote for this constituency in 2017 */

    public List<Citizen> citizenList;                           /* The list of simulated civilians */

    /**
     * Construct a new constituency profile.
     * @param constituencySize
     * @param turnout
     * @param country
     * @param region
     * @param voteResult17
     */
    public Constituency(String name, int constituencySize, double turnout, Country country, Region region, HashMap<String, PartyVote> voteResult17) {
        this.name = name;
        this.constituencySize = constituencySize;
        this.turnout = turnout;
        this.country = country;
        this.region = region;
        this.voteResult17 = voteResult17;

        citizenList = generateCitizens();

//        System.out.println(name + " has been generated with " + citizenList.size() + " citizens.");
    }

    private List<Citizen> generateCitizens(){
        LinkedList<Citizen> arr = new LinkedList<>();

        int totalCitizens = (int)(Const.CITIZENS_PER_POPULATION * constituencySize);

        int activeCitizens = (int)(turnout * constituencySize);
        int inactiveCitizens = totalCitizens - activeCitizens;

        int numGenerated = 0;
        int oldTotal;

        //generate the conservative voters
        for(;numGenerated < voteResult17.get("Conservative").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_CONSERVATIVE, 1));
        }
        oldTotal = numGenerated;

        //now labour
        for(;numGenerated < oldTotal + voteResult17.get("Conservative").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_LABOUR, 1));
        }
        oldTotal = numGenerated;

        //now LibDem
        for(;numGenerated < oldTotal + voteResult17.get("Liberal Democrats").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_LIBDEM, 1));
        }
        oldTotal = numGenerated;

        //now UKIP
        for(;numGenerated < oldTotal + voteResult17.get("UKIP").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_UKIP, 1));
        }
        oldTotal = numGenerated;

        //now Greens
        for(;numGenerated < oldTotal + voteResult17.get("Green").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_GREEN, 1));
        }
        oldTotal = numGenerated;

        //now SNP
        for(;numGenerated < oldTotal + voteResult17.get("SNP").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_SNP, 1));
        }
        oldTotal = numGenerated;

        //now PC
        for(;numGenerated < oldTotal + voteResult17.get("Plaid Cymru").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_PC, 1));
        }
        oldTotal = numGenerated;

        //now DUP
        for(;numGenerated < oldTotal + voteResult17.get("DUP").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_DUP, 1));
        }
        oldTotal = numGenerated;

        //now Sinn Fein
        for(;numGenerated < oldTotal + voteResult17.get("Sinn Fein").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_SINNFEIN, 1));
        }
        oldTotal = numGenerated;

        //now SDLP
        for(;numGenerated < oldTotal + voteResult17.get("SDLP").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_SDLP, 1));
        }
        oldTotal = numGenerated;

        //now UUP
        for(;numGenerated < oldTotal + voteResult17.get("UUP").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_UUP, 1));
        }
        oldTotal = numGenerated;

        //now Alliance
        for(;numGenerated < oldTotal + voteResult17.get("Alliance").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_ALLIANCE, 1));
        }
        oldTotal = numGenerated;

        //the rest are all "other" people
        for(; numGenerated < totalCitizens; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_UNALIGNED, 1));
        }

        return arr;
    }
}
