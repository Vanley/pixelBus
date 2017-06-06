package pixel.bus.gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vanley on 06/06/2017.
 */
public class MenuBus extends JPanel {

    private ArrayList<Image> images = new ArrayList<>();
    private int rotation = 0;

    public MenuBus() {
        images.add(setImage("/res/img/bus1.png"));
        images.add(setImage("/res/img/bus2.png"));
        images.add(setImage("/res/img/bus3.png"));
        images.add(setImage("/res/img/bus4.png"));
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
        URL loc = MenuBus.class.getResource(imageLocation);
        return new ImageIcon(loc).getImage();
    }

    private Image getImage(){
        rotation++;
        if (rotation == images.size()) rotation = 0;
        return images.get(rotation);
    }
}
