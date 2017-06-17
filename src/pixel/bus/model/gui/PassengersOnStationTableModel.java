package pixel.bus.model.gui;

import pixel.bus.model.Passenger;
import pixel.bus.service.GameEngineService;
import pixel.bus.service.GameLoaderFactory;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by vanley on 16/06/2017.
 */
public class PassengersOnStationTableModel extends AbstractTableModel {

    private final List<Passenger> passengerList;

    private final String[] columnNames = new String[] {
            "Arrived",
            "Will Wait",
            "Happiness",
            "Destination"
    };

    private final Class[] columnClass = new Class[] {
            String.class, String.class, Integer.class, String.class
    };

    public PassengersOnStationTableModel (List<Passenger> passengerList){
        this.passengerList = passengerList;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return passengerList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Passenger row = passengerList.get(rowIndex);
        if(0 == columnIndex) {
            return "" + row.getTickOfArrival();
        }
        else if(1 == columnIndex) {
            return "" + (row.getWillWaitTo() - GameEngineService.tick);
        }
        else if(2 == columnIndex) {
            return (row.getWillWaitTo() - GameEngineService.tick) * 2;  //decrease bar when its below 50 (as passengers are happy on start and get annoyed and left )
        }
        else if(3 == columnIndex) {
            return "Everywhere"; // + row.getDestination();
        }
        return null;
    }
}
