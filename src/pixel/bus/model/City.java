package pixel.bus.model;

import pixel.bus.model.map.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by vanley on 31/05/2017.
 */
public class City {
    private int cityX;
    private int cityY;
    private Dimension dimension;
    private ArrayList<Tile> tiles = new ArrayList<>();

    private CityLevel cityLevel;

    public City(CityLevel cityLevel) {
        this.cityLevel = cityLevel;
        buildMapTiles();
    }

    public Dimension getDimension() {
        return dimension;
    }

    public CityLevel getCityLevel() {
        return cityLevel;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    private void buildMapTiles() {
        String cityString = cityLevel.getMap();
        int tileSize = Tile.getTileSize();
        int x = tileSize;
        int y = tileSize;

        for (int i = 0; i < cityString.length(); i++) {

            char item = cityString.charAt(i);

            if (item == '\n') {
                y += tileSize;
                if (this.cityX < x) {
                    this.cityX = x;
                }
                x = tileSize;
            } else if (item == '-') {
                tiles.add(new TileRoad(x, y));
                x += tileSize;
            } else if (item == 's') {
                tiles.add(new Station(x, y));
                x += tileSize;
            } else if (item == 'f') {
                tiles.add(new TileField(x, y));
                x += tileSize;
            } else if (item == 'p') {
                tiles.add(new TileFieldPlain(x, y));
                x += tileSize;
            } else if (item == 't') {
                tiles.add(new TileFieldTree(x, y));
                x += tileSize;
            } else if (item == 'r') {
                tiles.add(new TileFieldTrees(x, y));
                x += tileSize;
            } else if (item == 'v') {
                tiles.add(new TileFieldVege(x, y));
                x += tileSize;
            } else if (item == ' ') {
                x += tileSize;
            }
            cityY = y;
        }

        cityX += tileSize;
        cityY += tileSize;

        dimension = new Dimension(cityX, cityY);
    }
}
