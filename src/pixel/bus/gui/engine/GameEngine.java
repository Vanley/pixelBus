package pixel.bus.gui.engine;

import pixel.bus.model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pixel.bus.model.Station.queuePassengers;

/**
 * Created by vanley on 29/05/2017.
 */
public class GameEngine implements Runnable, ActionListener {
    private static Timer timer;
    private static Game game;

    public GameEngine(Game game){
        this.game = game;
        setNewTimer(50);
    }

    public void run() {

    }

    public void setNewTimer(int delay) {
        timer = new Timer(delay * game.getGameSpeed(), this);
    }

    public void unPause(){
        timer.start();
    }

    public void pause(){
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Game.tick();
        queuePassengers();
    }
}

