import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TitleBackgroundImage extends JPanel {
    private Image image;

    public TitleBackgroundImage(int width, int height) {
        try {
            image = ImageIO.read(getClass().getResource("/resources/images/titlebg.jpg"));
            this.image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        JPanel titlePanel = new JPanel();
        titlePanel.add(new TitleLabel());
        titlePanel.add(new TitleDroneLabel());
        titlePanel.setOpaque(false);
        add(titlePanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0,0, this);
    }
}
