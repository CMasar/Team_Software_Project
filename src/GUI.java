import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class GUI {
    HashMap<String,VisualObject> visuals;
    Simulation simulation;

    public GUI(String filePath) {
        this.simulation = new Simulation(filePath);

        this.visuals = new HashMap<String, VisualObject>();
        Scanner sc = safeOpen(filePath);

        while (sc.hasNextLine()) {
            String ln = sc.nextLine();
            if (!ln.isEmpty()){
                String[] pair = ln.split(":",2);
                switch (pair[0]){
                    case "Visual":
                        String name = pair[1];
                        VisualObject visual = new VisualObject(sc);
                        visuals.put(name,visual);
                        break;
                }
            }
        }
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

    public void start(){
    }

    public String toString(){
        String str = "{";
        str += String.valueOf(visuals) + ", " + String.valueOf(simulation);
        str += "}";
        return str;
    }
}
