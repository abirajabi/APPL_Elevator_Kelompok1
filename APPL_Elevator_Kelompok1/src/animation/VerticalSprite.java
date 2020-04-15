package animation;
import objects.Object;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class VerticalSprite {
	
	private Object object;
	private Pane g;
	private int index = 0;
	
	public VerticalSprite(Pane g, Object object) {	
		this.object = object;
		this.g = g;
		g.setTranslateX(object.getX());
		g.setTranslateY(object.getY());
	}
	
	public void moveAFrame(int yDestination , int Speed) {
		TranslateTransition translate = new TranslateTransition();	
		translate.setFromY(object.getY());
		translate.setToY(yDestination);
		translate.setDuration(Duration.seconds(Speed));
		translate.setNode(g);
		translate.play();
		object.setY(yDestination);
	}
}
