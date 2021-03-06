import javax.swing.*;
import java.awt.*;

class BackgroundImage extends JPanel {
    private final Image image;

    public BackgroundImage(Image image, int width, int height) {
        this.image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0,0, this);
    }
}
