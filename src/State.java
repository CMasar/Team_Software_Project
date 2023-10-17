import java.util.ArrayList;
public class State {
    private int population;
    private int infected;
    private int dead;
    private double infectionRate;
    private double mortalityRate;

    private ArrayList<State> neighbors;
    private int newCases;

    public State(int population) {
        this.population = population;
        this.infected = 0;
        this.dead = 0;
        this.infectionRate = 0.1;
        this.mortalityRate = 0.01;
    }

    public int getPopulation() {
        return population;
    }

    public int getInfected() {
        return infected;
    }

    public void infect(int cases){
        newCases += infectionRate * cases;
    }

    private void increaseInfection() {
        infected += newCases;
        newCases = 0;
        if (infected > population) {
            infected = population;
        }
    }

    public double getInfectedPercentage() {
        return (double) infected / population;
    }

    public void infectNeighbors(){
        for (State state : neighbors){
            state.infect(infected);
        }
    }

    public void update(){
        int death = (int) (mortalityRate * infected);
        population -= death;
        infected -= death;
        dead += death;
        newCases += infectionRate * infected;

        increaseInfection();
    }
}
