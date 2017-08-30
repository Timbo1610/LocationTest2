package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Slot extends Location {

    ArrayList<PickWall> pickWalls = new ArrayList<>();
    Color color;
    Rectangle rect;

    public Slot(Pane root, int x, int y,int z) {
        super(root,x, y, z);


        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setZoom(true);
                rect.setFill(Color.BLUE);
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setZoom(false);
                rect.setFill(color);
            }
        });



        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.SECONDARY)) {
                    pickWalls.add(new PickWall((int)(Math.random() * 100)+ " Pickwall",z));
                    System.out.println(pickWalls.get(pickWalls.size()-1).getName() + " added to " + x);
                }
            }
        });
    }

    @Override
    public Node createView() {
        color = Color.LIGHTGRAY;
        rect = new Rectangle();
        rect.setHeight(Settings.LOCATION_WIDTH);
        rect.setWidth(Settings.LOCATION_WIDTH);
        rect.setFill(color);
        return rect;
    }

    public ArrayList<PickWall> getPickWalls() {
        return pickWalls;
    }

    public void setColor(Color color) {
        this.color = color;
        rect.setFill(this.color);
    }


    public void setZoom(boolean zoom)
    {
        if(zoom)
        {

            view.setScaleX(Settings.GRID_ZOOM_EFFECT);
            view.setScaleY(Settings.GRID_ZOOM_EFFECT);
        }
        else
        {
            view.setScaleX(1);
            view.setScaleY(1);
        }
    }

}
