package pixel.bus.gui;

import pixel.bus.model.AGame;
import pixel.bus.model.City;
import pixel.bus.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        paintCity(g);
        paintStatistic(g);
    }

    private void paintStatistic(Graphics g) {
            g.setColor(new Color(0, 0, 0));
            g.drawString("Tick: " + AGame.tick, 25, 20);
    }

    private void paintCity(Graphics g) {
        g.setColor(new Color(149, 250, 80));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (Tile tile : city.getTiles()) {
            if (tile != null) {
                g.drawImage(tile.getImage(), tile.x(), tile.y(), this);
                tile.animate(g);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
}
