package pixel.bus.dao.impl;

import pixel.bus.dao.IStationDao;
import pixel.bus.model.CityLevel;
import pixel.bus.model.GameData;
import pixel.bus.model.Station;
import pixel.bus.model.enu.GameSpeedEnum;
import pixel.bus.service.GameEngineService;
import pixel.bus.utils.DerbyConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public Station read(Station station) {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from stations where id = ?");
            stmt.setInt(1, station.getId());
            ResultSet cursor = stmt.executeQuery();

            if(cursor.next()) {
                station.setName(cursor.getString("name"));
                station.setNextPassengersIn(cursor.getInt("nextPassengersIn"));
                station.setNextPassengersAmount(cursor.getInt("nextPassengersAmount"));
                station.setStationSize(cursor.getInt("stationSize"));
                station.setTotalPassengersIn(cursor.getInt("totalPassengersIn"));
                station.setTotalPassengersLeft(cursor.getInt("totalPassengersLeft"));
//id INT PRIMARY KEY, name VARCHAR(32), nextPassengersIn INT, nextPassengersAmount INT, stationSize INT, totalPassengersIn INT, totalPassengersLeft INT)
                return station;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Station station) {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("insert into stations values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, station.getId());
            stmt.setString(2, station.getName());
            stmt.setInt(3, station.getNextPassengersIn());
            stmt.setInt(4, station.getNextPassengersAmount());
            stmt.setInt(5, station.getStationSize());
            stmt.setInt(6, station.getTotalPassengersIn());
            stmt.setInt(7, station.getTotalPassengersLeft());

            return stmt.executeUpdate() > 0;
        } catch (Exception e){
            update(station);
        }

        return false;
    }

    @Override
    public void create(List<Station> stations) {
        for (Station station : stations) {
            create(station);
        }
    }

    @Override
    public boolean update(Station station) {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(
                    "update stations set name = ?, nextPassengersIn = ?, nextPassengersAmount = ?, stationSize = ?, " +
                            "totalPassengersIn = ?, totalPassengersLeft = ? where id = ?");
            stmt.setString(1, station.getName());
            stmt.setInt(2, station.getNextPassengersIn());
            stmt.setInt(3, station.getNextPassengersAmount());
            stmt.setInt(4, station.getStationSize());
            stmt.setInt(5, station.getTotalPassengersIn());
            stmt.setInt(6, station.getTotalPassengersLeft());
            stmt.setInt(7, station.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
