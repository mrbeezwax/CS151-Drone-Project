
public class DroneShape {
	
	private String drone = "C:\\Users\\Thomas Wang\\Desktop\\DroneProjectWorkspace\\DroneProject\\Drone Picture.png";
	private int x;
	private int y;
	
	public DroneShape() {
		
	}
	
	public void up() {
		y = y + -15;
	}
	
	public void down() {
		y = y + 15;
	}
}
