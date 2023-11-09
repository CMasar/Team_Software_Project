import java.util.Scanner;
public class Region {
    final static int numParameters = 2;
    int population;

    String[] neighbors;
    int infected=0;
    int dead=0;
    int immune=0;


    //constructed from file
    public Region(Scanner sc){
        try{
            int flag = 0;
            for (int i =0; i<numParameters; i++){
                //read lines of form "name = value"
                String[] line = sc.nextLine().split("=");
                String name = line[0].trim();
                String value = line[1].trim();
                switch (name.toLowerCase()) { //switch on name
                    case "population":
                        this.population = Integer.parseInt(value.replace(",",""));
                        flag = flag | 1;
                        break;
                    case "neighbors":
                        this.neighbors = value.split(",");
                        assert neighbors.length > 0;
                        for (int j=0; j<neighbors.length; j++) {
                            neighbors[j] = neighbors[j].trim();
                        }
                        flag = flag | 1;
                        break;
                    case "infected":
                        this.infected = Integer.parseInt(value);
                        break;
                    case "dead":
                        this.dead = Integer.parseInt(value);
                        break;
                    case "immune":
                        this.immune = Integer.parseInt(value);
                        break;
                    default: throw new Exception();
                }
            }
            //check that everything has been initialized
            if (!(flag == (Math.pow(2,numParameters)-1))){
                throw new Exception();
            }
        } catch (Exception e){
            //invalid syntax causes program to terminate
            System.out.println("Invalid Preset File Syntax");
            e.printStackTrace();
            System.exit(1);
        }

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
