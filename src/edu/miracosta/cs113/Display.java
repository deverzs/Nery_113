package edu.miracosta.cs113;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display {

    public static final int MAP_DISPLAY_WIDTH = 420;
    public static final int MAP_DISPLAY_HEIGHT = 420;
    public static final String MAP_DISPLAY_FILE = "displayMap1.jpg";

    public static final int DIRECTIONS_DISPLAY_WIDTH = 400;
    public static final int DIRECTIONS_DISPLAY_HEIGHT = 300;
    public static final int DIRECTIONS_NUM_COLUMNS = 30;
    public static final int DIRECTIONS_NUM_ROWS = 10;

    public static final int INPUT_FRAME_WIDTH = 400;
    public static final int INPUT_FRAME_DEPTH = 100;
    public static final int INPUT_TEXTFIELD_SIZE = 5;

    //inner class that creates the frame that the user will use to give their starting
    //and ending destination.
    public static class InputFrame extends JFrame {

        protected JTextField start;
        protected JTextField end;

        protected JButton find;

        protected JPanel inputPanel;

        protected JLabel startLabel;
        protected JLabel endLabel;

        public InputFrame() {
            super("Enter Input Here");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(INPUT_FRAME_WIDTH, INPUT_FRAME_DEPTH);
            setLayout(new FlowLayout());

            start = new JTextField(INPUT_TEXTFIELD_SIZE);
            end = new JTextField(INPUT_TEXTFIELD_SIZE);

            find = new JButton("FIND");

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

    //inner class that will contain our background/map image.
    public static class ImagePanel extends JComponent {
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
    public static class MapDisplayPanel extends JFrame {

        public MapDisplayPanel() {
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
        }
    }

    //inner class to display the shortest path directions to user
    public static class DisplayDirectionsPanel extends JFrame {

        JTextArea textArea;
        JPanel textPanel;

        public DisplayDirectionsPanel() {
            super("Shortest Path Directions");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(DIRECTIONS_DISPLAY_WIDTH, DIRECTIONS_DISPLAY_HEIGHT);

            textArea =new JTextArea(DIRECTIONS_NUM_ROWS, DIRECTIONS_NUM_COLUMNS);
            textPanel = new JPanel();
            textPanel.add(textArea);

            this.add(textPanel);
        }
    }
    /**
     * Constructor that will set visible the three display frames
     */
    public Display() {
        MapDisplayPanel map = new MapDisplayPanel();
        InputFrame inputFrame = new InputFrame();
        DisplayDirectionsPanel directionsPanel = new DisplayDirectionsPanel();
        map.setVisible(true);
        inputFrame.setVisible(true);
        directionsPanel.setVisible(true);
    }

    /**
     * Test to see what our display actually looks like
     */
    public static void main(String[] args) {
        Display display = new Display();
    }

}
