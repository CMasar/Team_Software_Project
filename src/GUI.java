import java.io.File;
import java.io.FileNotFoundException;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


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
    private String presetFilePath;

    private Simulation simulation;
    private HashMap<String,VisualObject> visuals;

    private JFrame mainFrame;
    private JPanel buttonPanel;
    private JPanel mapPanel;

    private int currentMonth = 0;


    public GUI(String presetFilePath) {
        this.presetFilePath = presetFilePath;
        mainFrame = new JFrame("Viral Simulation");
        buttonPanel = new JPanel();

        simulation = new Simulation(presetFilePath); // Creating instance of Simulation class.
        visuals = new HashMap<String,VisualObject>(); // Stores visual representations of sim data.

        loadFromFile();
        buildButtonPanel();
        buildFrame();

    }


    //
    // -- Build methods for main frame and panels.
    //

    private void buildFrame() {
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(128, 128, 1200, 500);
        
        mainFrame.add(mapPanel, BorderLayout.CENTER);
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void buildButtonPanel() {
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Create start button
        JButton startButton = new JButton("Start Simulation");
        buttonPanel.add(startButton);

        // Create end simulation button
        JButton endButton = new JButton("End Simulation");
        buttonPanel.add(endButton);


        // Create increment month button
        JButton incrementButton = new JButton("Increment Month");
        buttonPanel.add(incrementButton);


        // Create exitButton
        JButton exitButton = new JButton("Exit Program");
        buttonPanel.add(exitButton);



        // Add action listeners to the buttons
        startButton.addActionListener(e1 -> this.startSimulation());
        endButton.addActionListener(e2 -> this.endSimulation());
        incrementButton.addActionListener(e3 -> this.incrementMonth());
        exitButton.addActionListener(e4 -> System.exit(0));
    }


    //
    // -- Button methods.
    //

    private void startSimulation() {
        simulation = new Simulation(presetFilePath);
    }

    private void endSimulation() {
        // Delete simulation class
    }

    private void incrementMonth() {
        currentMonth++;
        repaint();
    }


    //
    // -- Methods for fetching data from file.
    //


    // Fetch sim data from preset file
    private void loadFromFile() {
        File presetFile = new File(this.presetFilePath);
        try{
            Scanner sc = new Scanner(presetFile);
            while (sc.hasNextLine()){
                String[] header = sc.nextLine().split(":");
                switch (header[0].trim().toLowerCase()){
                    case "gui" -> {
                        mapPanel = new ImagePanel(sc);
                    }
                    case "visual" -> {
                        // Construct a visual object and put it in the hashmap
                        String stateName = header[1].trim().toLowerCase();
                        visuals.put(stateName,new VisualObject(sc));
                    }
                }
            }
        } catch (FileNotFoundException e){
            // invalid file path causes program to exit
            System.out.println("Invalid File Path");
            System.exit(1);
        } catch (Exception e){
            // Invalid preset syntax causes program to exit
            System.out.println("Invalid Preset Syntax");
            System.exit(1);
        }
    }

    // Private class for the background map image.
    private class ImagePanel extends JPanel {
        private Image image;
        private int width;
        private int height;

        public ImagePanel(Scanner sc) {
            ImageIcon imageIcon = null;

            try{
                String[] line = sc.nextLine().split("=");
                String name = line[0].trim().toLowerCase();
                String mapPath = line[1].trim();

                // If name does not match.
                if (!name.equals("map path")) throw new Exception();

                // Creating map image.
                imageIcon = new ImageIcon(mapPath);

                width = imageIcon.getIconWidth();
                height = imageIcon.getIconHeight();
                image = imageIcon.getImage();

            } catch (FileNotFoundException e) {
                // Invalid Map path causes program to exit
                System.out.println("Invalid Map Path");
                System.exit(1);
            } catch (Exception e){
                // Invalid Map preset syntax causes program to exit
                System.out.println("Invalid Map Preset Syntax");
                e.printStackTrace();
                System.exit(1);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(mainFrame.getWidth(), mainFrame.getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getHeight()*((width+512)/height), this.getHeight(), this);
        }

    }

}