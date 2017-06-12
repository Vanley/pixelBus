package pixel.bus.dao.impl;

import pixel.bus.dao.IGameDao;
import pixel.bus.model.CityLevel;
import pixel.bus.model.Game;
import pixel.bus.utils.DerbyConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by vanley on 08/06/2017.
 */
public class IGameDaoImpl implements IGameDao {

    public IGameDaoImpl(){}

    @Override
    public Game read() {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from game where id = 1");
            ResultSet cursor = stmt.executeQuery();

            if(cursor.next()){
                Game game = new Game(
                        CityLevel.valueOf(cursor.getString("cityLevel")),
                        cursor.getInt("gameSpeed"),
                        cursor.getInt("tick")
                );

                return game;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean create(Game game) {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("insert into game values (?, ?, ?, ?)");
            stmt.setInt(1, 1);
            stmt.setString(2, game.getCity().getCityLevel().toString());
            stmt.setInt(3, Game.getTick());
            stmt.setInt(4, game.getGameSpeed());

            return stmt.executeUpdate() > 0;
        }catch(Exception e){

            update(game);
        }

        return false;
    }

    @Override
    public boolean update(Game game) {
        try (Connection conn = DerbyConnectionUtility.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("UPDATE game SET cityLevel = ?, tick = ?, gameSpeed = ? WHERE id = 1");
            stmt.setString(1, game.getCity().getCityLevel().toString());
            stmt.setInt(2, Game.getTick());
            stmt.setInt(3, game.getGameSpeed());

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
