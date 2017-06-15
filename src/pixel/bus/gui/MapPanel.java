package pixel.bus.gui;

import pixel.bus.model.*;
import pixel.bus.model.map.Tile;
import pixel.bus.service.GameEngineService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;

/**
 * Created by vanley on 21/05/2017.
 */
public class MapPanel extends JPanel implements ActionListener {
    private static int DELAY = 1000/10;
    private static Timer timer;

    private City city;

    public MapPanel(City city) {
        this.city = city;
        setFocusable(true);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintCityBackground(g);
        paintRoads(g);
        paintCityForeGround(g);
        paintStatistic(g);
    }

    private void paintStatistic(Graphics g) {
            g.setColor(new Color(0, 0, 0));
            g.drawString("Tick: " + GameEngineService.tick, 25, 20);
    }

    private void paintCityForeGround(Graphics g) {
        for (Station station : Station.getStations()){
            station.animate(g);
            g.drawImage(station.getImage(), station.getX(), station.getY(), this);
        }
    }

    private void paintCityBackground(Graphics g) {
        g.setColor(new Color(149, 250, 80));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (Tile tile : city.getTiles()) {
            if (tile != null) {
                g.drawImage(tile.getImage(), tile.getX(), tile.getY(), this);
                tile.animate(g);
            }
        }
    }

    private void paintRoads(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        //Antialiasing ON
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(150,150,150));
        for (RoadConnection road : RoadConnection.getRoads()) {
            int startPointX = center(road.getSource().getX());
            int startPointY = center(road.getSource().getY()) + 12;
            int endPointX = center(road.getDestination().getX());
            int endPointY = center(road.getDestination().getY()) + 12;

            Path2D path = new Path2D.Double();
            path.moveTo(startPointX, startPointY);
            path.lineTo(endPointX, endPointY);

            g2d.setStroke(new BasicStroke(4));
            g2d.draw(path);
        }

        //Antialiasing OFF
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        g2d.dispose();
    }

    private int center (int point) {
        return point += Tile.getTileSize() / 2;
    }

    @Override
    public Dimension getPreferredSize() {
        return city.getDimension();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
}
