package pixel.bus.model;

import java.awt.*;

/**
 * Created by vanley on 12/06/2017.
 */
public class TileField extends Tile {
    private String imageLocation = "/res/img/field.png";

    public TileField(int x, int y) {
        super(x, y);
        this.setImage(imageLocation);
    }

    @Override
    public void animate(Graphics g) {

    }
}
