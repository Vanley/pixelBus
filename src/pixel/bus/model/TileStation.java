package pixel.bus.model;

import java.awt.*;

/**
 * Created by vanley on 01/06/2017.
 */
public class TileStation extends Tile{
    private String imageLocation = "/res/img/city1.png";
    private Station station;

    public TileStation(int x, int y) {
        super(x, y);
        this.setImage(imageLocation);

        this.station = new Station();
    }

    @Override
    public void animate(Graphics g) {
        g.setColor(new Color(200, 0, 0));
        g.drawString(" "+ station.getPassengerQueue().size(), x(), y()+getTileSize() * 4/3);
    }
}