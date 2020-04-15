package animation;
import objects.Object;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class horizontalSprite {
	private int index = 1;
	private Pane g;
	private Object object;
	
	public horizontalSprite(Pane g ,Object object) {
		this.g = g;
		this.object = object;
	}
	
	public void moveFrames(int xDestination, int Speed){
		object.setMoving(true);
		object.showImage();

		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		
		int xMove = getDirection(xDestination)*object.getDx();
		int loop = object.getImageView().length-1;

		for (; index < loop; index++) {
			t.getKeyFrames().add(new KeyFrame(
					Duration.millis(Speed*(index)),
						(ActionEvent event) -> {
							g.getChildren().setAll(object.getAnImageView(index));
							if (g.getTranslateX()!=xDestination) {
								g.setTranslateX(g.getTranslateX()+xMove);
								object.setX((int)g.getTranslateX());
							} else {
								object.setMoving(false);
								t.stop();
							}
							if(index == loop) {
								index = 1;
							}
							index++;
						}
					)
			);
		}
		t.play();
	}
	
	private boolean isGoRight(int destination) {
		return(object.getX() < destination); 
	}
	
	private int getDirection(int destination) {
		//return 1 if go right and return -1 if go left
		if(isGoRight(destination)) {
			return 1;
		}
		return -1;
	}
}
