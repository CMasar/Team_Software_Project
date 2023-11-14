import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;


/*
 * Notes:
 * JFram --> Crates display frame: JFrame jframe = new JFrame( "JFrame title" );
 * 				Everything should be added to  the JFram for it to display
 * 				E.X. "jframe.add(jpanel, BorderLayout.SOUTH);" add joanel to fram and puts in on the botton of screen

 * ImageIcon --> import image to screen: ImageIcon imageIcom = new ImageIcon ( "image path location" );
 * Dimension --> Dimension(width, height) creates dimension of said width and height
 * 					May be used to setPreferredSize(new Dimension(width,height)) of screen
 * JPanel --> create panel to hold buttons: JPanel jpanel = new JPanel();
 * 				E.X. "jpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));" sets position of jpanel to be right so buttons will be on the right
 * 						by default, the buttons will be in the center of the screen.
 * JButton --> create button that will do something on screen: JButton jbutton = new JButton("Words on button");
 * 				add button: JPanelVarName.add(JButtonVarName);
 * 				To add functionally through method, "JButtonVarName.addActionListener(OtherVarName -> classVarName.referenceMethod());"
 *
 *
 *
 * Useful methods:
 * .getImage(); returns the ImageIcon's information; can be used to retrieve it's width and height
 * "GraphicsVarName".drawImage(mapImageVarName, x coordinate, y coordinate, ImageObserver imageObserver ); displays image on screen; use this for imageObserver to use the current screen
 * "GraphicsVarName".drawString( "...", x coordinate, y coordinate ); writes the string in the 1st parameter at coordinate (x,y)
 * JFram.add( new ClassName() ); adds the passed in class to the JFram to be displayed
 * JPanel.add( new JButton( "Words on JButton" ) ); adds the parameter button to the JPannel
 * JPanel.setLayout( new FlowLayout( Flowlayout.RIGHT ) ); FlowLayout class allows buttons to be positioned at either side of the JPanel
 * 				By default, the JPanel holds the buttons at the center
 * 					JPanel.setLayout ( new FlowLayout ( Flowlayout.LEFT ) ); shifts the buttons to the left
 * 					JPanel.setLayout( new FlowLayout ( Flowlayout.RIGHT ) ); shifts the buttons to the right
 * 					JPanel.setLayout ( new FlowLayout ( Flowlayout.CENTER ) ); has the buttons in the center
 * JFram.setExtendedState( JFrame.MAXIMIZED_BOTH ); opens the JFram at a maximized width and height ( opens at full screen )
 *
 *
 * Useful classes:
 *
 * BorderLayout: Controls the position of the JPanel
 * 						JFrame.add( JPanel, BorderLayout."POSITION" );
 * 						"Position" for BorderLayout can be either SOUTH, NORTH, EAST, or WEST in all capitalization
 *
 * FlowLayout: Controls the position of the JButton in the JPanel
 * 						JPanel.setLayout ( new FLowLayout ( FlowLayout."POSITION" );
 * 						"POSITION" in FlowLayout can be either RIGHT, LEFT, or CENTER in all capitalization
 */
public class GUI extends JPanel {
    String presetFilePath;
    HashMap<String,VisualObject> visuals;
    Simulation simulation;
    private Image mapImage;
    private String mapPath;

    private JPanel labelsPanel;

    private int currentMonth = 0;


    public GUI(String presetFilePath) {
        this.presetFilePath = presetFilePath;

        //rectangles
        visuals = new HashMap<String,VisualObject>();

        //construct from file
        File presetFile = new File(this.presetFilePath);
        try{
            Scanner sc = new Scanner(presetFile);
            while (sc.hasNextLine()){
                String[] header = sc.nextLine().split(":");
                switch (header[0].trim().toLowerCase()){
                    case "gui" -> {
                        visualConstruction(sc);
                    }
                    case "visual" -> {
                        //construct a visual object and put it in the has map
                        String stateName = header[1].trim().toLowerCase();
                        visuals.put(stateName,new VisualObject(sc));
                    }
                }
            }
        } catch (FileNotFoundException e){
            //invalid file path causes program to exit
            System.out.println("Invalid File Path");
            System.exit(1);
        } catch (Exception e){
            //invalid preset syntax causes program to exit
            System.out.println("Invalid Preset Syntax");
            System.exit(1);
        }

    }

    //construct the visual aspects of the object
    private void visualConstruction(Scanner sc){
        try{
            String[] line = sc.nextLine().split("=");
            String name = line[0].trim();
            String value = line[1].trim();
            if (name.toLowerCase().equals("map path")){
                this.mapPath = value;
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            System.out.println("Invalid Preset Syntax");
            e.printStackTrace();
            System.exit(1);
        }

        // Load an image of the United States and assign it to mapImage for later display
        ImageIcon imageIcon = new ImageIcon(mapPath);
        mapImage = imageIcon.getImage();

        // create an instance of JFrame called "United States Map"
        JFrame jframe = new JFrame("United States Map");
        // add a new simulation so the simulation class is the content of the jframe
        jframe.add(this);



        // Create a JPanel to hold the buttons
        JPanel jpanel = new JPanel();



        // Create start button
        JButton startButton = new JButton("Start Simulation");
        // add start button to the button panel
        jpanel.add(startButton);

        // create end simulation button
        JButton endButton = new JButton("End Simulation");
        // add endButton to button panel
        jpanel.add(endButton);


        // create increment month button
        JButton incrementButton = new JButton("Increment Month");
        // add endButton to button panel
        jpanel.add(incrementButton);


        // create exitButton
        JButton exitButton = new JButton("Exit Program");
        // add exit button to button panel
        jpanel.add(exitButton);



        // add action listeners to the buttons
        startButton.addActionListener(e1 -> this.startSimulation());
        endButton.addActionListener(e2 -> this.endSimulation());
        incrementButton.addActionListener(e3 -> this.incrementMonth());
        exitButton.addActionListener(e4 -> System.exit(0));



        // the buttons are put on the bottom center of the screen
        jframe.add(jpanel, BorderLayout.SOUTH);
        // now that they are on the bottom,
        // by saying (new FlowLayout(FlowLayout.RIGHT)), the buttons are also put on the right
        jpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));//FlowLayout.RIGHT));



        // "frame.setExtendedState(JFrame.MAXIMIZED_BOTH)" forces the width and height to be full screen
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true); // if wanted, could remove boarder

        // run the frame of existing simulations
        jframe.setVisible(true);
    }

    // the painComponenet(Graphics g) method in in the ImageIcon class. It simply displays the image
    @Override
    public void paintComponent(Graphics g) {
        // super.painComponent(g) is important because it ensures that all painting components that are not
        // expressly defined in the method can be handled by the parent class's paintComponent(Graphics g) method
        super.paintComponent(g);
        // draw the image mapImage at coordinates (0,0). This refers to the current instance of the panel
        g.drawImage(mapImage, 0, 0, this);


        // Define the position for the text boxes.
        int x = getWidth() - 330;
        int y = 20;

        // draw numbered text boxes in the top right corner.
        for (int i = 1; i <= 10; i++) {
            g.drawString(i + ". ...", x, y);
            // push the text box 20 pixels down
            y += 20;
        }

        g.drawString("Current Month: " + currentMonth, getWidth() - 450, 20);
    }


    private void startSimulation() {
        simulation = new Simulation(presetFilePath);
    }

    private void endSimulation() {
        //delete simulation class
    }

    private void incrementMonth() {
        currentMonth++;
        repaint();
    }

    public static void main(String[] args) {
        // instance of simulation class
        GUI gui = new GUI("./presetFiles/AmericaPreset.txt");
//        GUI gui = new GUI("./presetFiles/EuropePreset.txt");
    }
}
