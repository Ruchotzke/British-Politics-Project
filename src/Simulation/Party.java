package Simulation;

import Simulation.FundingSchemes.IFundingScheme;
import Utilities.Const;

/**
 * A political party is any entity attempting to increase its
 * share of votes.
 */
public class Party {

    public final String name;
    public final double ideology;
    public int funding; //base + party dues

    private int dayOfSimulation = 0;
    private IFundingScheme fundingScheme;

    /**
     * Construct a new party using the supplied identifier.
     * @param party
     */
    public Party(UK_PARTY party, IFundingScheme fundingScheme){
        this.fundingScheme = fundingScheme;
        switch(party){
            case Conservative:
                name = "Conservative";
                ideology = Const.IDEOLOGY_CONSERVATIVE;
                funding = 100 + (int)(1000 * Const.POPULAR_CONSERVATIVE);
                break;
            case Labour:
                name = "Labour";
                ideology = Const.IDEOLOGY_LABOUR;
                funding = 100 + (int)(1000 * Const.POPULAR_LABOUR);
                break;
            case Liberal_Democrats:
                name = "Liberal Democrats";
                ideology = Const.IDEOLOGY_LIBDEM;
                funding = 100 + (int)(1000 * Const.POPULAR_LIBDEM);
                break;
            case UKIP:
                name = "United Kingdom Independence Party";
                ideology = Const.IDEOLOGY_UKIP;
                funding = 100 + (int)(1000 * Const.POPULAR_UKIP);
                break;
            case Green:
                name = "Green";
                ideology = Const.IDEOLOGY_GREEN;
                funding = 100 + (int)(1000 * Const.POPULAR_GREEN);
                break;
            case SNP:
                name = "Scottish National Party";
                ideology = Const.IDEOLOGY_SNP;
                funding = 100 + (int)(1000 * Const.POPULAR_SNP);
                break;
            case Plaid_Cymru:
                name = "Plaid Cymru";
                ideology = Const.IDEOLOGY_PC;
                funding = 100 + (int)(1000 * Const.POPULAR_PC);
                break;
            case DUP:
                name = "Democratic Unionist Party";
                ideology = Const.IDEOLOGY_DUP;
                funding = 100 + (int)(1000 * Const.POPULAR_DUP);
                break;
            case Sinn_Fein:
                name = "Sinn Fein";
                ideology = Const.IDEOLOGY_SINNFEIN;
                funding = 100 + (int)(1000 * Const.POPULAR_SINNFEIN);
                break;
            case SDLP:
                name = "Social Democratic Liberal Party";
                ideology = Const.IDEOLOGY_SDLP;
                funding = 100 + (int)(1000 * Const.POPULAR_SDLP);
                break;
            case UUP:
                name = "Ulster Unionist Party";
                ideology = Const.IDEOLOGY_UUP;
                funding = 100 + (int)(1000 * Const.POPULAR_UUP);
                break;
            case Alliance:
                name = "Alliance of Northern Ireland";
                ideology = Const.IDEOLOGY_ALLIANCE;
                funding = 100 + (int)(1000 * Const.POPULAR_ALLIANCE);
                break;
            default:
                name = "Other";
                ideology = Const.IDEOLOGY_UNALIGNED;
                funding = 100;
                break;
        }

    }

    public void incrementDay(){
        dayOfSimulation += 1;
    }

    public IBroadcast partyBroadcast(){
        if(funding > 0){
            funding -= fundingScheme.getSpending(dayOfSimulation);
            return new PartyBroadcast(fundingScheme.getSpending(dayOfSimulation), ideology);
        } else {
            return new PartyBroadcast(0, ideology); //no more money :(
        }
    }
}
