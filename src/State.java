import java.util.Scanner;
public class State {
    int population;
    int infected;
    String[] neighbors;
    private int pushed;

    public State(Scanner sc){
        this.population = sc.nextInt();
        sc.nextLine();
        this.neighbors = sc.nextLine().split(",");
        this.infected = 0;
        this.pushed = 0;

    }

    public void kill(int death){

    }

    public void pushInfected(int newCases) {

    }

    public void updateInfected(){

    }

    public String toString(){
        String str = "{";
        str += String.valueOf(population) +", " + String.valueOf(infected);
        for (String n : neighbors){
            str += ", " + n;
        }
        str += "}";
        return str;
    }
}
