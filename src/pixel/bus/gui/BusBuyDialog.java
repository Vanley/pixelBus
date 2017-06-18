package pixel.bus.gui;

import pixel.bus.model.enu.VehicleEnum;
import pixel.bus.service.GameLoaderFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class BusBuyDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonBuy;
    private JButton buttonCancel;
    private JLabel labelDescriptionName;
    private JLabel labelDescriptionPrice;
    private JLabel labelDescriptionCapacity;
    private JLabel labelDescriptionSpeed;
    private JList listOfVehiclesEnum;

    DefaultListModel model;
    private VehicleEnum selectedVehicle;

    public BusBuyDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonBuy);


        listOfVehiclesEnum.setListData(VehicleEnum.values());
        VehicleEnum selectedVehicleEnum = (VehicleEnum) listOfVehiclesEnum.getSelectedValue();
        listOfVehiclesEnum.setSelectedIndex(0);
        updateLabels();

        listOfVehiclesEnum.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateLabels();
            }
        });


        buttonBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBuy();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void updateLabels() {
        selectedVehicle = (VehicleEnum) listOfVehiclesEnum.getSelectedValue();
        labelDescriptionName.setText("Type: " + selectedVehicle.getName());
        labelDescriptionPrice.setText("Price: " + selectedVehicle.getInitialPrice() + "$");
        labelDescriptionCapacity.setText("Capacity: " + selectedVehicle.getCapacity() + " people");
        labelDescriptionSpeed.setText("Speed: " + selectedVehicle.getSpeed() + " pixel/tick");
    }

    private void onBuy() {
        // add your code here
        GameLoaderFactory.getInstance().getInstance(GameFrame.class).getCurrentStation().addVehicle(selectedVehicle);
        GameLoaderFactory.getInstance().getInstance(GameFrame.class).getVehiclesOnStationTableModel().fireTableDataChanged();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        BusBuyDialog dialog = new BusBuyDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
