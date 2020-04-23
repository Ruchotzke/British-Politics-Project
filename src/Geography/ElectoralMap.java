package Geography;

import Data.Database;
import Data.Row;
import Simulation.FundingSchemes.ConstantFunding;
import Simulation.Party;
import Simulation.UK_PARTY;
import Utilities.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The electoral map is the culmination of all
 * constituencies.
 */
public class ElectoralMap {
    private HashMap<String, Constituency> name_constituencies;                  /* Constituencies by name */
    private HashMap<Region, ArrayList<Constituency>> region_constituencies;     /* Constituencies by Region */
    private HashMap<Country, ArrayList<Constituency>> country_constituencies;   /* Constituencies by Country */

    public ArrayList<Party> politicalParties;

    /* Results Information */
    public HashMap<Party, Integer> individual_votes;
    public HashMap<Party, Integer> electoral_seats;

    public ElectoralMap(){
        name_constituencies = new HashMap<>();
        region_constituencies = new HashMap<>();
        country_constituencies = new HashMap<>();
    }

    public void buildElectoralMap(Database db){
        for(Map.Entry row_db : db.data.entrySet()){
            Row row = ((Row)row_db.getValue());

            String name = row.getField("constituency_name");
            int size = row.getIntegerField("electorate17");
            double turnout = row.getDoubleField("Turnout17") / 100.0;
            Region region = getRegion(row.getField("region_name"));
            Country country = getCountry(row.getField("country_name"));
            HashMap<String, PartyVote> votes = getElectoralData(row, (int)(size * turnout));

            Constituency c = new Constituency(name, size, turnout, country, region, votes);
            insertConstituency(c);
        }
        System.out.println("Map built with " + name_constituencies.size() + " constituencies.");
    }

    public void buildParties(){
        politicalParties = new ArrayList<>();
        politicalParties.add(new Party(UK_PARTY.Conservative, new ConstantFunding(0)));
        politicalParties.add(new Party(UK_PARTY.Labour, new ConstantFunding(60)));
        politicalParties.add(new Party(UK_PARTY.Liberal_Democrats, new ConstantFunding(60)));
        politicalParties.add(new Party(UK_PARTY.SNP, new ConstantFunding(60)));
    }

    /**
     * Pull electoral data from the database for a given row.
     * @param r
     * @param turnout
     * @return
     */
    private static HashMap<String, PartyVote> getElectoralData(Row r, int turnout){
        HashMap<String, PartyVote> votes = new HashMap<>();

        int cons = r.getIntegerField("convote17");
        int labo = r.getIntegerField("labvote17");
        int ld = r.getIntegerField("ldvote17");
        int ukip = r.getIntegerField("ukipvote17");
        int green = r.getIntegerField("greenvote17");
        int snp = r.getIntegerField("snpvote17");
        int pc = r.getIntegerField("pcvote17");
        int dup = r.getIntegerField("dupvote17");
        int sf = r.getIntegerField("sfvote17");
        int sdlp = r.getIntegerField("sdlpvote17");
        int uup = r.getIntegerField("uupvote17");
        int alliance = r.getIntegerField("alliancevote17");
        int other = r.getIntegerField("othervote17");

        votes.put("Conservative"    , new PartyVote("Conservative", cons, ((double)cons) / turnout));
        votes.put("Labour"          , new PartyVote("Labour", labo, ((double)labo) / turnout));
        votes.put("Liberal Democrats", new PartyVote("Liberal Democrats", ld, ((double)ld) / turnout));
        votes.put("UKIP"            , new PartyVote("UKIP", ukip, ((double)ukip) / turnout));
        votes.put("Green"           , new PartyVote("Green", green, ((double)green) / turnout));
        votes.put("SNP"             , new PartyVote("SNP", snp, ((double)snp) / turnout));
        votes.put("Plaid Cymru"     , new PartyVote("Plaid Cymru", pc, ((double)pc) / turnout));
        votes.put("DUP"             , new PartyVote("DUP", dup, ((double)dup) / turnout));
        votes.put("Sinn Fein"       , new PartyVote("Sinn Fein", sf, ((double)sf) / turnout));
        votes.put("SDLP"            , new PartyVote("SDLP", sdlp, ((double)sdlp) / turnout));
        votes.put("UUP"             , new PartyVote("UUP", uup, ((double)uup) / turnout));
        votes.put("Alliance"        , new PartyVote("Alliance", alliance, ((double)alliance) / turnout));
        votes.put("Other"           , new PartyVote("Other", other, ((double)other) / turnout));

        return votes;
    }

    public static Country getCountry(String s){
        switch(s.toLowerCase()){
            case "england":
                return Country.England;
            case "Northern Ireland":
                return Country.Northern_Ireland;
            case "Scotland":
                return Country.Scotland;
            case "Wales":
                return Country.Wales;
            default:
                return Country.Other;
        }
    }

    public static Region getRegion(String s){
        switch(s.toLowerCase()){
            case "east":
                return Region.East;
            case "london":
                return Region.London;
            case "north east":
                return Region.North_East;
            case "north west":
                return Region.North_West;
            case "scotland":
                return Region.Scotland;
            case "south east":
                return Region.South_East;
            case "south west":
                return Region.South_West;
            case "wales":
                return Region.Wales;
            case "west midlands":
                return Region.West_Midlands;
            case "yorkshire and the humber":
                return Region.Yorkshire_and_The_Humber;
            case "northern ireland":
                return Region.Northern_Ireland;
            default:
                return Region.Other;
        }
    }

    /**
     * Insert a constituency into this electoral map.
     * @param c
     */
    public void insertConstituency(Constituency c){
        name_constituencies.put(c.name.toLowerCase(), c); //name insertion is easy

        if(!region_constituencies.containsKey(c.region)){
            region_constituencies.put(c.region, new ArrayList<>());
        }

        if(!country_constituencies.containsKey(c.country)){
            country_constituencies.put(c.country, new ArrayList<>());
        }

        region_constituencies.get(c.region).add(c);
        country_constituencies.get(c.country).add(c);
    }

    /**
     * Get the constituency by its name.
     * @param name The name of the selected constituency. Caps do not matter.
     * @return Null if it does not exist, else true.
     */
    public Constituency getByName(String name){
        if(name_constituencies.containsKey(name.toLowerCase())){
            return name_constituencies.get(name.toLowerCase());
        } else {
            return null;
        }
    }

    /**
     * Get all constituencies in a region.
     * @param r The region.
     * @return Null if not found.
     */
    public ArrayList<Constituency> getByRegion(Region r){
        if(region_constituencies.containsKey(r)){
            return region_constituencies.get(r);
        } else {
            return null;
        }
    }

    /**
     * Get all constituencies in a region.
     * @param c The nation.
     * @return Null if not found.
     */
    public ArrayList<Constituency> getByCountry(Country c){
        if(country_constituencies.containsKey(c)){
            return country_constituencies.get(c);
        } else {
            return null;
        }
    }

    public int getSize(){
        int total = 0;
        for(Map.Entry constit : name_constituencies.entrySet()){
            Constituency c = (Constituency) constit.getValue();
            total += c.citizenList.size();
        }
        return total;
    }

    public ArrayList<Constituency> getAllConstituencies(){
        ArrayList<Constituency> arr = new ArrayList<>();

        for(Map.Entry constit : name_constituencies.entrySet()){
            Constituency c = (Constituency) constit.getValue();
            arr.add(c);
        }

        return arr;
    }

    public double getAverageIdeology(){
        ArrayList<Constituency> consts = getAllConstituencies();
        double total = 0;
        for(Constituency c : consts){
            total += c.getAverage();
        }

        return total / consts.size();
    }

    public double getAverageEffectiveIdeology(){
        ArrayList<Constituency> consts = getAllConstituencies();
        double total = 0;
        for(Constituency c : consts){
            total += c.getEffectiveAverage();
        }

        return total / consts.size();
    }

    public void simulateOneDay(){
        ArrayList<Constituency> constituencies = getAllConstituencies();

        //First do communications in each constituency, then do party broadcasts
        for(Constituency c : constituencies){
            c.inConstituencyCommunication(Const.AVERAGE_COMMUNICATIONS);
            for(Party p : politicalParties){
                c.inConstituencyBroadcast(p.partyBroadcast());
            }
        }

    }

    public Constituency getLeftmostConstituency(){
        ArrayList<Constituency> constituencies = getAllConstituencies();
        Constituency left = constituencies.get(0);
        for(Constituency c : constituencies){
            if(c.getAverage() < left.getAverage()) left = c;
        }

        return left;
    }

    public Constituency getRightmostConstituency(){
        ArrayList<Constituency> constituencies = getAllConstituencies();
        Constituency right = constituencies.get(0);
        for(Constituency c : constituencies){
            if(c.getAverage() > right.getAverage()) right = c;
        }

        return right;
    }

    /**
     * Trigger an election, and compile the results into a hashmap.
     * @return
     */
    public void electionResults(){
        individual_votes = new HashMap<>();
        electoral_seats = new HashMap<>();

        //first put all of the parties in
        for(Party p : politicalParties){
            individual_votes.put(p, 0);
            electoral_seats.put(p, 0);
        }

        //now vote in each constituency, compiling the results as we go.
        ArrayList<Constituency> constituencies = getAllConstituencies();
        for(Constituency c : constituencies){
            HashMap<Party, Integer> constituencyResults = c.electionResults(politicalParties);
            //find the winner (FPTP)
            //put the individual votes into the appropriate slot
            Party winner = null;
            int winnerVotes = 0;
            for(Party p : politicalParties){
                if(constituencyResults.containsKey(p)){
                    individual_votes.put(p, individual_votes.get(p) + constituencyResults.get(p));
                    if(constituencyResults.get(p) > winnerVotes){
                        winnerVotes = constituencyResults.get(p);
                        winner = p;
                    }
                }
            }
            electoral_seats.put(winner, electoral_seats.get(winner) + 1);
        }
    }
}
