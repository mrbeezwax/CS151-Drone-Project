import javax.swing.*;

public class TitleLabel extends JLabel {
    public TitleLabel() {
        Icon icon = new ImageIcon(getClass().getResource("/resources/images/title.png"));
        this.setIcon(icon);
    }
}
