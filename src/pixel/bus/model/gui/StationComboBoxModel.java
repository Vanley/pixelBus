package pixel.bus.model.gui;

import javax.swing.*;

/**
 * Created by vanley on 16/06/2017.
 */
public class StationComboBoxModel extends DefaultComboBoxModel<String> {
    public StationComboBoxModel(String[] items) {
        super(items);
    }

    @Override
    public String getSelectedItem() {
        String selectedItem = (String) super.getSelectedItem();

        return selectedItem;
    }
}