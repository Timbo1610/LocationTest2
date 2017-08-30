package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<Slot> createLocations(Pane root,int width, int depth){

        ArrayList<Slot> locations = new ArrayList<>();


        for(int x = 0; x< width;x++)
        {
            for(int y = 0; y< depth;y++) {
                locations.add(new Slot(root, x, y, 1));
                //locations.add(new Slot(root, x, y, 2));
            }

        }

        return locations;

    }
}
