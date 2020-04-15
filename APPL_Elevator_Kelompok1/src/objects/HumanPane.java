package objects;

import javafx.scene.layout.Pane;

public class HumanPane {
	private Human human;
	private Pane pane;

	public HumanPane(Human human , Pane pane) {
		this.human = human;
		this.pane = pane;
	}

	public Human getHuman() {
		return human;
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
	
}
