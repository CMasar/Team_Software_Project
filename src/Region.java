import java.util.Scanner;

public class Region {
    final static int requiredParameters = 2;
    long population;
    long vulnPopulation;

    String[] neighbors;
    long infected=0;
    int dead=0;
    int immune=0;
    Response response;


    //constructed from file
    public Region(Scanner sc){
        //construct from file
        try{
            response = new Response(3, 100); // Debug Response
            int flag = 0;
            read:
            while (sc.hasNextLine()){
                //read lines of form "name = value"
                String[] line = sc.nextLine().split("=");
                String name = line[0].trim();
                String value = (line.length>1) ? line[1].trim() : "";
                switch (name.toLowerCase()) { //switch on name of parameter
                    case "population":
                        this.population = Integer.parseInt(value.replace(",",""));
                        vulnPopulation = population; // Differentiate vulnerable vs. total population
                        flag = flag | 1;
                        break;
                    case "neighbors":
                        this.neighbors = value.split(",");
                        assert neighbors.length > 0;
                        for (int j=0; j<neighbors.length; j++) {
                            neighbors[j] = neighbors[j].trim().toLowerCase();
                        }
                        flag = flag | 2;
                        break;
                    case "infected":
                        this.infected = Integer.parseInt(value.replace(",",""));
                        break;
                    case "dead":
                        this.dead = Integer.parseInt(value);
                        break;
                    case "immune":
                        this.immune = Integer.parseInt(value);
                        break;
                    case "":
                        break read;
                    default: throw new Exception();
                }
            }
            //check that everything has been initialized
            if (!(flag == (Math.pow(2, requiredParameters)-1))){
                throw new Exception();
            }
        } catch (Exception e){
            //invalid syntax causes program to terminate
            System.out.println("Invalid Preset File Syntax");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void addInfected(long newInfections){
        infected += newInfections;
        vulnPopulation -= infected; // Decrease vulnerable population
        if (infected>vulnPopulation && vulnPopulation >= 0) {
            infected += vulnPopulation;
            vulnPopulation = 0;
        }
    }

    public double getPercentInfected(){
        return (double)infected/population;
    }
    // Potential immunity method
    public void addImmune(){
        immune += response.rateOfVaccination;
        vulnPopulation -= immune;
        if (immune > vulnPopulation && vulnPopulation >= 0)
            immune += vulnPopulation;
            vulnPopulation = 0; 
    }

    public void startVaccine(){

    }
}
