package pixel.bus.gui.component;

import pixel.bus.gui.GameFrame;
import pixel.bus.model.*;
import pixel.bus.model.map.Tile;
import pixel.bus.service.GameEngineService;
import pixel.bus.service.GameLoaderFactory;
import pixel.bus.service.StationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

/**
 * Created by vanley on 21/05/2017.
 */
public class MapPanel extends JPanel implements ActionListener {
    private static int DELAY = 1000/10;
    private static Timer timer;

    private boolean isMouseHere = false;
    private Point mousePoint;
    private String mouseOverEntity;

    private City city;

    public MapPanel(City city) {
        this.city = city;
        setFocusable(true);

        timer = new Timer(DELAY, this);
        timer.start();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Point p = e.getPoint();
                int px = (int) (p.getX() - (p.getX() % 32));
                int py = (int) (p.getY() - (p.getY() % 32));

                for(Tile s : city.getTiles()) {
                    if (s.getX() == px && s.getY() == py){
                        mouseOverEntity = s.getClass().getSimpleName();
                        if (s instanceof Station) {
                            GameFrame gf = GameLoaderFactory.getInstance().getInstance(GameFrame.class);
                            if (!gf.getCurrentStation().equals(s)) {
                                gf.setCurrentStation((Station) s);
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                isMouseHere = true;
                mouseOverEntity = "Select Station or click on tile to read class name.";
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                isMouseHere = false;
            }

        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePoint = e.getPoint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintCityBackground(g);
        paintRoads(g);
        paintCityForeGround(g);
        paintStatistic(g);
        if (isMouseHere) {
            paintMouse(g);
        }
    }

    private void paintMouse(Graphics g) {
        g.setColor(new Color(0, 0, 0, 30));
        g.fillRect(
                (int) (mousePoint.getX() - (mousePoint.getX() % 32)),
                (int) (mousePoint.getY() - (mousePoint.getY() % 32)),
                32,32);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(18, 169, 180, 70));
        Area shape = new Area(
                new Rectangle(
                        (int) (mousePoint.getX() - (mousePoint.getX() % 32)),
                        (int) (mousePoint.getY() - (mousePoint.getY() % 32)),
                        32,32)
        );
        shape.subtract(new Area(
                new Rectangle(
                        (int) (mousePoint.getX() - (mousePoint.getX() % 32) + 3),
                        (int) (mousePoint.getY() - (mousePoint.getY() % 32) + 3),
                        32 - 6,32 - 6)
        ));
        g2d.fill(shape);
        g.setColor(new Color(0, 0, 0));
        g.drawString(mouseOverEntity, 25, this.getHeight() -15);
    }

    private void paintStatistic(Graphics g) {
            g.setColor(new Color(0, 0, 0));
            g.drawString("Tick: " + GameEngineService.tick, 25, 20);
    }

    private void paintCityForeGround(Graphics g) {
        for (Station station : GameLoaderFactory.getInstance().getInstance(StationService.class).getStations()){
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
