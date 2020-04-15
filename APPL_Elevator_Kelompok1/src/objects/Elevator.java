package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Elevator extends Object {
	private static final String path = "elevator.png";
	private static final int elevator_width = 100;
	private static final int elevator_height = 150;
	private int dest_floor;
	private int curr_floor;
	
	public Elevator(){
		loadImage();
		this.curr_floor = 1;
		this.dest_floor = -1;
	}
	
	@Override
	public void loadImage() {
		try {
			super.setImage(new Image[1]); 
			super.setImageView(new ImageView[1]);
			super.setAnImage(0, new Image(path));
			super.setAnImageView(0,new ImageView(getAnImage(0)));
			getAnImageView(0).setFitHeight(elevator_height);
			getAnImageView(0).setFitWidth(elevator_width);	
			setX(400);
			setY(0);
			setMoving(false);
			
		} catch (Exception e) {
			System.out.println("Image not Found");
		}
	}
	
	void setCoord() {
		
	}
	
	@Override
	public void showImage() {
		// TODO Auto-generated method stub
		
	}
}
