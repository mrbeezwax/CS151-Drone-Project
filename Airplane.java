import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Airplane extends JLabel {
    private int x = 700; // 700+ is edge of the frame and farther
    private int y; // Range = [-20 to 270]. Anything lower or higher is cropped out of screen
    private BufferedImage airplane_img;

    public Airplane() {
        try {
            airplane_img = ImageIO.read(getClass().getResource("resources/images/airplane.png"));
        } catch (IOException e) {
            System.out.println("Error reading drone image");
        }
        repaint();
        y = new Random().nextInt(270 + 1 + 20) - 20;  // y is randomly generated between -20 and 180
    }
    
//    public Airplane(int y) {
//    	this.y = y;
//    	try {
//			airplane_img = ImageIO.read(getClass().getResource("resources/images/airplane.png"));
//		} catch (IOException e) {
//			System.out.println("Error reading airplane image");
//		}
//    	repaint();
//
//    }

    public void moveLeft(int speed) {
        x -= speed;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(airplane_img, x, y, null);
        //g2.drawImage(airplane_img, x + 200, y + 10, null);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

//    public static void main(String[] args) {
//        JFrame testFrame = new JFrame();
//        Airplane airplane = new Airplane();
//        testFrame.setDefaultCloseOperation(testFrame.EXIT_ON_CLOSE);
//        testFrame.setSize(800, 800);
//        testFrame.setVisible(true);
//        JLabel label = new JLabel(airplane.getIcon());
//        testFrame.add(label);
//    }
}
