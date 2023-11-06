import java.util.Scanner;

public class Virus {
    int r0;
    int mortalityRate;
    int duration;
    boolean immunity;

    public Virus(Scanner sc) {
        try{
            this.r0 = Integer.parseInt(sc.nextLine().split("=")[1].trim().replace(",",""));
            this.mortalityRate = Integer.parseInt(sc.nextLine().split("=")[1].trim().replace(",",""));
            this.duration = Integer.parseInt(sc.nextLine().split("=")[1].trim().replace(",",""));
            this.immunity = Boolean.parseBoolean(sc.nextLine().split("=")[1].trim().replace(",","")); //parse boolean, might need input checking
        } catch (Exception e){
            //invalid syntax causes program to terminate
            System.out.println("Invalid Preset File Syntax");
            System.exit(1);
        }
    }

    void Evolve(int r0, int mortalityRate, int duration, boolean immunity)
    {
        this.r0 = r0;
        this.mortalityRate = mortalityRate;
        this.duration = duration;
        this.immunity = immunity;
    }
}
