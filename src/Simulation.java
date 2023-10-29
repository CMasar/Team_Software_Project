import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulation {
    HashMap<String, State> states;
    double infectionRate;
    double mortalityRate;

    //construction from file
    public Simulation(String filePath){
        //create scanner from file
        File file = new File(filePath);
        Scanner sc = null;
        try{
            sc = new Scanner(file);
        } catch(FileNotFoundException e){
            //invalid file path caused program to terminate
            System.out.println("Invalid File Path");
            System.exit(1);
        }

        states = new HashMap<String,State>();
        
        try {
            while (sc.hasNextLine()) {
                String[] header = sc.nextLine().split(":");
                switch (header[0].trim()) {
                    case "Simulation" -> {
                        //initialize simulation parameters
                        this.infectionRate = Double.parseDouble(sc.nextLine().split("=")[1].trim());
                        this.mortalityRate = Double.parseDouble(sc.nextLine().split("=")[1].trim());
                    }
                    case "State" -> {
                        //initialize state and put it into hash map
                        String name = header[1].trim();
                        states.put(name, new State(sc));
                    }
                }
            }
        } catch(Exception e){
            //invalid syntax causes program to terminate
            System.out.println("Invalid Preset File Syntax");
            System.exit(1);
        }

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
