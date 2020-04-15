package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Door extends Object{
	private static final int frameLength = 4;
	private static final String path = "door";	
	private static final int door_width = 100;
	private static final int door_height = 150;	
	private boolean openState = false;
	
	public Door() {
		loadImage();
	}
	
	@Override
	public void loadImage() {
		openState = false;
		super.setImage(new Image[frameLength]); 
		super.setImageView(new ImageView[frameLength]);
		super.setDx(0);
		super.setDy(0);
		for(int i = 0; i < frameLength; i++) {
			try {
				super.setAnImage(i,new Image(path+(i+1)+".png"));	
				super.setAnImageView(i,new ImageView(super.getAnImage(i)));
				super.getAnImageView(i).setFitHeight(door_height);
				super.getAnImageView(i).setFitWidth(door_width);
				super.getAnImageView(i).setVisible(false);
				setX(400);
				setY(0);
			} catch (Exception e) {
				System.out.println(path+(i+1)+".png" + " isnt found");
				System.exit(0);
			}
		}
		super.getAnImageView(0).setVisible(true);		
	}

	@Override
	public void showImage() {
		for(int i = 0; i < frameLength; i++) {
			super.getAnImageView(i).setVisible(true);		
		}
	}
	
	public void reverseImage() {
		//Method ini digunakan ketika pintu selesai dibuka atau selesai ditutup
		ImageView[] tempImgView = new ImageView[frameLength];
		int j = frameLength - 1;
		for(int i = 0; i < frameLength; i++) {
			tempImgView[j] = super.getAnImageView(i);
			j--;
		}	
		super.setImageView(tempImgView);
	}
	
	public void changeOpenState() {
		this.openState = !this.getOpenState();
	}
	
	public boolean getOpenState() {
		return this.openState;
	}
	
}
