package pixel.bus.dao.impl;

import pixel.bus.dao.IPassengerDao;
import pixel.bus.model.Passenger;
import pixel.bus.utils.DerbyConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by vanley on 08/06/2017.
 */
public class IPassengerDaoImpl implements IPassengerDao {

    public IPassengerDaoImpl(){}

    @Override
    public Queue<Passenger> getAll(int stationId, int tick) {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            Queue<Passenger> result = new LinkedList<>();

            PreparedStatement stmt = conn.prepareStatement("select * from passengers where onStation = ? and willWaitTo >= ?");
            stmt.setInt(1, stationId);
            stmt.setInt(2, tick);
            ResultSet cursor = stmt.executeQuery();

            while(cursor.next()) {
                Passenger p = new Passenger(
                        cursor.getInt("tickOfArrival"),
                        cursor.getInt("willWaitTo"),
                        cursor.getString("destination")
                );
                result.add(p);
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Passenger read(String id) {
        return null;
    }

    @Override
    public boolean create(Passenger passenger, int stationId) {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO passengers (tickOfArrival, willWaitTo, destination, onStation) values (?, ? ,? ,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, passenger.getTickOfArrival());
            stmt.setInt(2, passenger.getWillWaitTo());
            stmt.setString(3, passenger.getDestination());
            stmt.setInt(4, stationId);

            return stmt.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }

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
