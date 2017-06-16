package pixel.bus.gui.renderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by vanley on 16/06/2017.
 */
public class ProgressCellRenderer extends JProgressBar implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int progress = 0;

        if (value instanceof Integer) {
            progress = (int) value;
        }

        setValue(progress);
        return this;
    }

}