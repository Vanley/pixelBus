package pixel.bus.dao;

import pixel.bus.model.Passenger;

import java.util.List;

/**
 * Created by vanley on 08/06/2017.
 */
public interface IPassengerDao extends IDao {
    public List<Passenger> getAll();

    public Passenger read(String id);

    public boolean create(Passenger passenger);

    public boolean update(String id, Passenger passenger);

    public boolean delete(String id);
}
