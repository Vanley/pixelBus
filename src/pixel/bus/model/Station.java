package pixel.bus.model;

import pixel.bus.gui.GameFrame;
import pixel.bus.model.map.Tile;
import pixel.bus.utils.RandomFromRange;

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

    private String name = "";
    private int nextPassengersIn = 0;
    private int nextPassengersAmount = 0;
    private int stationSize = 0;


    private static List<Station> stations = new ArrayList<>();

    private Queue<Passenger> passengerQueue = new LinkedList<>();

    public Station (int x, int y) {
        super(x, y);
        this.setImage(imageLocation);

        stations.add(this);
        RoadConnection.connect(stations);
    }

    public static List<Station> getStations() {
        return stations;
    }

    public Queue<Passenger> getPassengerQueue() {
        return passengerQueue;
    }

    public void addPassenger(){
        Passenger p = new Passenger();
        passengerQueue.add(p);
        GameFrame.addToTable(p);
    }

    public void addPassengerGroup(int amount){
        while (amount > 0){
            addPassenger();
            amount--;
        }
    }

    private void checkPassengers() {
        Iterator<Passenger> iterator = passengerQueue.iterator();
        for (Iterator<Passenger> it = passengerQueue.iterator(); it.hasNext(); ) {
            Passenger aValue = it.next();
            if(!aValue.isWaiting()) {
                it.remove();
            }
        }
    }

    private void scheduleNextPassengers(){
        int min = 0;
        int max = 100;
        int integerFromRange = RandomFromRange.get(min, max);
        int integerFromRange2 = RandomFromRange.get(min, max);
        nextPassengersIn = (integerFromRange * integerFromRange2)/100 - stationSize;
        nextPassengersAmount = integerFromRange * integerFromRange2/1000 + stationSize;
    }

    public static void queuePassengers(){
        for(Station station : stations){
            station.checkPassengers();
            if (station.nextPassengersIn == 0){
                station.addPassengerGroup(station.nextPassengersAmount);
                station.scheduleNextPassengers();
            } else {
                station.nextPassengersIn--;
            }
        }
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
