import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Drone extends JLabel implements KeyListener{
    private int x = 0;
    private int y = 150;
    int dx = 0;
    int dy = 0;
    private BufferedImage drone_img;

    public Drone() {
        addKeyListener(this);
        try {
            drone_img = ImageIO.read(getClass().getResource("resources/images/resized_drone.png"));
        } catch (IOException e) {
            System.out.println("Error reading drone image");
        }
    }
    
    public void move(){
        x += dx;
        y += dy;
        //x += 1;
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
        System.out.println("...");
        
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
        System.out.println("...");
        
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }
    
    public void keyTyped(KeyEvent e) {
        
        int key = e.getKeyCode();
        System.out.println("...");
            
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
    
}