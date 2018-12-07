package edu.miracosta.cs113;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display {
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

        // elsewhere
//        BufferedImage myImage = ImageIO.read(...);
//        JFrame myJFrame = new JFrame("Image pane");
//        myJFrame.setContentPane(new ImagePanel(myImage));

    public static class MapDisplay extends JFrame {
        public static final int WIDTH = 420;
        public static final int HEIGHT = 420;

        protected JButton find;
        protected JTextField start;
        protected JTextField end;

        public MapDisplay() {
            super("Final Group Project");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(WIDTH, HEIGHT);

            //set background to map image
            try {
                System.out.println("we are here");
                BufferedImage backgroundImage = ImageIO.read(new File("displayMap1.jpg"));
                this.setContentPane(new ImagePanel(backgroundImage));
            }
            catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }

    public Display() {
        MapDisplay map = new MapDisplay();
        map.setVisible(true);
    }
    public static void main(String[] args) {
        Display display = new Display();
    }

}
