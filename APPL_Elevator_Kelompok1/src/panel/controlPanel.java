package panel;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import objects.Door;
import objects.Object;
import objects.Human;
import objects.HumanPane;
import objects.Elevator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import animation.*;
import consts.Consts;

public class controlPanel {
	private static final Door door = new Door();
	private static final Elevator elevator = new Elevator();
	private static final Human human = new Human(2D);
	private static final ArrayList<HumanPane> listHuman = new ArrayList<HumanPane>();
	
	private static final Boolean anyRequest[] = {false,false,false};
	private static Integer[] people_startFloor = new Integer[3];  /*sum of people in current floor */
	private static Integer[] people_destFloor = new Integer[3];
	private static int timeCounter=0;
	private static final int elevatorTime = 3300;
	
    static String floor_list[] = 
        { "1", "2", "3"};
	
	private Pane root;
	private Pane elevatorPane = new Pane(elevator.getAnImageView(0));
	private Pane doorPane = new Pane(door.getAnImageView(0));
	private Pane hp = new Pane(human.getAnImageView(0));	
	
	
	private boolean depanElevator = false;
	private boolean dalemElevator = false;
	
	private int human_currentFloor = 1;
	private int elevator_currentFloor =1;
	
	// human control
	private static final ComboBox floorstart_comboBox = new ComboBox(FXCollections.observableArrayList(floor_list));
	private static final ComboBox<String> floordest_comboBox  = new ComboBox(FXCollections.observableArrayList(floor_list));
	private static final TextField weight_textField = new TextField();
	private static final Button button_addHuman = new Button("Add Human");
	
	public controlPanel() {
		root = new Pane();
	}	
	
	public Pane start() {
		// Draw the elevator
		root.getChildren().add(elevatorPane);
		elevatorPane.setTranslateX(elevator.getX());
		elevatorPane.setTranslateY(elevator.getY());

		// Draw the door
		root.getChildren().add(hp);
		hp.setTranslateX(-10);
		hp.setTranslateY(55);
		human.setY(55);
		human.setX(100);
		
		root.getChildren().add(doorPane);
		doorPane.setTranslateX(door.getX());
		doorPane.setTranslateY(door.getY());
		
		
		for(int i=0;i<3;i++) {
			people_destFloor[i]=0;
			people_startFloor[i]=0;
		}
		
		
		summonButton();
		Timer t = new Timer();
		return root;
	}
	
	
	private void moveHorizontal(Object object , Pane p , int xDestination , int Speed) {
		horizontalSprite move = new horizontalSprite(p, object);
		move.moveFrames(xDestination, Speed);
	}
	
	private void moveVertical(Object object , Pane p , int yDestination , int Speed) {
		VerticalSprite move =new VerticalSprite(p,object);
		move.moveAFrame(yDestination, Speed);
	}
	
	public void doorAnimation(Door door , Pane p , int Speed) {
		door.changeOpenState();
		standStillSprite move = new standStillSprite(p,door);
		move.onceAnimation(Speed);
	}
	
	public int get_floor_coord(int floor) {
		switch(floor) {
		case 1 : floor = Consts.floor1_y ; break;
		case 2 : floor = Consts.floor2_y ; break;
		case 3 : floor = Consts.floor3_y ; break;
	   }
		return floor;
	}

	

	private void summonButton() {
		//put button	
			
		root.getChildren().add(floorstart_comboBox);
		floorstart_comboBox.setTranslateX(525);
		floorstart_comboBox.setTranslateY(30);
		floorstart_comboBox.setPrefSize(50, 20);
		
		
		root.getChildren().add(floordest_comboBox);
		floordest_comboBox.setTranslateX(600);
		floordest_comboBox.setTranslateY(30);
		floordest_comboBox.setPrefSize(50, 20);
		
		root.getChildren().add(weight_textField);
		weight_textField.setTranslateX(675);
		weight_textField.setTranslateY(30);
		weight_textField.setPrefSize(45, 20);
		
		root.getChildren().add(button_addHuman);
		button_addHuman.setTranslateX(574);
		button_addHuman.setTranslateY(75);
		button_addHuman.setPrefSize(101, 19);
		button_addHuman.addEventHandler(MouseEvent.MOUSE_CLICKED, new addHuman());
	}
	
	@SuppressWarnings("rawtypes")
	private class moveElevator implements EventHandler {
		private int go;
		
		public moveElevator(int go) {
			this.go = go;
		}
		
		@Override
		public void handle(Event arg0) {
			TimerTask task1 = new TimerTask()
			{
		        public void run()
		        {
		        	if(dalemElevator) {
		        		moveVertical(human, hp, get_floor_coord(go)+55, 4);
		        		human_currentFloor = go;
		        	}
		        	moveVertical(elevator,elevatorPane, get_floor_coord(go), 4);
		        	moveVertical(door,doorPane, get_floor_coord(go), 4);
		        	elevator_currentFloor = go;
		        }
			};
			
			Timer t = new Timer();
			
			if(door.getOpenState() == true) {
		       	doorAnimation(door,doorPane,500);
				t.schedule(task1,1000);
			} else {
				t.schedule(task1, 0);
			}

			TimerTask task2 = new TimerTask()
			{
			        public void run()
			        {
			        	doorAnimation(door,doorPane,500);
			        }
			};
			Timer t1 = new Timer();
			t1.schedule(task2,6000);
		}
	}
	
	private class moveHuman implements EventHandler{
		@Override
		public void handle(Event arg0) {	
			moveHorizontal(human, hp, 350, 100);
			depanElevator = true;
		}
	}
	
	
	private class addHuman implements EventHandler {
		private Alert alert;
		public addHuman() {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
		}
		@Override
		public void handle(Event arg0) {
			boolean isStartValued = floorstart_comboBox.getValue() != null;
			boolean isDestValued = floordest_comboBox.getValue()!=null;
			boolean isWeightValued = weight_textField.getText()!= null;
			boolean isSame = floorstart_comboBox.getValue()==floordest_comboBox.getValue();
			if(isStartValued &&  isDestValued && !isSame && isWeightValued  ) {
				try {
					Double humanWeight = Double.parseDouble(weight_textField.getText());
					int startFloor = Integer.parseInt((String) floorstart_comboBox.getValue());
					int destFloor = Integer.parseInt((String) floordest_comboBox.getValue());
					
					newHuman(startFloor,destFloor,humanWeight);
					
					int lastIndex = listHuman.size()-1;
					moveHorizontal(listHuman.get(lastIndex).getHuman(),listHuman.get(lastIndex).getPane(), 350, 75);
					
					TimerTask waitHuman = new TimerTask()
					{
					        public void run()
					        {	
								listHuman.get(lastIndex).getHuman().setInFrontOfElevator(true);
								queueForElevator(listHuman.get(lastIndex).getHuman() , listHuman.get(lastIndex).getPane(),
										listHuman.get(lastIndex).getHuman().getStartFloor());
								queueForElevator(listHuman.get(lastIndex).getHuman() , listHuman.get(lastIndex).getPane(),
										listHuman.get(lastIndex).getHuman().getDestFloor());
								people_startFloor[listHuman.get(lastIndex).getHuman().getStartFloor()-1]++;
								people_destFloor[listHuman.get(lastIndex).getHuman().getDestFloor()-1]++;
					        }

					};
					Timer waitHumanTimer = new Timer();
					waitHumanTimer.schedule(waitHuman,2500);	
					
					//Request Floor

					
				}catch(Exception e) {
					alert.setHeaderText("Weight has to be NUMBER");
					alert.showAndWait();
				}
			}else {
				alert.setHeaderText("Erro not find its expectation");
				alert.setContentText("1.All field cant be null \n2.Start floor cant be same as Dest Floor");
				alert.showAndWait();
			}
		}
	}
	
	private void moveElevator(Human human , Pane hp , int go) {
		if(go==elevator_currentFloor)
		{
			if(door.getOpenState() == false) {
		       	doorAnimation(door,doorPane,500);
			}
			
	       	for(int i =0 ; i < listHuman.size() ; i++) {
        		if(listHuman.get(i).getHuman().getStartFloor() == go && listHuman.get(i).getHuman().isInFrontOfElevator() ) {
	        		moveHorizontal(listHuman.get(i).getHuman(),listHuman.get(i).getPane(), 400 ,200);
	        		listHuman.get(i).getHuman().setInElevator(true);
	        		listHuman.get(i).getHuman().setInFrontOfElevator(false);
	        		people_startFloor[go-1]--;
        		}
        	}
		} else {
			int closeDoorTime = 0;
        	elevator.setMoving(true);
			TimerTask task1 = new TimerTask()
			{
		        public void run()
		        {
		        	anyRequest[go-1] = false;
		        	for(int i =0 ; i < listHuman.size() ; i++) {
		        		if(listHuman.get(i).getHuman().isInElevator()) {
						      moveVertical(listHuman.get(i).getHuman(),listHuman.get(i).getPane(), get_floor_coord(go)+55, 3);
		        		}
		        	}
					  moveVertical(elevator,elevatorPane, get_floor_coord(go), 3);
				      moveVertical(door,doorPane, get_floor_coord(go), 3);		     
		        }
			};
			
			Timer t = new Timer();
			
			if(door.getOpenState() == true) {
		       	doorAnimation(door,doorPane,500);
				t.schedule(task1,1000);
				closeDoorTime=1000;
				
			} else {
				t.schedule(task1, 0);
			}
			
			TimerTask task2 = new TimerTask()
			{
			        public void run()
			        {			        	
			        	doorAnimation(door,doorPane,500);
			        	elevator_currentFloor = go;
	
			        	for(int i =0 ; i < listHuman.size() ; i++) {
			        		if(listHuman.get(i).getHuman().getStartFloor() == go && listHuman.get(i).getHuman().isInFrontOfElevator() ) {
				        		moveHorizontal(listHuman.get(i).getHuman(),listHuman.get(i).getPane(), 400 ,200);
				        		listHuman.get(i).getHuman().setInElevator(true);
				        		listHuman.get(i).getHuman().setInFrontOfElevator(false);
				        		people_startFloor[go-1]--;
			        		}
			        	}
			        	
			        	for(int i =0 ; i < listHuman.size() ; i++) {
			        		if(listHuman.get(i).getHuman().getDestFloor() == go  && listHuman.get(i).getHuman().isInElevator()) {
			        			moveHorizontal(listHuman.get(i).getHuman(),listHuman.get(i).getPane(), 900 ,100);
				        		listHuman.get(i).getHuman().setInElevator(false);
				        		listHuman.remove(i);
				        		people_destFloor[go-1]--;
			        		}
			        	}

			        }
			};
			Timer t1 = new Timer();
			t1.schedule(task2,3000+closeDoorTime);
			
			if(people_destFloor[go-1] > people_startFloor[go-1]) {
				timeCounter = -200*people_destFloor[go-1];
			}else {
				timeCounter = -200*people_startFloor[go-1];
			}
			
	
			Timer t3 = new Timer();
			TimerTask elevatorTimer = new TimerTask()
			{
			        public void run()
			        {
			        	if(timeCounter == elevatorTime) {
			        		timeCounter=0;
				        	elevator.setMoving(false);
			        		t3.cancel();
			        	}
			        	timeCounter = timeCounter + 100;
			        			
			        }
			};
			t3.schedule(elevatorTimer,0,100);
		}	
	}
	
	private void newHuman(int startFloor , int destFloor , double weight) {
		Human human = new Human(weight);
		human.setStartFloor(startFloor);
		human.setDestFloor(destFloor);
		
		Pane pane = new Pane(human.getAnImageView(0));
		pane.setTranslateX(0);
		pane.setTranslateY(get_floor_coord(startFloor)+55);
		human.setY((int)pane.getTranslateY());
		human.setX((int)pane.getTranslateX());
		
		HumanPane humanPane = new HumanPane(human,pane);
		
		listHuman.add(humanPane);
		root.getChildren().add(listHuman.get(listHuman.size()-1).getPane());

	}
	
	private void queueForElevator(Human human , Pane hp , int go) {
		
		if(!anyRequest[go-1].booleanValue()) {
			if(elevator.isMoving()) {
				anyRequest[go-1] = true;
				int counter=0;
				for(int i=0 ; i<anyRequest.length ; i++ ) {
					if(anyRequest[i].booleanValue()) {
						counter++;
					}
				}
				Timer waitingTimer =new Timer();
				TimerTask waitingTask = new TimerTask()
				{
				        public void run()
				        {
				        	anyRequest[go-1] = false;
							moveElevator(human ,hp,go);			
				        }
				};
				int waitingTime =elevatorTime - timeCounter+(counter-1)* elevatorTime + 1000; 
				waitingTimer.schedule(waitingTask,waitingTime);
			}else {
				moveElevator(human ,hp,go);
			}

		}
	}		
}
	
