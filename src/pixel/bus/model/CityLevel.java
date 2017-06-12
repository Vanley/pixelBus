package pixel.bus.model;

/**
 * Created by vanley on 21/05/2017.
 */
public enum CityLevel {
//TODO change to enum like CATEGORY to save space in db
    LEVEL_ZERO(" \n"),
    LEVEL_ONE(" \n"
              + "     s\n"
              + "                   s     \n"
              + " \n");

    private String levelMap;

    CityLevel(String levelMap){
        this.levelMap = levelMap;
    }

    public String getMap() {
        return levelMap;
    }
}
