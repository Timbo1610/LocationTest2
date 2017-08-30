package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public abstract class Location extends Region {

    int locationX;
    int locationY;
    int locationZ;

    Node view;

    public Location(Pane root, int x, int y, int z)
    {
        this.view = createView();
        this.locationX = x;
        this.locationY = y;
        this.locationZ = z;
        display();
        getChildren().add( view);
        root.getChildren().add(this);



    }

    /**
     * Update node position
     */
    public void display() {

       relocate(locationX*Settings.GRID_WIDTH,
                locationY*Settings.GRID_WIDTH);


    }


    public abstract Node createView();

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;

    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;

    }

    public int getLocationZ() {
        return locationZ;
    }

    public void setLocationZ(int locationZ) {
        this.locationZ = locationZ;
    }
}
