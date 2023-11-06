import java.util.Scanner;
public class Response {
int cureDevelopmentTime;
int rateOfVaccination;
    public Response(Scanner sc){
        try{
            this.cureDevelopmentTime = Integer.parseInt(sc.nextLine().split("=")[1].trim().replace(",",""));
            this.rateOfVaccination = Integer.parseInt(sc.nextLine().split("=")[1].trim().replace(",",""));
        } catch (Exception e){
            //invalid syntax causes program to terminate
            System.out.println("Invalid Preset File Syntax");
            System.exit(1);
        }
    }

    //Change response after initial construction
    void setResponse(int cureDevelopmentTime, int rateOfVaccination){
        this.cureDevelopmentTime = cureDevelopmentTime;
        this.rateOfVaccination = rateOfVaccination;
    }

}

