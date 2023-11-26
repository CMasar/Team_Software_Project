import java.util.Scanner;
import java.awt.Color;

public class VisualObject {
    final static int requiredParameters = 4;
    int x;
    int y;
    int width;
    int height;
    
    String rgb = "#0000FF";

    //from preset file
    public VisualObject(Scanner sc){
        //read parameters from file
        try{
            int flag = 0;
            read:
            while(sc.hasNextLine()){
                //read lines of form "name = value"
                String[] line = sc.nextLine().split("=");
                String name = line[0].trim();
                String value = (line.length>1) ? line[1].trim() : "";
                switch (name.toLowerCase()) { //switch on name
                    case "x":
                        this.x = Integer.parseInt(value);
                        flag = flag | 1;
                        break;
                    case "y":
                        this.y = Integer.parseInt(value);
                        flag = flag | 2;
                        break;
                    case "width":
                        this.width = Integer.parseInt(value);
                        flag = flag | 4;
                        break;
                    case "height":
                        this.height = Integer.parseInt(value);
                        flag = flag | 8;
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


        //TODO: construct rectangle
    }

    public Color updateColor(double percentInfected){
        int red = 255;
        int green = (int) (255 + percentInfected*(-255));
        int blue = (int) (255 + percentInfected*(-255));

        // Stores the r, g, b color values to a hex value.
        rgb = String.format("#%02x%02x%02x", red, green, blue);

        return Color.decode(rgb);
    }
}
