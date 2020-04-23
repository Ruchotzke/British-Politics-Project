package Simulation;

public class PartyBroadcast implements IBroadcast {

    private double ideology;
    private int cost;
    private double strength;

    public PartyBroadcast(int cost, double ideology){
        this.cost = cost;
        this.ideology = ideology;
        this.strength = 1 - 1/(0.05 * cost + 1); //map [0,inf] to [0,1]
    }

    @Override
    public double getStrength() {
        return 0;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public double getIdeology() {
        return 0;
    }
}
