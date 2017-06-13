package pixel.bus.model;

import java.awt.*;

/**
 * Created by vanley on 12/06/2017.
 */
public class TileFieldTree extends Tile {
    private String imageLocation = "/res/img/field_tree.png";

    public TileFieldTree(int x, int y) {
        super(x, y);
        this.setImage(imageLocation);
    }

    @Override
    public void animate(Graphics g) {

    }
}
