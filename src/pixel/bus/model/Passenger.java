package pixel.bus.model;

/**
 * Created by vanley on 29/05/2017.
 */
public class Passenger {
    private int timeOfArrival;
    private int willWaitTo;
    private String destination;


    public Passenger(){
        this.timeOfArrival = AGame.tick;
        this.willWaitTo = timeOfArrival + generateWillWaitTo();
    }

    public boolean isWaiting() {
        return AGame.tick < willWaitTo;
        // dao station, passanger, gaame, vehicle
        //add different frame for menu or add null game for preloading pannels
    }

    private static int generateWillWaitTo(){
        int min = 50;
        int max = 100;
        int integerFromRange = Utilities.getRandomIntegerFromRange(min, max);
        return (integerFromRange * integerFromRange)/100 * 2 ;
    }
}
