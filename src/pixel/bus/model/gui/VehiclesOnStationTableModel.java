package pixel.bus.model.gui;

import pixel.bus.gui.GameFrame;
import pixel.bus.model.Vehicle;
import pixel.bus.service.GameLoaderFactory;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by vanley on 17/06/2017.
 */
public class VehiclesOnStationTableModel extends AbstractTableModel {

    private final List<Vehicle> vehicleList;

    private final String[] columnNames = new String[]{
            "Name",
            "Speed",
            "Capacity",
            "Estimate"
    };

    private final Class[] columnClass = new Class[]{
            String.class, Integer.class, Integer.class, Vehicle.class
    };

    public VehiclesOnStationTableModel(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return vehicleList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vehicle row = vehicleList.get(rowIndex);
        if (0 == columnIndex) {
            return row.getName();
        } else if (1 == columnIndex) {
            return row.getSpeed();
        } else if (2 == columnIndex) {
            return row.getCapacity();
        } else if (3 == columnIndex) {
//            return (row.getCapacityEstimation() * 100 / row.getCapacity()); //estimate
            return row; //estimate
//            return (row.getCapacityCurrent() * 100 / row.getCapacity()); //vehicle capacity(0-40) to bar capacity 0-100
        }
        return null;
    }

    public void updateVehicleEstimatedCapacity(Vehicle v){
        int row = vehicleList.indexOf(v);
        this.fireTableCellUpdated(row, 3);
    }
}