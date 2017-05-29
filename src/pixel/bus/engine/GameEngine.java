package pixel.bus.engine;

import javax.swing.*;

/**
 * Created by vanley on 29/05/2017.
 */
public class GameEngine implements Runnable {
    private Timer timer = new Timer(500, new GameEngineActionListener());
    public void run() {

    }

    public void unPause(){
        timer.start();
    }

    public void pause(){
        timer.stop();
    }

}

