import java.util.ArrayList;
public class State {

    String name;
    int alive;
    int dead;
    int infected;
    ArrayList<State> neighbors;
    private int pushed;

    public void infectNeighbors(){

    }

    public void push(int newCases){

    }

    public void update(){
        death();
        infectionFromNeighbors();
        infectionFromSelf();
    }

    private void death(){

    }

    private void infectionFromNeighbors(){

    }

    private void infectionFromSelf(){

    }

    public String toString(){
        return name+":"+String.valueOf(infected);
    }
}
