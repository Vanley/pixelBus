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
        buildMapTiles();
    }

    public int getCityX() {
        return this.cityX;
    }

    public int getCityY() {
        return this.cityY;
    }

    public final void buildMapTiles() {
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
        cityY += tileSize;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildCity(g);
        buildStatistic(g);
    }

    private void buildStatistic(Graphics g) {
            g.setColor(new Color(0, 0, 0));
            g.drawString("Label:", 25, 20);
    }

    private void buildCity(Graphics g) {
        g.setColor(new Color(149, 250, 80));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (Tile tile : tiles) {
            if (tile != null) {
                g.drawImage(tile.getImage(), tile.x(), tile.y(), this);
            }
        }
    }

}
