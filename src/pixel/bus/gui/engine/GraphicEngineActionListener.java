package pixel.bus.gui.engine;

import pixel.bus.gui.MapPanel;
import pixel.bus.gui.MenuBus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vanley on 31/05/2017.
 */
public class GraphicEngineActionListener implements ActionListener {
    private MapPanel mapPanel;
    private MenuBus menuBus;
    public GraphicEngineActionListener(MapPanel mapPanel, MenuBus menuBus) {
        this.mapPanel = mapPanel;
        this.menuBus = menuBus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello Graphic World Timer");
        mapPanel.repaint();
        menuBus.repaint();
    }
}
