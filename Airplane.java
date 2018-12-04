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

    public void moveLeft(int speed) {
        x -= speed;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(airplane_img, x, y, null);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
