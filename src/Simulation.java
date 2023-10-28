import java.util.HashMap;
public class Simulation {
    HashMap<String, State> states;
    double infectionRate;
    double mortalityRate;

    //construction from file
    public Simulation(){
        //constructs the state class variables
    }

    //called from GUI
    public void incrementMonth(){
        //state to neighbor infections
        //state to self infections
        //state to self deaths
    }

    //called from GUI on each state
    public double getPercentInfected(String stateName){
        return states.get(stateName).getPercentInfected();
    }
}
