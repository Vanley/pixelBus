package pixel.bus.service;

import pixel.bus.dao.DaoFactory;
import pixel.bus.dao.IGameDao;
import pixel.bus.model.CityLevel;
import pixel.bus.model.Game;
import pixel.bus.utils.DerbyTableUtility;

/**
 * Created by vanley on 15/06/2017.
 */
public class GameSelectionService {
    private final IGameDao gameDB = DaoFactory.getInstance(IGameDao.class);

    public GameSelectionService(){
    }

    public boolean hasInstanceInDB() {
        return gameDB.isSaved();
    }

    public Game load() {
        //TODO load all instances from DB
        //always load from db everything
        //simply sometimes wipe db before loading
        return gameDB.read();
    }

    public Game load(CityLevel cityLevel) {
        //TODO clear DB
        DerbyTableUtility.cleanAll();
        // create new Service factory
        // load all new services persisted in DB
        // on constructor clear static field of pojo
        return new Game(cityLevel);
    }

}
