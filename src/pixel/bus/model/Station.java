package pixel.bus.model;

import pixel.bus.dao.DaoFactory;
import pixel.bus.dao.IPassengerDao;
import pixel.bus.dao.IStationDao;
import pixel.bus.gui.GameFrame;
import pixel.bus.model.enu.VehicleEnum;
import pixel.bus.model.map.Tile;
import pixel.bus.service.GameEngineService;
import pixel.bus.service.GameLoaderFactory;
import pixel.bus.service.StationService;
import pixel.bus.utils.RandomFromRange;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vanley on 21/05/2017.
 */
public class Station extends Tile {
    private static String imageLocation = "/res/img/city/city0.png";
    private static String[] imageLocations = new String[]{
            "/res/img/city/city1.png",
            "/res/img/city/city2.png",
            "/res/img/city/city3.png",
            "/res/img/city/city4.png",
            "/res/img/city/city5.png",
            "/res/img/city/city6.png",
            "/res/img/city/city7.png",
            "/res/img/city/city8.png",
            "/res/img/city/city9.png"};

    private int id;
    private String name = "Station ";
    private int nextPassengersIn = 0;
    private int nextPassengersAmount = 0;
    private int stationSize = 0;
    private AtomicInteger totalPassengersIn = new AtomicInteger(0);
    private int totalPassengersLeft = 0;

    private Queue<Passenger> passengerQueue = new ConcurrentLinkedQueue<>();
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
        IPassengerDao passengerDao = DaoFactory.getInstance(IPassengerDao.class);
        passengerDao.create(p, id);
    }

    public void addPassengerGroup(int amount){
        while (amount > 0){
            addPassenger();
            amount--;
            totalPassengersIn.getAndIncrement();
        }
    }

    public void addVehicle(VehicleEnum selectedVehicle) {
        Vehicle v = new Vehicle(selectedVehicle);
        vehicles.add(v);
        //todo substract money
        //todo add to DB
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

    public AtomicInteger getTotalPassengersIn() {
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

    public void setTotalPassengersIn(AtomicInteger totalPassengersIn) {
        this.totalPassengersIn = totalPassengersIn;
    }
    public void setTotalPassengersIn(int totalPassengersIn) {
        this.totalPassengersIn.set(totalPassengersIn);
    }

    public void setTotalPassengersLeft(int totalPassengersLeft) {
        this.totalPassengersLeft = totalPassengersLeft;
    }

    @Override
    public void animate(Graphics g) {
        animateBuilding(g);
        animateDrawPassengerCountBackground(g);
        animateDrawTownEllipse(g);
        animateDrawPassengerCount(g);
    }

    private void animateBuilding(Graphics g) {
        if (next == 0) {
            this.setImage(imageLocations[RandomFromRange.get(0, imageLocations.length-1)]);
            //sequential
//            this.setImage(imageLocations[current]);
//            current++;
//            if(current >= imageLocations.length)
//                current = 0;
            next = RandomFromRange.get(5, 50);
        }
        next--;
    }

    private void animateDrawPassengerCountBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        Station currentStation = GameLoaderFactory.getInstance().getInstance(GameFrame.class).getCurrentStation();
        if(currentStation != null && currentStation.equals(this)){
            g2d.setColor(new Color(18, 169, 180));
        } else {
            g2d.setColor(new Color(180, 180, 180));
        }
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

        Station currentStation = GameLoaderFactory.getInstance().getInstance(GameFrame.class).getCurrentStation();
        if(currentStation != null && currentStation.equals(this)){
            g2d.setColor(new Color(39, 145, 180));
        } else {
            g2d.setColor(new Color(180, 180, 180));
        }

        g2d.fill(ellip);
        g2d.draw(ellip);
        g2d.dispose();
    }


}
