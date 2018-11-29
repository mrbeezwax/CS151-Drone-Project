import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Plane extends JFrame implements KeyListener {
    private MoveableShape shape;
    private ShapeIcon icon;
    private double x = 0;
    private double y = 0;

    public Plane() {
        addKeyListener(this);
        shape = new PlaneShape(x, y, 100);
        icon = new ShapeIcon(shape, 775, 100);
        JLabel label = new JLabel(icon);

        add(label);

    }

    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) {
            shape.up();
        }
        if(code == KeyEvent.VK_DOWN) {
            shape.down();

        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public static void main(String[] args) {
        Plane frame = new Plane();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
