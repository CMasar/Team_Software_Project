import java.util.ArrayList;

public class Simulation {

    ArrayList<State> states;
    ArrayList<Shape> shapes;

    public void update(int days){
        for(int i=0; i<days; i++){
            for (State state : states){
                state.infectNeighbors();
            }
            for (State state : states){
                state.update();
            }
            for (Shape shape : shapes){
                shape.update();
            }
        }
    }
}
