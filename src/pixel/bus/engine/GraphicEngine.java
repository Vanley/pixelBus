package pixel.bus.engine;

import pixel.bus.gui.MapPanel;
import pixel.bus.gui.MenuBus;

import javax.swing.*;

/**
 * Created by vanley on 31/05/2017.
 */
public class GraphicEngine implements Runnable {

    private static int DELAY = 1000/10;

    private static Timer timer;

    public GraphicEngine(MapPanel mapPanel, MenuBus menuBus){
        timer = new Timer(DELAY, new GraphicEngineActionListener(mapPanel, menuBus) );
    }

    public void run() {

    }

    public void unPause(){
        timer.start();
    }

    public void pause(){
        timer.stop();
    }
}
