package pixel.bus.model;

/**
 * Created by vanley on 29/05/2017.
 */
public class Player {
    private String name = "";
    private int inGameSpeed = 10;

    public Player(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInGameSpeed() {
        return inGameSpeed;
    }

    public void setInGameSpeed(int inGameSpeed) {
        this.inGameSpeed = inGameSpeed;
    }


}
