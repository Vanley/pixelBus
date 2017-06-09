package pixel.bus.dao.impl;

import pixel.bus.dao.IStationDao;
import pixel.bus.model.Station;

import java.util.List;

/**
 * Created by vanley on 08/06/2017.
 */
public class IStationDaoImpl implements IStationDao {

    public IStationDaoImpl(){}

    @Override
    public List<Station> getAll() {
        return null;
    }

    @Override
    public Station read(String id) {
        return null;
    }

    @Override
    public boolean create(Station station) {
        return false;
    }

    @Override
    public boolean update(String id, Station station) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
