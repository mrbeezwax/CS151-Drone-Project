import javax.swing.*;
import java.awt.*;

public class BackgroundImage extends JPanel {
    private Image image;

    public BackgroundImage(Image image) {
        this.image = image;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0,0, this);
    }
}
