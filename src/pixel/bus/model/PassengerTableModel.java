package pixel.bus.model;

import pixel.bus.model.Passenger;

import javax.swing.table.AbstractTableModel;
import java.util.List;


/**
 * Created by vanley on 13/06/2017.
 */
public class PassengerTableModel extends AbstractTableModel {

    private final List<Passenger> passengerList;

    private final String[] columnNames = new String[] {
        "tickOfArrival",
        "willWaitTo",
        "destination"
    };

    private final Class[] columnClass = new Class[] {
        Integer.class, Integer.class, String.class
    };

    public PassengerTableModel (List<Passenger> passengerList){
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
            return row.getTickOfArrival();
        }
        else if(1 == columnIndex) {
            return row.getWillWaitTo();
        }
        else if(2 == columnIndex) {
            return row.getDestination();
        }
        return null;
    }

}
