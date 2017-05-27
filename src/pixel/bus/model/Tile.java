package pixel.bus.model;

import java.awt.*;

/**
 * Created by vanley on 21/05/2017.
 */
public abstract class Tile {

    private static final int TILE_SIZE = 32;

    public static int getTileSize() {
        return TILE_SIZE;
    }

    private int x;
    private int y;
    private Image image;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
