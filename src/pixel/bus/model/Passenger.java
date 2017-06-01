package pixel.bus.model;

import java.time.Instant;

/**
 * Created by vanley on 29/05/2017.
 */
public class Passenger {
    private Instant timeOfArrival;
    private Instant willingnesToWait;
    private String destination;


    public Passenger(){
        this.timeOfArrival = Instant.now();
//        this.
    }
}
