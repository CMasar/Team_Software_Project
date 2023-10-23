import java.util.HashMap;

public class GUI {
    HashMap<String,VisualObject> visuals;
    Simulation simulation;

    public GUI(String filePath) {
        simulation = new Simulation(filePath);
    }

    public void start(){

    }
}
