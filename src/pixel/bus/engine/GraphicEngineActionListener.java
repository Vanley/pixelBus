package pixel.bus.engine;

import pixel.bus.gui.MapPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vanley on 31/05/2017.
 */
public class GraphicEngineActionListener implements ActionListener {
    private MapPanel mapPanel;

    public GraphicEngineActionListener(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello Graphic World Timer");
        mapPanel.repaint();
    }
}
