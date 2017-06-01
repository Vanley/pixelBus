package pixel.bus.engine;

import pixel.bus.model.Player;

import javax.swing.*;

/**
 * Created by vanley on 29/05/2017.
 */
public class GameEngine implements Runnable {
    private Player player;

    private static Timer timer;
    private static Timer passengerTimer;

    public GameEngine(Player player){
        this.player = player;
        timer = new Timer(50 * player.getInGameSpeed(), new GameEngineActionListener());
        passengerTimer = new Timer(500 * player.getInGameSpeed(), new GameEnginePassengerActionListener());
    }

    public void run() {

    }

    public void unPause(){
        timer.start();
        passengerTimer.start();
    }

    public void pause(){
        timer.stop();
        passengerTimer.stop();
    }

}

