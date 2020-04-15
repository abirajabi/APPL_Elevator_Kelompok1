package objects;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class Human extends Object {
	private static final int frameLength = 6;
	private static final String path = "walkingin";
	private int startFloor;
	private int destFloor;
	private double weight;
	private boolean inFrontOfElevator = false;
	private boolean inElevator = false;

	public Human(double humanWeight) {
		this.weight = humanWeight;
		loadImage();
	}
	
	public void loadImage(){
		super.setImage(new Image[frameLength]); 
		super.setImageView(new ImageView[frameLength]);
		super.setDx(10);
		super.setDy(2);
		for(int i = 0; i < frameLength; i++) {
			try {
				super.setAnImage(i,new Image(path+(i+1)+".png"));	
				super.setAnImageView(i,new ImageView(super.getAnImage(i)));
				super.getAnImageView(i).setFitHeight(100);
				super.getAnImageView(i).setFitWidth(weight);
				super.getAnImageView(i).setVisible(false);
			} catch(Exception e) {
				System.out.println(path+(i+1)+".png" + " isnt found");
				System.exit(0);
			}		
		}
		super.getAnImageView(0).setVisible(true);
	}
	
	public void showImage() {
		for(int i = 0 ; i < 6 ; i++) {
			super.getAnImageView(i).setVisible(true);
		}
	}

	
	public int getStartFloor() {
		return startFloor;
	}

	public void setStartFloor(int startFloor) {
		this.startFloor = startFloor;
	}

	public int getDestFloor() {
		return destFloor;
	}

	public void setDestFloor(int destFloor) {
		this.destFloor = destFloor;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isInFrontOfElevator() {
		return inFrontOfElevator;
	}

	public void setInFrontOfElevator(boolean inFrontOfElevator) {
		this.inFrontOfElevator = inFrontOfElevator;
	}

	public boolean isInElevator() {
		return inElevator;
	}

	public void setInElevator(boolean inElevator) {
		this.inElevator = inElevator;
	}
	
	
	
}
