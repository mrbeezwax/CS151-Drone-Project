import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.KeyEvent;

public class Drone extends JLabel {
    private int x = 0;
    private int y = 150;
    private int dx = 0;
    private int dy = 0;
    private BufferedImage drone_img;

    public Drone() {
        try {
            drone_img = ImageIO.read(getClass().getResource("resources/images/resized_drone.png"));
            setIcon(new ImageIcon(drone_img));
        } catch (IOException e) {
            System.out.println("Error reading drone image");
        }
    }
    
    public void move() {
        x += dx;
        y += dy;
        if (x < -10) {
            x = -10;
        }
        if(x > 600) {
            x = 600;
        }
        if (y < -20) {
            y = -20;
        }
        if(y > 270) {
            y = 270;
        }
        repaint(); //330 and 650
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public void resetPosition() {
        x = 0;
        y = 150;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(drone_img, x, y, null);
    }
    
     public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}