package pixel.bus.dao.impl;

import pixel.bus.dao.IPassengerDao;
import pixel.bus.model.Passenger;

import java.util.List;

/**
 * Created by vanley on 08/06/2017.
 */
public class IPassengerDaoImpl implements IPassengerDao {

    public IPassengerDaoImpl(){}

    @Override
    public List<Passenger> getAll() {
        return null;
    }

    @Override
    public Passenger read(String id) {
        return null;
    }

    @Override
    public boolean create(Passenger passenger) {
        return false;
    }

    @Override
    public boolean update(String id, Passenger passenger) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
