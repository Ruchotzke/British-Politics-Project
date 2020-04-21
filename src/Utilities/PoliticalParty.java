package Utilities;

public interface PoliticalParty {

    /**
     * Get the name of this party.
     * @return
     */
    String getName();

    /**
     * Get the ideology of this party.
     * @return 0 if far left, 2 if far right.
     */
    double getIdeology();
}
