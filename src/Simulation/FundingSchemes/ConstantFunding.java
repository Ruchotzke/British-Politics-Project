package Simulation.FundingSchemes;

/**
 * A funding scheme where a constant amount of money is spent each day.
 */
public class ConstantFunding implements IFundingScheme {

    public final int amount;

    public ConstantFunding(int amount){
        this.amount = amount;
    }

    @Override
    public int getSpending(int day) {
        return amount;
    }
}
