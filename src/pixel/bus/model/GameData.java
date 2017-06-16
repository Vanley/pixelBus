package pixel.bus.model;

import pixel.bus.model.enu.GameSpeedEnum;

/**
 * Created by vanley on 15/06/2017.
 */
public class GameData {
    private CityLevel cityLevel;
    private GameSpeedEnum gameSpeed = GameSpeedEnum.NORMAL;
    private int tick = 0;

    public GameData(CityLevel cityLevel) {
        this.cityLevel = cityLevel;
    }

    public GameData(
            CityLevel cityLevel,
            GameSpeedEnum gameSpeed,
            int tick) {
        this.cityLevel = cityLevel;
        this.gameSpeed = gameSpeed;
        this.tick = tick;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public GameSpeedEnum getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(GameSpeedEnum gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public CityLevel getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(CityLevel cityLevel) {
        this.cityLevel = cityLevel;
    }

}
