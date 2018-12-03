import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Drone extends JLabel {
    private int x = 0;
    private int y = 0;

    public Drone() {
        try {
            BufferedImage drone_img = ImageIO.read(getClass().getResource("/resources/images/dronepic.png"));
            ImageIcon icon = new ImageIcon(drone_img);
            this.setIcon(icon);
        } catch (IOException e) {
            System.out.println("Error reading drone image");
        }
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.drawImage(drone_img, x, y, null);
//        super.paintComponent(g);
//    }
}