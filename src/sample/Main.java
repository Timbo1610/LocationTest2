package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Utils;

import java.util.ArrayList;


public class Main extends Application {

    ArrayList<Slot> slots = new ArrayList<>();
    AnimationTimer gameLoop;
    Pane root = new Pane();
    Pane subpane= new Pane();
    Button bt;
    ListView<String> pickWallListView;
    DragContext dragContext = new DragContext();
    int height = 1;
    Slot currentSlot = null;
    Double zoom = 1D;

    @Override
    public void start(Stage primaryStage) throws Exception{

        root.setPrefSize(Settings.SCENE_WIDTH,Settings.SCENE_HEIGHT);

        primaryStage.setTitle("Pick Module Test");
        primaryStage.setScene(new Scene(root, Settings.SCENE_WIDTH,Settings.SCENE_HEIGHT));
        primaryStage.show();

        bt = new Button("Run Query");

        pickWallListView = new ListView<>();

        root.getChildren().add(subpane);
        subpane.setLayoutX(250);
        subpane.setLayoutY(10);

        root.getChildren().add(bt);
        root.getChildren().add(pickWallListView);
        pickWallListView.setLayoutY(20);

        slots.addAll(Utils.createLocations(subpane,50,50));
        addListeners();
        startVisualization();

    }



    public static void main(String[] args) {
        launch(args);
    }

    private void addListeners() {
       subpane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                for(Slot slot:slots)
                {
                    if(event.getTarget().equals(slot.view))
                    {
                        pickWallListView.getItems().clear();
                        for(PickWall pickwall: slot.getPickWalls())
                        {
                            if(pickwall.height == height)
                            {
                                pickWallListView.getItems().add(pickwall.getName());
                                slot.setColor(Color.RED);
                                System.out.println(pickwall.getName());

                                if(currentSlot != null)
                                    currentSlot.setZoom(false);

                                slot.setZoom(true);
                                currentSlot = slot;
                            }

                        }

                        System.out.println(slot.getLocationX() + " detected");
                    }
                }
                System.out.println(event.getTarget().toString());

            }
        });

       subpane.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               dragContext.x = event.getX();
               dragContext.y = event.getY();
               System.out.println("x:" +  dragContext.x + " y:" + dragContext.y);
           }
       });
       subpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {

               double dx = event.getX() - dragContext.x;
               double dy = event.getY() - dragContext.y;



                       subpane.relocate(subpane.getLayoutX() + dx ,
                                subpane.getLayoutY() + dy);
               System.out.println("x:" + dx + " y:" +dy);
           }
       });
       subpane.setOnScroll(new EventHandler<ScrollEvent>() {
           @Override
           public void handle(ScrollEvent event) {
               zoom = zoom + event.getDeltaY()/100;
               subpane.setScaleX(zoom);
               subpane.setScaleY(zoom);
           }
       });





        bt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("perform query...");

            }
        });
    }
    private void startVisualization() {

        // start game
        gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                slots.forEach(Location::display);


            }
        };

        gameLoop.start();

    }


    class DragContext {

        double x=0;
        double y=0;

    }
}
