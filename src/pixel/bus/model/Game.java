package pixel.bus.model;

/**
 * Created by vanley on 07/06/2017.
 */
public class Game {
    public static int tick = 0;
    public int gameSpeed = 10;
    public City city;

    public Game(String cityLevel){
        city = new City(cityLevel);
    }

    public City getCity() {
        return city;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        Game.tick = tick;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }
}
