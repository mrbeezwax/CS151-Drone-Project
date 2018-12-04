import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Airplane extends JLabel {
    private int x = 650; // 650 is edge of the frame
    private int y = 150; // 330 is max. Anything higher is cropped out of screen
    private Timer timer;
    private boolean isDestroyed;
    private BufferedImage airplane_img;

    public Airplane() {
        try {
            airplane_img = ImageIO.read(getClass().getResource("resources/images/airplane.png"));
        } catch (IOException e) {
            System.out.println("Error reading drone image");
        }
        isDestroyed = false;
        timer = new Timer();
        x += new Random().nextInt(300);  // X is randomly generated so each Airplane arrives at different intervals
    }

    public void moveLeft(int scalar) {
        x -= 1 + scalar;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(airplane_img, x, y, null);
    }

    public void setDestroyed() {
        isDestroyed = true;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
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
