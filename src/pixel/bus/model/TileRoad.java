package pixel.bus.model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by vanley on 21/05/2017.
 */
public class TileRoad extends Tile {
    private Image image;
    private String imageString = "/res/img/road1.png";

    public TileRoad(int x, int y) {
        super(x, y);
        URL loc = Tile.class.getResource(imageString);
        image = new ImageIcon(loc).getImage();
        this.setImage(image);
    }
}
