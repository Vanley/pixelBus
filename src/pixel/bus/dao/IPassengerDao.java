package pixel.bus.dao;

import pixel.bus.model.Passenger;

import java.util.List;
import java.util.Queue;

/**
 * Created by vanley on 08/06/2017.
 */
public interface IPassengerDao extends IDao {
    Queue<Passenger> getAll(int stationId, int tick);

    List<Passenger> getAll();

    public Passenger read(String id);

    boolean create(Passenger passenger, int stationId);

    public boolean update(String id, Passenger passenger);

    public boolean delete(String id);

}
