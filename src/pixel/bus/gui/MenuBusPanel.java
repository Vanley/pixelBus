package pixel.bus.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vanley on 06/06/2017.
 */
public class MenuBusPanel extends JPanel implements ActionListener {
    private static int DELAY = 1000/10;
    private ArrayList<Image> images = new ArrayList<>();
    private int rotation = 0;
    private Timer timer;

    public MenuBusPanel() {
        images.add(setImage("/res/img/bus/bus1.png"));
        images.add(setImage("/res/img/bus/bus2.png"));
        images.add(setImage("/res/img/bus/bus3.png"));
        images.add(setImage("/res/img/bus/bus4.png"));

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintBus(g);
    }

    private void paintBus(Graphics g) {
        g.drawImage(getImage(), (this.getWidth()/2)-64, (this.getHeight()/2)-64, this);
        g.drawString("PIXEL BUS", 25, 20);
    }

    private Image setImage(String imageLocation){
        URL loc = MenuBusPanel.class.getResource(imageLocation);
        return new ImageIcon(loc).getImage();
    }

    private Image getImage(){
        rotation++;
        if (rotation == images.size()) rotation = 0;
        return images.get(rotation);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 220);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
}
