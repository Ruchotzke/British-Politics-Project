package Geography;

/**
 * A small struct-class to hold information about voting in
 * a constituency.
 */
public class PartyVote {

    public final String partyName;
    public final int totalVotes;
    public final double voteShare;

    public PartyVote(String partyName, int totalVotes, double voteShare) {
        this.partyName = partyName;
        this.totalVotes = totalVotes;
        this.voteShare = voteShare;
    }
}
