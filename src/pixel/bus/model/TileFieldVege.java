package pixel.bus.model;

import java.awt.*;

/**
 * Created by vanley on 12/06/2017.
 */
public class TileFieldVege extends Tile {
    private String imageLocation = "/res/img/field_vege.png";

    public TileFieldVege(int x, int y) {
        super(x, y);
        this.setImage(imageLocation);
    }

    @Override
    public void animate(Graphics g) {

    }
}
