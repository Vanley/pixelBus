package pixel.bus.gui.renderer;

import pixel.bus.model.Vehicle;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by vanley on 18/06/2017.
 */

public class VehicleProgressCellRenderer extends JProgressBar implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Vehicle v = (Vehicle) value;

        setStringPainted(true);
        setString(v.getCapacityEstimation() + "/" + v.getCapacity());

        setValue(v.getCapacityEstimation() * 100 / v.getCapacity());
        return this;
    }

}
