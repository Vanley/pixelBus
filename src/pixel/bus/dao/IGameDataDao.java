package pixel.bus.dao;

import pixel.bus.model.GameData;

/**
 * Created by vanley on 08/06/2017.
 */
public interface IGameDataDao extends IDao {
    public GameData read();

    public boolean create(GameData game);

    public boolean update(GameData game);

    public boolean isSaved();
}
