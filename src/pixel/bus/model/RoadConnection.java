package pixel.bus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanley on 12/06/2017.
 */
public class RoadConnection {
    private static List<RoadConnection> roads = new ArrayList<>();

    private final Station source;
    private final Station destination;

    public RoadConnection(Station source, Station destination) {
        this.source = source;
        this.destination = destination;

        roads.add(this);
    }


    public Station getSource() {
        return source;
    }

    public Station getDestination() {
        return destination;
    }

    public static List<RoadConnection> getRoads() {
        return roads;
    }

    public static void connect(List<Station> stations){
        if (stations.size() > 1) {
            new RoadConnection(
                    stations.get(0),
                    stations.get(stations.size()-1)
            );
        }
    };
}
