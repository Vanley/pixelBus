package pixel.bus.model;

import pixel.bus.service.GameEngineService;
import pixel.bus.utils.RandomFromRange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanley on 29/05/2017.
 */
public class Passenger {
    public static final List<Passenger> passengerList = new ArrayList<>();

    private int tickOfArrival;
    private int willWaitTo;
    private String destination;

    public Passenger(){
        this.tickOfArrival = GameEngineService.tick;
        this.willWaitTo = tickOfArrival + generateWillWaitTo();
    }

    public Passenger(int tickOfArrival, int willWaitTo, String destination) {
        this.tickOfArrival = tickOfArrival;
        this.willWaitTo = willWaitTo;
        this.destination = destination;
    }

    public boolean isWaiting() {
        return GameEngineService.tick < willWaitTo;
    }

    private static int generateWillWaitTo(){
        int min = 50;
        int max = 100;
        int integerFromRange = RandomFromRange.get(min, max);
        return (integerFromRange * integerFromRange)/100 * 2 ;
    }

    public int getTickOfArrival() {
        return tickOfArrival;
    }

    public int getWillWaitTo() {
        return willWaitTo;
    }

    public String getDestination() {
        return destination;
    }
}
