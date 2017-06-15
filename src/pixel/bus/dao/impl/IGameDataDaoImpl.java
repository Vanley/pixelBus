package pixel.bus.dao.impl;

import pixel.bus.dao.IGameDataDao;
import pixel.bus.model.CityLevel;
import pixel.bus.model.GameData;
import pixel.bus.service.GameEngineService;
import pixel.bus.utils.DerbyConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by vanley on 08/06/2017.
 */
public class IGameDataDaoImpl implements IGameDataDao {

    public IGameDataDaoImpl(){}

    @Override
    public GameData read() {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from game_data where id = 1");
            ResultSet cursor = stmt.executeQuery();

            if(cursor.next()){
                GameData gameData = new GameData(
                        CityLevel.valueOf(cursor.getString("cityLevel")),
                        cursor.getInt("gameSpeed"),
                        cursor.getInt("tick")
                );

                return gameData;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean create(GameData gameData) {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("insert into game_data values (?, ?, ?, ?)");
            stmt.setInt(1, 1);
            stmt.setString(2, gameData.getCityLevel().toString());
            stmt.setInt(3, GameEngineService.tick);
            stmt.setInt(4, gameData.getGameSpeed());

            return stmt.executeUpdate() > 0;
        }catch(Exception e){

            update(gameData);
        }

        return false;
    }

    @Override
    public boolean update(GameData gameData) {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("UPDATE game_data SET cityLevel = ?, tick = ?, gameSpeed = ? WHERE id = 1");
            stmt.setString(1, gameData.getCityLevel().toString());
            stmt.setInt(2, GameEngineService.tick);
            stmt.setInt(3, gameData.getGameSpeed());

            return stmt.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isSaved() {
        return read() != null;
    }
}
