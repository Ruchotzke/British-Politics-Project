package Geography;

import Simulation.Citizen;
import Simulation.IBroadcast;
import Simulation.Party;
import Utilities.Const;
import sun.management.StackTraceElementCompositeData;

import java.util.*;

import static Geography.ElectoralMap.removeIrishParties;

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
            arr.add(new Citizen(this, Const.IDEOLOGY_CONSERVATIVE, 1, 1));
        }
        oldTotal = numGenerated;

        //now labour
        for(;numGenerated < oldTotal + voteResult17.get("Conservative").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_LABOUR, 1, 1));
        }
        oldTotal = numGenerated;

        //now LibDem
        for(;numGenerated < oldTotal + voteResult17.get("Liberal Democrats").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_LIBDEM, 1, 1));
        }
        oldTotal = numGenerated;

        //now UKIP
        for(;numGenerated < oldTotal + voteResult17.get("UKIP").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_UKIP, 1, 1));
        }
        oldTotal = numGenerated;

        //now Greens
        for(;numGenerated < oldTotal + voteResult17.get("Green").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_GREEN, 1, 1));
        }
        oldTotal = numGenerated;

        //now SNP
        for(;numGenerated < oldTotal + voteResult17.get("SNP").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_SNP, 1, 1));
        }
        oldTotal = numGenerated;

        //now PC
        for(;numGenerated < oldTotal + voteResult17.get("Plaid Cymru").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_PC, 1, 1));
        }
        oldTotal = numGenerated;

        //now DUP
        for(;numGenerated < oldTotal + voteResult17.get("DUP").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_DUP, 1, 1));
        }
        oldTotal = numGenerated;

        //now Sinn Fein
        for(;numGenerated < oldTotal + voteResult17.get("Sinn Fein").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_SINNFEIN, 1, 1));
        }
        oldTotal = numGenerated;

        //now sinnFein
        for(;numGenerated < oldTotal + voteResult17.get("sinnFein").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_SDLP, 1, 1));
        }
        oldTotal = numGenerated;

        //now UUP
        for(;numGenerated < oldTotal + voteResult17.get("UUP").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_UUP, 1, 1));
        }
        oldTotal = numGenerated;

        //now Alliance
        for(;numGenerated < oldTotal + voteResult17.get("Alliance").totalVotes * Const.CITIZENS_PER_POPULATION; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_ALLIANCE, 1, 1));
        }
        oldTotal = numGenerated;

        //the rest are all "other" people
        for(; numGenerated < totalCitizens; numGenerated++){
            arr.add(new Citizen(this, Const.IDEOLOGY_UNALIGNED, 1, 1));
        }


        //now distribute activation, interactivity, and confidence.
        //TODO make this less random.
        //randomly sprinkle interactivity and activation
        Random rand = new Random();
        for(Citizen c : arr){
            c.interactivity = rand.nextDouble();
            c.activation = rand.nextDouble();
            c.confidence = rand.nextDouble();
        }

        return arr;
    }

    /**
     * Get the average ideology for the citizens in this
     * constituency.
     * @return
     */
    public double getAverage(){
        double total = 0;
        for(Citizen c : citizenList){
            total += c.ideology;
        }

        return total / citizenList.size();
    }

    public double getEffectiveAverage(){
        double total = 0;
        for(Citizen c : citizenList){
            total += c.getEffectiveIdeology();
        }

        return total / citizenList.size();
    }

    public void inConstituencyCommunication(int numConnections){
        Random rand = new Random();

        for(Citizen c : citizenList){
            //generate a "normal" amount of connections around the supplied value.
            int connections = (int)(numConnections * (rand.nextGaussian() + 1.0));
            for(int i = 0; i < connections; i++){
                c.communicateWith(citizenList.get(rand.nextInt(citizenList.size())));
            }
        }
    }

    public void inConstituencyBroadcast(IBroadcast broadcast){
        for(Citizen c : citizenList){
            c.takeBroadcast(broadcast);
        }
    }

    /**
     * Compile the electoral votes for this constituency.
     * @param options The options for this ballot.
     * @return A hashmap converting parties to votes.
     */
    public HashMap<Party, Integer> electionResults(ArrayList<Party> options){
        HashMap<Party, Integer> results = new HashMap<>();
        ArrayList<Party> newOptions = new ArrayList<Party>();
        newOptions.addAll(options);

        switch(country){
            case Northern_Ireland:
                newOptions = ElectoralMap.removeNonIrishParties(options);
                break;
            case England:
                newOptions = ElectoralMap.removeScottishParties(options);
                newOptions = ElectoralMap.removeIrishParties(newOptions);
                newOptions = ElectoralMap.removeWelshParties(newOptions);
                break;
            case Wales:
                newOptions = ElectoralMap.removeIrishParties(options);
                newOptions = ElectoralMap.removeScottishParties(newOptions);
                break;
            case Scotland:
                newOptions = ElectoralMap.removeWelshParties(options);
                newOptions = ElectoralMap.removeIrishParties(newOptions);
                break;
        }

        for(Citizen c : citizenList){
            Party choice = c.vote(newOptions);
            if(!results.containsKey(choice)) results.put(choice, 0);
            results.put(choice, results.get(choice) + 1);
        }

        return results;
    }


    @Override
    public String toString(){
        return "[Constituency] " + name + ", region: " + region + ", size: " + constituencySize + ", average: " + getAverage() + ".";
    }
}
