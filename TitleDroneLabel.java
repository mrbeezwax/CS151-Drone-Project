import javax.swing.*;

public class TitleDroneLabel extends JLabel {
    public TitleDroneLabel() {
        Icon icon = new ImageIcon(getClass().getResource("resources/images/resized_drone.png"));
        this.setIcon(icon);
    }
}
