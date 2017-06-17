package pixel.bus.dao;

import pixel.bus.model.Passenger;

import java.util.List;
import java.util.Queue;

/**
 * Created by vanley on 08/06/2017.
 */
public interface IPassengerDao extends IDao {
    public Queue<Passenger> getAll(int stationId, int tick);

    public Passenger read(String id);

    public boolean create(Passenger passenger, int stationId);

    public boolean update(String id, Passenger passenger);

    public boolean delete(String id);
}
