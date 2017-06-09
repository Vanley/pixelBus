package pixel.bus.dao;

import pixel.bus.dao.impl.IGameDaoImpl;
import pixel.bus.dao.impl.IPassengerDaoImpl;
import pixel.bus.dao.impl.IStationDaoImpl;

/**
 * Created by vanley on 08/06/2017.
 */
public class DaoFactory {

    private static final IGameDao gameDao = new IGameDaoImpl();
    private static final IPassengerDao passengerDao = new IPassengerDaoImpl();
    private static final IStationDao stationDao = new IStationDaoImpl();

    private DaoFactory() {}

    public static <T extends IDao> T getInstance(Class<T> iClass) {
        if (iClass == IGameDao.class) {
            return (T) gameDao;
        } else if (iClass == IPassengerDao.class) {
            return (T) passengerDao;
        } else if (iClass == IStationDao.class) {
            return (T) stationDao;
        } else {
            return null;
        }
    }

}
