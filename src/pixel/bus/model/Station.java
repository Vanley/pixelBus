package pixel.bus.model;

import pixel.bus.dao.DaoFactory;
import pixel.bus.dao.IPassengerDao;
import pixel.bus.dao.IStationDao;
import pixel.bus.gui.GameFrame;
import pixel.bus.model.map.Tile;
import pixel.bus.service.GameEngineService;
import pixel.bus.service.GameLoaderFactory;
import pixel.bus.service.StationService;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

/**
 * Created by vanley on 21/05/2017.
 */
public class Station extends Tile {
    private String imageLocation = "/res/img/city1.png";

    private int id;
    private String name = "Station ";
    private int nextPassengersIn = 0;
    private int nextPassengersAmount = 0;
    private int stationSize = 0;
    private int totalPassengersIn = 0;
    private int totalPassengersLeft = 0;

    private Queue<Passenger> passengerQueue;
    private List<Vehicle> vehicles = new ArrayList<>();

    public Station (int x, int y) {
        super(x, y);
        StationService stationService = GameLoaderFactory.getInstance().getInstance(StationService.class);
        id = stationService.getStations().size();
        name = name + id;
        this.setImage(imageLocation);
        stationService.addStation(this);
        RoadConnection.connect();
        IStationDao stationDao = DaoFactory.getInstance(IStationDao.class);
        IPassengerDao passengerDao = DaoFactory.getInstance(IPassengerDao.class);
        passengerQueue = passengerDao.getAll(id, GameEngineService.tick);
        stationDao.read(this);
    }

    public Queue<Passenger> getPassengerQueue() {
        return passengerQueue;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addPassenger(){
        Passenger p = new Passenger();
        passengerQueue.add(p);
        GameFrame.addToTable(p);
        IPassengerDao passengerDao = DaoFactory.getInstance(IPassengerDao.class);
        passengerDao.create(p, id);
    }

    public void addPassengerGroup(int amount){
        while (amount > 0){
            addPassenger();
            amount--;
            totalPassengersIn++;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNextPassengersIn() {
        return nextPassengersIn;
    }

    public void setNextPassengersIn(int nextPassengersIn) {
        this.nextPassengersIn = nextPassengersIn;
    }

    public int getNextPassengersAmount() {
        return nextPassengersAmount;
    }

    public void setNextPassengersAmount(int nextPassengersAmount) {
        this.nextPassengersAmount = nextPassengersAmount;
    }

    public int getStationSize() {
        return stationSize;
    }

    public int getTotalPassengersIn() {
        return totalPassengersIn;
    }

    public int getTotalPassengersLeft() {
        return totalPassengersLeft;
    }

    public void addTotalPassengersLeft(int totalPassengersLeft) {
        this.totalPassengersLeft += totalPassengersLeft;
    }

    public void setStationSize(int stationSize) {
        this.stationSize = stationSize;
    }

    public void setTotalPassengersIn(int totalPassengersIn) {
        this.totalPassengersIn = totalPassengersIn;
    }

    public void setTotalPassengersLeft(int totalPassengersLeft) {
        this.totalPassengersLeft = totalPassengersLeft;
    }

    @Override
    public void animate(Graphics g) {
        animateDrawPassengerCountBackground(g);
        animateDrawTownEllipse(g);
        animateDrawPassengerCount(g);
    }

    private void animateDrawPassengerCountBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(200, 200, 200));
        for (RoadConnection road : RoadConnection.getRoads()) {
            Rectangle2D rect = new Rectangle(
                    getX(),
                    getY() + getTileSize() - 3,
                    getTileSize(),
                    14);
            g2d.fill(rect);
            g2d.draw(rect);
        }
        g2d.dispose();
    }

    private void animateDrawPassengerCount(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.drawString(" "+ this.getPassengerQueue().size(), getX(), getY() + getTileSize() * 4/3);
    }

    private void animateDrawTownEllipse(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        Ellipse2D ellip = new  Ellipse2D.Double(
                getX(),
                getY() + getTileSize() - 8,
                getTileSize(),
                8);
        g2d.setColor(new Color(180, 180, 180));
        g2d.fill(ellip);
        g2d.draw(ellip);
        g2d.dispose();
    }

}
