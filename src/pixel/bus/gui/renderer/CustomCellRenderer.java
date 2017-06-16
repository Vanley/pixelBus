package pixel.bus.gui.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by vanley on 14/06/2017.
 */
public class CustomCellRenderer extends DefaultTableCellRenderer {

    public CustomCellRenderer() {
        super.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if(value != null)
            super.setText(value.toString());

        if(row % 2 == 0)
            super.setBackground(Color.WHITE);
        else
            super.setBackground(Color.LIGHT_GRAY);

        if (isSelected)
            super.setBackground(Color.GRAY);
        return this;
    }

}
