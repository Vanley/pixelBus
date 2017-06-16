package pixel.bus.service;

import pixel.bus.dao.DaoFactory;
import pixel.bus.dao.IGameDataDao;
import pixel.bus.dao.IPassengerDao;
import pixel.bus.dao.IStationDao;
import pixel.bus.dao.impl.IPassengerDaoImpl;
import pixel.bus.dao.impl.IStationDaoImpl;
import pixel.bus.model.CityLevel;
import pixel.bus.model.GameData;
import pixel.bus.utils.DerbyTableUtility;

/**
 * Created by vanley on 15/06/2017.
 */
public class GameLoaderFactory {
    private GameData gameData;
    private GameEngineService gameEngineService;
    private GameService gameService;
    private IPassengerDao passengerDao;
    private IStationDao stationDao;

    private static GameLoaderFactory instance = null;
    protected GameLoaderFactory() {
        // Exists only to defeat instantiation.
    }
    public static GameLoaderFactory getInstance() {
        if(instance == null) {
            instance = new GameLoaderFactory();
        }
        return instance;
    }

    public <T> T getInstance(Class<T> sClass) {
        if (sClass == GameData.class) {
            return (T) gameData;
        } else if (sClass == GameEngineService.class) {
            return (T) gameEngineService;
        } else if (sClass == GameService.class) {
            return (T) gameService;
        } else if (sClass == IPassengerDao.class) {
            return (T) passengerDao;
        } else if (sClass == IStationDao.class) {
            return (T) stationDao;
        } else {
            return null;
        }
    }


    private final IGameDataDao gameDataDao = DaoFactory.getInstance(IGameDataDao.class);

    public boolean hasInstanceInDB() {
        return gameDataDao.isSaved();
    }



    public void load() {
        //TODO load all instances from DB
        //always load from db everything
        //simply sometimes wipe db before loading


        gameData = gameDataDao.read();
        gameEngineService = new GameEngineService(gameData);


//        gameService = new GameService();
//        passengerDao = new IPassengerDaoImpl();
//        stationDao = new IStationDaoImpl();

    }

    public void load(CityLevel cityLevel) {
        DerbyTableUtility.cleanAll();

        gameData = new GameData(cityLevel);
        gameDataDao.create(gameData);

        load();
    }


    public void unLoad() {
        gameEngineService.pause();
        gameDataDao.create(gameData);

        gameData = null;
        gameEngineService = null;

    }
}
