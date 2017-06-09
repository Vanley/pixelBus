package pixel.bus.dao;

import pixel.bus.model.Game;

import java.util.List;

/**
 * Created by vanley on 08/06/2017.
 */
public interface IGameDao extends IDao {
    public Game read();

    public boolean create(Game game);

    public boolean update(Game game);

    public boolean isSaved();
}
