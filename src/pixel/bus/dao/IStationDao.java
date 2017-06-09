package pixel.bus.dao;

import pixel.bus.model.Station;

import java.util.List;

/**
 * Created by vanley on 08/06/2017.
 */
public interface IStationDao extends IDao {
    public List<Station> getAll();

    public Station read(String id);

    public boolean create(Station station);

    public boolean update(String id, Station station);

    public boolean delete(String id);
}
