import java.util.Scanner;
public class Response {
    final static int requiredParameters = 2;
    int cureDevelopmentTime;
    int rateOfVaccination;
    public Response(Scanner sc){
        //construct from file
        try{
            int flag = 0;
            read:
            while (sc.hasNextLine()){
                //read lines of form "name = value"
                String[] line = sc.nextLine().split("=");
                String name = line[0].trim();
                String value = (line.length>1) ? line[1].trim() : "";
                switch (name.toLowerCase()) { //switch on name of parameter
                    case "cure development time":
                        this.cureDevelopmentTime = Integer.parseInt(value);
                        flag = flag | 1;
                        break;
                    case "rate of vacination":
                        this.rateOfVaccination = Integer.parseInt(value);
                        flag = flag | 2;
                        break;
                    case "":
                        break read;
                    default: throw new Exception();
                }
            }
            //check that everything has been initialized
            if (!(flag == (Math.pow(2, requiredParameters)-1))){
                throw new Exception();
            }
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

