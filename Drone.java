
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Drone extends JFrame implements KeyListener{

	private MoveableShape shape;
	private ShapeIcon icon;
	private double x = 0;
	private double y = 0;
	private String picture = "C:\\Users\\Thomas Wang\\Desktop\\DroneProjectWorkspace\\DroneProject\\Drone Picture.png";
	
	public Drone() {
		addKeyListener(this);
		ImageIcon icon = new ImageIcon(picture);
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);
		JLabel label = new JLabel(newIcon);
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
		Drone frame = new Drone();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
}