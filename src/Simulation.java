import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Simulation {
    private HashMap<String, Region> regions;
    private Virus virus;

    Simulation(String presetFilePath){
        regions = new HashMap<String,Region>();

        //construct from file
        File presetFile = new File(presetFilePath);
        try{
            Scanner sc = new Scanner(presetFile);
            while (sc.hasNextLine()){
                String[] header = sc.nextLine().split(":");
                switch (header[0].trim().toLowerCase()){
                    case "simulation":
                        break;
                    case "region":
                        //construct a visual object and put it in the has map
                        String stateName = header[1].trim().toLowerCase();
                        regions.put(stateName,new Region(sc));
                        break;
                    case "virus":
                        this.virus = new Virus(sc);
                        break;
                }
            }
        } catch (FileNotFoundException e){
            // Invalid Map path prompts user to renter path
            System.out.println("Invalid File Path");
        } catch (Exception e){
            //invalid preset syntax causes program to exit
            System.out.println("Invalid Preset Syntax");
            e.printStackTrace();
            System.exit(1);
        }
    }

    double getPercentInfected(String regionName){
        return regions.get(regionName).getPercentInfected();
    }

    void updateSimulation() {
        updateInfected();
        //updateDead();
        updateSpread();

    }

    void updateInfected() {
        // Looping through all regions.
        for(Region region : regions.values()) {
            // Updating number of infected in region, max value being the population size.
            region.addInfected(region.infected * virus.r0);
        }

    }

    void updateDead() {
        // Looping through all regions.
        for(Region region : regions.values()) {

        }

    }

    void updateSpread() {
        // Stores the spread to be applied at once.
        HashMap<String, Integer> regionSpread = new HashMap<>();
        
        // Looping through all regions, calculating the spread.
        for(String regionName : regions.keySet()) {
            Region region = regions.get(regionName);
            
            // Skip if region is already infected.
            if(region.infected > 0) continue;

            // Looping through all of a region's neighbors.
            Region neighbor;
            for(String neighborName : region.neighbors) {
                neighbor = regions.get(neighborName); // Getting a neighbor.
                if(neighbor.infected <= 0) continue; // Skip if neighbor has no infected.
                
                // Calculating and Storing spread.
                int spread = regionSpread.getOrDefault(regionName,0) + (int) ((float) neighbor.getPercentInfected() * virus.r0 * 100);
                regionSpread.put(regionName, spread);
            }
        }

        // Looping through all regions, applying the spead.
        for(String regionName : regions.keySet()) {
            Region region = regions.get(regionName);

            // Skip if region is already infected.
            if(region.infected > 0) continue;
            
            // Applying spread to region.
            region.addInfected(regionSpread.getOrDefault(regionName, 0));
        }
    }

    public HashMap<String,Region> getRegions(){
        return (HashMap<String, Region>) regions.clone();
    }

}
