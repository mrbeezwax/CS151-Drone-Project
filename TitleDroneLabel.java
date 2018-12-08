import javax.swing.*;

class TitleDroneLabel extends JLabel {
    public TitleDroneLabel() {
        Icon icon = new ImageIcon(getClass().getResource("resources/images/resized_drone.png"));
        this.setIcon(icon);
    }
}
