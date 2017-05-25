package pixel.bus.gui;

import pixel.bus.model.CityLevels;
import pixel.bus.model.Tile;
import pixel.bus.model.TileRoad;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by vanley on 21/05/2017.
 */
public class CityMap extends JPanel {


    private int cityX;
    private int cityY;
    private ArrayList<Tile> tiles = new ArrayList<>();


    private String cityLevel = CityLevels.cityLevel1;

    public CityMap() {

        setFocusable(true);
        initCity();
    }

    public int getCityX() {
        return this.cityX;
    }

    public int getCityY() {
        return this.cityY;
    }

    public final void initCity() {
        int tileSize = Tile.getTileSize();
        int x = tileSize;
        int y = tileSize;

        TileRoad tileRoad;


        for (int i = 0; i < cityLevel.length(); i++) {

            char item = cityLevel.charAt(i);

            if (item == '\n') {
                y += tileSize;
                if (this.cityX < x) {
                    this.cityX = x;
                }

                x = tileSize;
            } else if (item == '-') {
                tileRoad = new TileRoad(x, y);
                tiles.add(tileRoad);
                x += tileSize;
            } else if (item == ' ') {
                x += tileSize;
            }

            cityY = y;
        }

        cityX += tileSize;
        cityY += tileSize * 2;
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildCity(g);
    }

    public void buildCity(Graphics g) {

        g.setColor(new Color(149, 250, 80));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int i = 0; i < tiles.size(); i++) {

            Tile item = (Tile) tiles.get(i);

            if (item instanceof Tile) {
                  g.drawImage(item.getImage(), item.x(), item.y(), this);
            }


            g.setColor(new Color(0, 0, 0));
            g.drawString("Label:", 25, 20);


        }
    }

}
