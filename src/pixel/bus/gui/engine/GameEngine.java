package pixel.bus.gui.engine;

import pixel.bus.gui.GameFrame;
import pixel.bus.model.AGame;

import javax.swing.*;

/**
 * Created by vanley on 29/05/2017.
 */
public class GameEngine implements Runnable {

    private static Timer timer;

    public GameEngine(){
        timer = new Timer(50 * AGame.gameSpeed, new GameEngineActionListener());
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

