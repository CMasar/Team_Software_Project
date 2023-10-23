import java.util.Scanner;
public class VisualObject {
    Object graphicalObject;

    public VisualObject(Scanner sc){
        graphicalObject = sc.nextLine();
    }
    public void update(double percentInfected){
        System.out.println(graphicalObject);
    }

    public String toString(){
        return String.valueOf(graphicalObject);
    }
}
