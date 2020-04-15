package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Object {

	private Image[] image;
	private ImageView[] imageView;	
	private int X;
	private int Y;
	private int dx;
	private int dy;
	private boolean isMoving=false;
	
	public abstract void  loadImage();
	public abstract void  showImage();

	public ImageView getAnImageView(int i) {
		return imageView[i];
	}
	
	public void setAnImageView(int i , ImageView imageView) {
		this.imageView[i] = imageView;
	}
	
	public Image[] getImage() {
		return image;
	}

	public void setImage(Image[] image) {
		this.image = image;
	}

	public ImageView[] getImageView() {
		return imageView;
	}

	public void setImageView(ImageView[] imageView) {
		this.imageView = imageView;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void setAnImage(int i , Image image) {
		this.image[i]= image;
	}
	
	public Image getAnImage(int i) {
		return this.image[i];
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}	
}
	
	
	

