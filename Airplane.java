import javax.swing.*;
import java.awt.event.ActionEvent;

public class Airplane extends JFrame {
    private MoveableShape shape;
    private ShapeIcon icon;
    private double x = -250;
    private double y = 0;

    public Airplane() {
        shape = new PlaneShape(x, y, 100);
        icon = new ShapeIcon(shape, 775, 100);
        JLabel label = new JLabel(icon);

        add(label);

    }

    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        Airplane frame = new Airplane();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
