package pixel.bus.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pixel.bus.model.Station.queuePassengers;

/**
 * Created by vanley on 07/06/2017.
 */
public class Game implements ActionListener {
    private static Timer timer;
    private static int tick = 0;
    private int gameSpeed = 10;

    private City city;

    public Game(CityLevel cityLevel){
        Game.tick = 0;
        this.city = new City(cityLevel);
        setNewTimer(50);
    }

    public Game(CityLevel cityLevel, int gameSpeed, int tick) {
        this.gameSpeed = gameSpeed;
        this.city = new City(cityLevel);
        Game.tick = tick;
        setNewTimer(50);
    }

    public City getCity() {
        return city;
    }

    public static int getTick() {
        return Game.tick;
    }


    public static void tick() {
        Game.tick++;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }




    public void setNewTimer(int delay) {
        timer = new Timer(delay * gameSpeed, this);
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
