import java.util.Scanner;
public class State {
    int population;
    int infected;
    String[] neighbors;

    //constructed from file
    public State(Scanner sc){
        try{
            this.population = Integer.parseInt(sc.nextLine().split("=")[1].trim().replace(",",""));
            this.infected = Integer.parseInt(sc.nextLine().split("=")[1].trim().replace(",",""));
            this.neighbors = sc.nextLine().split("=")[1].split(",");
            assert neighbors.length > 0;
            for (int i=0; i<neighbors.length; i++) {
                neighbors[i] = neighbors[i].trim();
            }
        } catch (Exception e){
            //invalid syntax causes program to terminate
            System.out.println("Invalid Preset File Syntax");
            System.exit(1);
        }
    }

    //put some methods in here to change the population and infected people

    public double getPercentInfected(){
        return (double)infected/population;
    }
}
