package pixel.bus.model.enu;

/**
 * Created by vanley on 16/06/2017.
 */
public enum GameSpeedEnum {
    PAUSE("Paused", 0, "||"),
    NORMAL("Normal", 500, ">"),
    FAST("Fast", 200, ">>"),
    EPIC("Very Fast", 50, ">>>");

    final private String name;
    final private int speed;
    final private String symbol;

    GameSpeedEnum(String name,
                  int speed,
                  String symbol
    ) {
        this.name = name;
        this.speed = speed;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public String getSymbol() {
        return symbol;
    }

    public static GameSpeedEnum valueOfAttribute(String attribute) {
        switch (attribute) {
            case "||": return PAUSE;
            case ">": return NORMAL;
            case ">>": return FAST;
            case ">>>": return EPIC;
            default: throw new IllegalArgumentException(String.valueOf(attribute));
        }
    }
}
