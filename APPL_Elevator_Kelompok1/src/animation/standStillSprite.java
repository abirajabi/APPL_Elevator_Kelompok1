package animation;
import objects.Door;
import objects.Object;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/*
 
  Class ini dibuat untuk door :)
 
 */
public class standStillSprite {
	private int index = 1;
	private Pane g;
	private Door object;
	
	public standStillSprite(Pane g ,Door object) {
		this.g = g;
		this.object = object;
		g.setTranslateX(object.getX());
		g.setTranslateY(object.getY());
	}
	
	public void onceAnimation(int Speed){
		object.setMoving(true);
		object.showImage();
		
		g.setTranslateX(object.getX());
		g.setTranslateY(object.getY());
		
		Timeline t = new Timeline();

		int loop = object.getImageView().length-1;
		for(; index < loop; index++) {
			t.getKeyFrames().add(new KeyFrame(
					Duration.millis(Speed*(index)),
					(ActionEvent event) -> {
						g.getChildren().setAll(object.getAnImageView(index));
						if(index == 3) {
							object.reverseImage();
							object.setMoving(false);
							t.stop();
						}
						index++;
					}
				)
			);
		}
		t.play();
	}
}
