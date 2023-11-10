import java.util.Scanner;

public class Virus {
    final static int numParameters = 4;
    int r0;
    int mortalityRate;
    int duration;
    boolean immunity;

    public Virus(Scanner sc) {
        //construct from file
        try{
            int flag = 0;
            for (int i =0; i<numParameters; i++){
                //read lines of form "name = value"
                String[] line = sc.nextLine().split("=");
                String name = line[0].trim();
                String value = line[1].trim();
                switch (name.toLowerCase()) { //switch on name of parameter
                    case "r0":
                        this.r0 = Integer.parseInt(value);
                        flag = flag | 1;
                        break;
                    case "mortality rate":
                        this.mortalityRate = Integer.parseInt(value);
                        flag = flag | 2;
                        break;
                    case "duration":
                        this.duration = Integer.parseInt(value);
                        flag = flag | 4;
                        break;
                    case "immunity":
                        this.immunity = Boolean.parseBoolean(value);
                        flag = flag | 8;
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
    }

    void Evolve(int r0, int mortalityRate, int duration, boolean immunity)
    {
        this.r0 = r0;
        this.mortalityRate = mortalityRate;
        this.duration = duration;
        this.immunity = immunity;
    }
}
