package pixel.bus.service;

import pixel.bus.model.GameData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pixel.bus.model.Station.queuePassengers;

/**
 * Created by vanley on 15/06/2017.
 */
public class GameEngineService implements ActionListener {
    private Timer timer;
    public static int tick = 0;
    private GameData gameData;

    public GameEngineService(GameData gameData) {
        this.gameData = gameData;
        tick = gameData.getTick();
        setNewTimer(50);
    }

    public void setNewTimer(int delay) {
        timer = new Timer(delay * gameData.getGameSpeed(), this);
    }

    public void unPause(){
        timer.start();
    }

    public void pause(){
        timer.stop();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        tick++;
        queuePassengers();
    }
}
