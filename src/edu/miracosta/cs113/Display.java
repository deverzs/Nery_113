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

    public static final int INPUT_FRAME_WIDTH = 400;
    public static final int INPUT_FRAME_DEPTH = 100;

    public static final int INPUT_TEXTFIELD_SIZE = 5;

    public static final String MAP_DISPLAY_FILE = "displayMap1.jpg";


    //Creates the frame that the user will use to give their starting
    //and ending destination.
    public static class InputFrame extends JFrame {

        protected JTextField start;
        protected JTextField end;

        protected JButton find;

        protected JPanel inputPanel;
        protected JPanel startPanel;
        protected JPanel endPanel;

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
    public static class MapeDisplayFrame extends JFrame {

        public MapeDisplayFrame() {
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

    public Display() {
        MapeDisplayFrame map = new MapeDisplayFrame();
        InputFrame inputFrame = new InputFrame();
        map.setVisible(true);
        inputFrame.setVisible(true);
    }
    public static void main(String[] args) {
        Display display = new Display();
    }

}
