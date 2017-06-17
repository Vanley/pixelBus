package pixel.bus.gui;

import pixel.bus.model.Station;
import pixel.bus.model.enu.VehicleEnum;
import pixel.bus.service.GameLoaderFactory;
import pixel.bus.service.StationService;

import javax.swing.*;
import java.awt.event.*;

public class BusBuyDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonBuy;
    private JButton buttonCancel;
    private JComboBox comboBoxVehicles;
    private JLabel labelDescriptionName;
    private JLabel labelDescriptionPrice;
    private JLabel labelDescriptionCapacity;
    private JLabel labelDescriptionSpeed;

    DefaultListModel model;
    private VehicleEnum selectedVehicle;

    public BusBuyDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonBuy);

        final DefaultComboBoxModel vehicleNames = new DefaultComboBoxModel();

//        vehicleNames.addElement(VehicleEnum.MOTORBIKE);
//        vehicleNames.addElement(VehicleEnum.CAR);
        vehicleNames.addElement(VehicleEnum.VAN);
        vehicleNames.addElement(VehicleEnum.BUS);
        vehicleNames.addElement(VehicleEnum.LONG_BUS);

        comboBoxVehicles.setModel(vehicleNames);
        comboBoxVehicles.setSelectedIndex(0);
        selectedVehicle = (VehicleEnum) comboBoxVehicles.getSelectedItem();
        updateLabels();

        comboBoxVehicles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                selectedVehicle = (VehicleEnum) comboBoxVehicles.getSelectedItem();
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
        comboBoxVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void updateLabels() {
        labelDescriptionName.setText("Type: " + selectedVehicle.getName());
        labelDescriptionPrice.setText("Price: " + selectedVehicle.getInitialPrice() + "$");
        labelDescriptionCapacity.setText("Capacity: " + selectedVehicle.getCapacity() + " people");
        labelDescriptionSpeed.setText("Speed: " + selectedVehicle.getSpeed() + " pixel/tick");
    }

    private void onBuy() {
        // add your code here
        GameLoaderFactory.getInstance().getInstance(GameFrame.class).getCurrentStation().addVehicle(selectedVehicle);
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
