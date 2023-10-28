
public class State {
    int population;
    int infected;
    String[] neighbors;

    //constructed from file
    public State(){
    }

    //put some methods in here to change the population and infected people

    public double getPercentInfected(){
        return (double)infected/population;
    }
}
