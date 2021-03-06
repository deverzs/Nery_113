package edu.miracosta.cs113;
/**
 *  Display.java :
 *  GUI class that will display the map, the input frame, the directions and any location information.
 *
 * @author @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 * @version 1.0
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {
    //Map Display Constants
    public static final int MAP_DISPLAY_WIDTH = 616;
    public static final int MAP_DISPLAY_HEIGHT = 576;
    public static final String MAP_DISPLAY_FILE = "displayMap4.jpg";
    //Location Display Constants
    public static final int LOCATION_DISPLAY_WIDTH = 400;
    public static final int LOCATION_DISPLAY_HEIGHT = 300;
    public static final int LOCATION_TEXTAREA_NUM_ROWS = 5;
    public static final int LOCATION_TEXTAREA_NUM_COLUMNS = 30;
    //Directions Display Constants
    public static final int DIRECTIONS_DISPLAY_WIDTH = 400;
    public static final int DIRECTIONS_DISPLAY_HEIGHT = 300;
    public static final int DIRECTIONS_NUM_COLUMNS = 30;
    public static final int DIRECTIONS_NUM_ROWS = 10;
    //Input Frame Constants
    public static final int INPUT_FRAME_WIDTH = 400;
    public static final int INPUT_FRAME_DEPTH = 100;
    public static final int INPUT_TEXTFIELD_SIZE = 5;
    //Location Info Array that will connect location with a description
    public static final String[] locationInfoArr = MiniMap.readLocationData("locationInformation.txt");

    //InputFrame's variable
    protected JTextField start;
    protected JTextField end;
    protected JButton find;
    protected JPanel inputPanel;
    protected JLabel startLabel;
    protected JLabel endLabel;

    //DisplayDescription's variables
    protected JTextArea directionsTextArea;
    protected JPanel textPanel;

    //The Map Location Buttons
    protected JButton locationButton0, locationButton1, locationButton2, locationButton3, locationButton4, locationButton5,
                        locationButton6, locationButton7, locationButton8, locationButton9, locationButton10, locationButton11,
                        locationButton12, locationButton13, locationButton14, locationButton15, locationButton16, locationButton17,
                        locationButton18, locationButton19;
    //This array will make adding the buttons to the map at a later time easier. Also uses less lines of code.
    protected JButton[] locationButtons = {locationButton0, locationButton1, locationButton2, locationButton3, locationButton4,
                                            locationButton5, locationButton6, locationButton7, locationButton8, locationButton9,
                                            locationButton10, locationButton11, locationButton12, locationButton13, locationButton14,
                                            locationButton15, locationButton16, locationButton17, locationButton18, locationButton19};
    //Location Button Constants
    public static final int LOCATION_BUTTON_WIDTH = 50;
    public static final int LOCATION_BUTTON_HEIGHT = 30;
    //The X and Y position of all the Location Buttons
    public static final int[] LOCATION_BUTTON_X_POS = {190, 250, 330, 405, 480, 240, 370, 480, 370, 480, 240, 300,
                                                        300, 405, 480, 370, 480, 370, 460, 530};
    public static final int[] LOCATION_BUTTON_Y_POS = {105, 105, 105, 130, 105, 207, 207, 207, 260, 260, 320, 320,
                                                        360, 335, 340, 405, 410, 470, 505, 475};
    //Location Frame's Components
    protected JTextArea locationTextArea;

    protected MiniMap miniMap = new MiniMap(0,18);

    //Input Frame:
    //Creates the frame that the user will use to give their starting
    //location and ending destination.
    private class InputFrame extends JFrame {



        public InputFrame() {
            super("Enter Input Here");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(INPUT_FRAME_WIDTH, INPUT_FRAME_DEPTH);
            setLayout(new FlowLayout());

            start = new JTextField(INPUT_TEXTFIELD_SIZE);
            end = new JTextField(INPUT_TEXTFIELD_SIZE);

            find = new JButton("FIND");
            find.addActionListener(new FindDirectionsListener());

            startLabel = new JLabel("start");
            endLabel = new JLabel("end");

            inputPanel = new JPanel(new FlowLayout());
            inputPanel.add(startLabel);
            inputPanel.add(start);
            inputPanel.add(endLabel);
            inputPanel.add(end);
            inputPanel.add(find);
            this.add(inputPanel);
        }
    }

    //ImagePanel:
    //Component that will contain our background/map image.
    private class ImagePanel extends JComponent {
        protected Image image;
        public ImagePanel(Image image) {
            this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
    //MapDisplayFrame:
    //Displays map to user. Uses the image and adds it a ImagePanel and
    //sets the background to that ImagePanel.
    private class MapDisplayFrame extends JFrame {

        public MapDisplayFrame() {
            super("Final Group Project");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(MAP_DISPLAY_WIDTH, MAP_DISPLAY_HEIGHT);

            //read image and set it as background
            try {
                BufferedImage backgroundImage = ImageIO.read(new File(MAP_DISPLAY_FILE));
                this.setContentPane(new ImagePanel(backgroundImage));
            }
            catch (IOException e) {
                System.out.println(e.toString());
            }
            //Create the location buttons and places them on the display map.
            for(int i = 0 ; i < locationButtons.length; i++) {
                locationButtons[i] = new JButton(Integer.toString(i));
                locationButtons[i].setBounds(LOCATION_BUTTON_X_POS[i], LOCATION_BUTTON_Y_POS[i],
                                                LOCATION_BUTTON_WIDTH, LOCATION_BUTTON_HEIGHT);
                locationButtons[i].addActionListener(new LocationDisplayListener(locationButtons[i].getText()));
                add(locationButtons[i]);
            }

        }
    }


    //LocationDisplayFrame
    //Displays location information. The information displayed is dependent
    //on the button that the user presses.
    private class LocationDisplayFrame extends JFrame {
        public LocationDisplayFrame() {
            super("LOCATION INFORMATION");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(LOCATION_DISPLAY_WIDTH, LOCATION_DISPLAY_HEIGHT);

            locationTextArea = new JTextArea(LOCATION_TEXTAREA_NUM_ROWS, LOCATION_TEXTAREA_NUM_COLUMNS);
            locationTextArea.setEditable(false);
            locationTextArea.setFont(new Font("Serif",Font.PLAIN,20));
            JScrollPane scrollPane = new JScrollPane(locationTextArea);

            add(scrollPane);

        }
    }
    //DirectionsDisplay:
    //Displays the shortest path directions to the user.
    private class DirectionsDisplayFrame extends JFrame {

        public DirectionsDisplayFrame() {
            super("Shortest Path Directions");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(DIRECTIONS_DISPLAY_WIDTH, DIRECTIONS_DISPLAY_HEIGHT);

            directionsTextArea =new JTextArea(DIRECTIONS_NUM_ROWS, DIRECTIONS_NUM_COLUMNS);
            directionsTextArea.setEditable(false);
            directionsTextArea.setFont(new Font("Serif",Font.PLAIN,20));
            JScrollPane scrollPane = new JScrollPane(directionsTextArea);

            add(scrollPane);
        }
    }
    //LocationDisplayListener:
    //ActionListener that will check which button was pressed, retrieve the location information for that button
    //and display tha information to the user in the LocationDisplayFrame
    private class LocationDisplayListener implements ActionListener {
        private String value;
        //Constructor to make getting a string variable of the Button's text easier (ex. "0" , "7", "18").
        //This variable will make getting the location information from the MiniMap class easier.
        public LocationDisplayListener(String buttonName) {
            this.value = buttonName;
            System.out.println(buttonName);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String temp = (String)miniMap.getLocationData(Integer.parseInt(value));
            locationTextArea.setText("");
            locationTextArea.append(temp);
        }
    }
    //FindDirectionsListener:
    //Action Listener that will retrieve the starting location and ending destination from the Input Frame.
    //It then will use the MiniMap class to determine the shortest path between the start and end.
    //Finally, it will display those results to the user using the DirectionsDisplayFrame.
    private class FindDirectionsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int startPosition = Integer.parseInt(start.getText());
            int endPosition = Integer.parseInt(end.getText());

            miniMap = new MiniMap(startPosition, endPosition);
            miniMap.setMapVertices(locationInfoArr);
            directionsTextArea.setText("");
            directionsTextArea.append(miniMap.getShortestPath());
        }

    }
    //Display Constructor:
    //First, miniMap links each location/button to a specific string element of locationInfoArr.
    //The elements are Strings describing that specific location in Oceanside.
    //Finally, it instantiates each of the inner class JFrames and sets them visible.
    public Display() {
        miniMap.setMapVertices(locationInfoArr);
        MapDisplayFrame map = new MapDisplayFrame();
        InputFrame inputFrame = new InputFrame();
        DirectionsDisplayFrame directionsPanel = new DirectionsDisplayFrame();
        LocationDisplayFrame locationDisplayFrame = new LocationDisplayFrame();
        map.setVisible(true);
        inputFrame.setVisible(true);
        directionsPanel.setVisible(true);
        locationDisplayFrame.setVisible(true);
    }
}
