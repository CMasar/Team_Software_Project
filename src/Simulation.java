import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Simulation {
    HashMap<String, State> states;
    double infectionRate;
    double mortalityRate;

    public Simulation(String filePath){
        states = new HashMap<String, State>();
        Scanner sc = safeOpen(filePath);

        while (sc.hasNextLine()) {
            String ln = sc.nextLine();
            if (!ln.isEmpty()){
                String[] pair = ln.split(":",2);
                switch (pair[0]){
                    case "Simulation":
                        this.infectionRate = sc.nextDouble();
                        this.mortalityRate = sc.nextDouble();
                        break;
                    case "State":
                        String name = pair[1];
                        State state = new State(sc);
                        states.put(name,state);
                        break;
                }
            }
        }

        sc.close();
    }

    private Scanner safeOpen(String filePath){
        File file = new File(filePath);

        Scanner sc = null;

        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return sc;
    }

    public void simulate(int steps){

    }

    public double getPercentInfected(String stateName){
        return 0;
    }

    private void stateToNeighborInfections(){

    }

    private void stateToSelfInfections(){

    }

    private void stateToSelfDeaths(){

    }

    public String toString(){
        String str = "{";
        str += String.valueOf(infectionRate) + ", " + String.valueOf(mortalityRate) + ", " + String.valueOf(states);
        str += "}";
        return str;
    }
}
