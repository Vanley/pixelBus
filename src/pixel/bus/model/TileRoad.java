package pixel.bus.model;

/**
 * Created by vanley on 21/05/2017.
 */
public class TileRoad extends Tile {
    private String imageLocation = "/res/img/road1.png";

    public TileRoad(int x, int y) {
        super(x, y);
        this.setImage(imageLocation);
    }
}
