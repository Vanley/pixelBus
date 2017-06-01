package pixel.bus.model;

import java.util.ArrayList;

/**
 * Created by vanley on 31/05/2017.
 */
public class City {
    private int cityX;
    private int cityY;
    private ArrayList<Tile> tiles = new ArrayList<>();

    private String cityLevel;

    public City(String cityLevel) {
        this.cityLevel = cityLevel;
        buildMapTiles();
    }

    public int getCityX() {
        return this.cityX;
    }

    public int getCityY() {
        return this.cityY;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
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
}
