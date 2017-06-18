package pixel.bus.service;

import pixel.bus.dao.*;
import pixel.bus.dao.impl.IPassengerDaoImpl;
import pixel.bus.dao.impl.IStationDaoImpl;
import pixel.bus.gui.GameFrame;
import pixel.bus.model.CityLevel;
import pixel.bus.model.GameData;
import pixel.bus.model.Station;
import pixel.bus.utils.DerbyTableUtility;

import java.awt.*;

/**
 * Created by vanley on 15/06/2017.
 */
public class GameLoaderFactory {
    private GameData gameData;
    private GameEngineService gameEngineService;
    private GameService gameService;
    private StationService stationService;

    private GameFrame gameFrame;

    private final IGameDataDao gameDataDao = DaoFactory.getInstance(IGameDataDao.class);
    private final IStationDao stationDao = DaoFactory.getInstance(IStationDao.class);

    private static GameLoaderFactory instance = null;

    private GameLoaderFactory() {
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
        } else if (sClass == GameFrame.class) {
            return (T) gameFrame;
        } else if (sClass == GameService.class) {
            return (T) gameService;
        } else if (sClass == StationService.class) {
            return (T) stationService;
        } else {
            return null;
        }
    }


    public boolean hasInstanceInDB() {
        return gameDataDao.isSaved();
    }

    public void load() {
        gameData = gameDataDao.read();
        gameEngineService = new GameEngineService(gameData);
        stationService = new StationService();

//        gameService = new GameService();

        //TODO load from DB

    }

    public void load(CityLevel cityLevel) {
        DerbyTableUtility.cleanAll();
        GameEngineService.tick = 0;
        gameData = new GameData(cityLevel);
        gameDataDao.create(gameData);

        load();
    }


    public void unLoad() {
        gameEngineService.pause();
        Thread t1 = new Thread(
                new Runnable() {
                    public void run () {
                        gameDataDao.create(gameData);
                    }
                }
        );
        Thread t2 = new Thread(
                new Runnable() {
                    public void run () {
                        stationDao.create(stationService.getStations());
                    }
                }
        );
        //passengers saved in runtime

        //todo add saving vehicles

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gameData = null;
        gameEngineService = null;
        stationService = null;

    }

    public void injectGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
}
