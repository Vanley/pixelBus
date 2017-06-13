package pixel.bus.model;

/**
 * Created by vanley on 21/05/2017.
 */
public enum CityLevel {
    LEVEL_ZERO(" \n"),
    LEVEL_ONE(  "rrrrrrrrrrrrrrrrrrrrrr\n"
              + "ppvfpptfrprspfvppprrpp\n"
              + "fppvspptppptrppvpppptr\n"
              + "fpptpptrppprrvpppspptr\n"
              + "ptrrvtpppvpffppfpppffp\n");

    private String levelMap;

    CityLevel(String levelMap){
        this.levelMap = levelMap;
    }

    public String getMap() {
        return levelMap;
    }
}
