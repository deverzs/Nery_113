package edu.miracosta.cs113;

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
    public static final int MAP_DISPLAY_HEIGHT = 564;
    public static final String MAP_DISPLAY_FILE = "displaymap2.jpg";
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
    //String Array that will connect location with a description
    //.... still needs work
    public static final String[] stringArr = { "one","two","three","4","5", "6", "7","8","9", "10", "11", "12", "13",
            "14", "15", "16", "17", "18", "19", "20"};

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
                        locationButton6, locationButton7, locationButton8, locationButton9, locationButton10, locationButton11;
    //This array will make adding the buttons to the map at a later time easier. Also uses less lines of code.
    protected JButton[] locationButtons = {locationButton0, locationButton1, locationButton2, locationButton3, locationButton4,
                                            locationButton5, locationButton6, locationButton7, locationButton8, locationButton9,
                                            locationButton10, locationButton11};
    //Location Button Constants
    public static final int LOCATION_BUTTON_WIDTH = 50;
    public static final int LOCATION_BUTTON_HEIGHT = 30;
    //The X and Y position of all the Location Buttons
    public static final int[] LOCATION_BUTTON_X_POS = {190, 250, 330, 405, 480, 240, 370, 480, 370, 480, 240, 300};
    public static final int[] LOCATION_BUTTON_Y_POS = {105, 105, 105, 130, 105, 207, 207, 207, 260, 260, 320, 320};
    //Location Frame's Components
    protected JTextArea locationTextArea;

    protected MiniMap miniMap = new MiniMap(0,18);

    //inner class that creates the frame that the user will use to give their starting
    //and ending destination.
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

    private class FindDirectionsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int startPosition = Integer.parseInt(start.getText());
            int endPosition = Integer.parseInt(end.getText());

            miniMap = new MiniMap(startPosition, endPosition);
            miniMap.setMapVertices(stringArr);
            directionsTextArea.setText("");
            directionsTextArea.append(miniMap.getShortestPath());
        }

    }


    //inner class that will contain our background/map image.
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

    //inner class to display the map image
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

//            locationButton0 = new JButton("0");
//            locationButton0.setBounds(190,105,50,30);
//            locationButton0.addActionListener(new LocationDisplayListener(locationButton0.getText()));
//
//            JButton locationButton9 = new JButton("9");
//            JButton locationButton10 = new JButton("10");
//            JButton locationButton11 = new JButton("11");
//
//            locationButton9.setBounds(480, 260, 50, 30);
//            locationButton10.setBounds(240, 320, 50, 30);
//            locationButton11.setBounds(300, 320, 50, 30);
//
//
//            add(locationButton9);
//            add(locationButton10);
//            add(locationButton11);

            for(int i = 0 ; i < locationButtons.length; i++) {
                locationButtons[i] = new JButton(Integer.toString(i));
                locationButtons[i].setBounds(LOCATION_BUTTON_X_POS[i], LOCATION_BUTTON_Y_POS[i],
                                                LOCATION_BUTTON_WIDTH, LOCATION_BUTTON_HEIGHT);
                locationButtons[i].addActionListener(new LocationDisplayListener(locationButtons[i].getText()));
                add(locationButtons[i]);
            }

        }
    }

    private class LocationDisplayListener implements ActionListener {

        private String value;

        public LocationDisplayListener(String buttonName) {
            this.value = buttonName;
            System.out.println(buttonName);
        }



        @Override
        public void actionPerformed(ActionEvent e) {
            String temp = (String)miniMap.getLocationData(Integer.parseInt(value));
            locationTextArea.append(temp);
        }
    }

    //inner class to display location information
    private class LocationDisplayFrame extends JFrame {
        public LocationDisplayFrame() {
            super("LOCATION INFORMATION");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(LOCATION_DISPLAY_WIDTH, LOCATION_DISPLAY_HEIGHT);

            locationTextArea = new JTextArea(LOCATION_TEXTAREA_NUM_ROWS, LOCATION_TEXTAREA_NUM_COLUMNS);
            locationTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(locationTextArea);

            add(scrollPane);

        }
    }

    //inner class to display the shortest path directions to user
    private class DirectionsDisplayFrame extends JFrame {

        public DirectionsDisplayFrame() {
            super("Shortest Path Directions");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(DIRECTIONS_DISPLAY_WIDTH, DIRECTIONS_DISPLAY_HEIGHT);

            directionsTextArea =new JTextArea(DIRECTIONS_NUM_ROWS, DIRECTIONS_NUM_COLUMNS);
            directionsTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(directionsTextArea);

            add(scrollPane);
        }
    }
    /**
     * Constructor that will set visible the three display frames
     */
    public Display() {
        miniMap.setMapVertices(stringArr);
        MapDisplayFrame map = new MapDisplayFrame();
        InputFrame inputFrame = new InputFrame();
        DirectionsDisplayFrame directionsPanel = new DirectionsDisplayFrame();
        LocationDisplayFrame locationDisplayFrame = new LocationDisplayFrame();
        map.setVisible(true);
        //inputFrame.setVisible(true);
        //directionsPanel.setVisible(true);
        //locationDisplayFrame.setVisible(true);
    }

    /**
     * Test to see what our display actually looks like
     */
    public static void main(String[] args) {
        Display display = new Display();
    }

}
