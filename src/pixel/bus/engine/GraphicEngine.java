package pixel.bus.engine;

import pixel.bus.gui.MapPanel;

import javax.swing.*;

/**
 * Created by vanley on 31/05/2017.
 */
public class GraphicEngine implements Runnable {

    private static int FREQUENCY = 1000/23;

    private MapPanel mapPanel;

    private static Timer timer;

    public GraphicEngine(MapPanel mapPanel){
        this.mapPanel = mapPanel;
        timer = new Timer(FREQUENCY, new GraphicEngineActionListener());
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
