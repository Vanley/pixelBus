package pixel.bus.service;

import pixel.bus.model.Passenger;
import pixel.bus.model.Station;
import pixel.bus.model.gui.StationComboBoxModel;
import pixel.bus.utils.RandomFromRange;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * Created by vanley on 16/06/2017.
 */
public class StationService {

    private List<Station> stations = new ArrayList<>();

    private ComboBoxModel<String> comboBoxModel;

    public StationService() {
    }

    public void addStation(Station station) {
        stations.add(station);
        updateModels();
    }

    public void updateModels() {
        comboBoxModel = new StationComboBoxModel(updateStationDropBoxModel());
    }

    private String[] updateStationDropBoxModel() {
        String[] stationsNameArray;
        List<String> where = new ArrayList<>();
        for(Station station : stations){
            where.add(station.getName());
        }
        stationsNameArray = new String[ where.size() ];
        stationsNameArray = where.toArray(stationsNameArray);
        return stationsNameArray;
    }

    public void queuePassengers(){
        for(Station station : stations){

            Queue<Passenger> passengerQueue = station.getPassengerQueue();
            Iterator<Passenger> iterator = passengerQueue.iterator();
            for (Iterator<Passenger> it = passengerQueue.iterator(); it.hasNext(); ) {
                Passenger aValue = it.next();
                if(!aValue.isWaiting()) {
                    it.remove();
                    station.addTotalPassengersLeft(1);
                }
            }

            int passengersIn = station.getNextPassengersIn();
            int passengersAmount = station.getNextPassengersAmount();
            if (passengersIn == 0){
                station.addPassengerGroup(passengersAmount);
                int min = 0;
                int max = 100;
                int integerFromRange = RandomFromRange.get(min, max);
                int integerFromRange2 = RandomFromRange.get(min, max);

                passengersIn = (integerFromRange * integerFromRange2)/100 - station.getStationSize();
                passengersAmount = (integerFromRange * integerFromRange2)/1000 + station.getStationSize();
            } else {
                passengersIn--;
            }

            station.setNextPassengersIn(passengersIn);
            station.setNextPassengersAmount(passengersAmount);
        }
    }


    public List<Station> getStations() {
        return stations;
    }

    public ComboBoxModel<String> getComboBoxModel() {
        return comboBoxModel;
    }

    public Station getByName(String name) {
        for (Station station : stations){
            if (name.equals(station.getName()))
                return station;
        }
        return null;
    }
}
