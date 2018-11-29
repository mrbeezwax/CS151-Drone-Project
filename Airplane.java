import javax.swing.*;
import java.awt.*;

public class Airplane extends JComponent {
    private PlaneShape shape;
    private double x = -250;
    private double y = 0;

    public Airplane() {
        shape = new PlaneShape(x, y, 100);
    }

    public void moveLeft() {
        shape.right();
    }

    public void moveRight() {
        shape.left();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        shape.draw(g2);
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
