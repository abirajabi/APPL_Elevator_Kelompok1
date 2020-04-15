package main;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import objects.*;
import panel.controlPanel;
import animation.*;
import consts.Consts;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		Pane pane = new Pane();
		ImageView background = new ImageView(new Image("background.png"));
		pane.getChildren().add(background);
		
		Pane gg = new Pane();
		controlPanel CP = new controlPanel();
		gg = CP.start();
		pane.getChildren().add(gg);

		Scene scene = new Scene(pane,800,650);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}