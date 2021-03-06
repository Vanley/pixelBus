package pixel.bus.gui;

import pixel.bus.gui.action_listener.ChangeSpeedAction;
import pixel.bus.gui.component.MapPanel;
import pixel.bus.gui.dialog.ActionLogDialog;
import pixel.bus.gui.dialog.BusBuyDialog;
import pixel.bus.gui.renderer.CustomCellRenderer;
import pixel.bus.gui.renderer.ProgressCellRenderer;
import pixel.bus.gui.renderer.VehicleProgressCellRenderer;
import pixel.bus.model.*;
import pixel.bus.model.enu.GameSpeedEnum;
import pixel.bus.model.gui.PassengersOnStationTableModel;
import pixel.bus.model.gui.VehiclesOnStationTableModel;
import pixel.bus.service.GameEngineService;
import pixel.bus.service.GameLoaderFactory;
import pixel.bus.service.StationService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vanley on 07/06/2017.
 */
public class GameFrame extends JFrame {
    public JPanel mainPanel;
    private JPanel mapPanel;
    protected JTable tableAllPassengers;
    protected JScrollPane wrapper;
    private JButton buttonGoToMainMenu;
    private JTable tablePassengersOnStation;
    private JTable tableVehiclesOnStation;
    private JRadioButton radioSpeed1;
    private JRadioButton radioSpeed2;
    private JRadioButton radioSpeed3;
    private JRadioButton radioSpeed4;
    private JLabel labelStationPassengers;
    private JComboBox comboBoxStationSelect;
    private JLabel labelStationName;
    private JLabel labelStationVehicles;
    private JLabel labelStationLevel;
    private JTextField textStationName;
    private JLabel labelPassengersTotal;
    private JButton buttonStationNameEdit;
    private JButton buttonStationNameConfirm;
    private JPanel panelStationName;
    private JButton logButton;
    private JButton buttonBuy;
    private final GameLoaderFactory gameLoaderFactory;


    private Station currentStation;
    private PassengersOnStationTableModel passengersOnStationTableModel;

    private VehiclesOnStationTableModel vehiclesOnStationTableModel;

    public GameFrame(final GameLoaderFactory gameLoaderFactory) {
        this.gameLoaderFactory = gameLoaderFactory;
        gameLoaderFactory.getInstance(GameEngineService.class).unPause();
        currentStation = gameLoaderFactory.getInstance((StationService.class)).getStations().get(0);

        loadStationPicker();
        loadStationDetails();
        loadStationDetailsPassengersControls();
        loadStationDetailsVehiclesControls();

        loadSettingsControls();
        showSpeedControlGroup();

        //edit station name
        buttonStationNameEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c = (CardLayout) panelStationName.getLayout();
                c.show(panelStationName, "Card2");
                textStationName.setText(currentStation.getName());
            }
        });

        buttonStationNameConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StationService stationService = gameLoaderFactory.getInstance(StationService.class);
                CardLayout c = (CardLayout) panelStationName.getLayout();
                c.show(panelStationName, "Card1");
                currentStation.setName(textStationName.getText());
                currentStation = stationService.getByName((String) textStationName.getText());
                stationService.updateModels();
                loadStationPicker();
            }
        });

        buttonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog dialog = new BusBuyDialog();
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

                dialog.pack();
                dialog.setVisible(true);
            }
        });

    }

    private void loadStationPicker() {
        StationService stationService = gameLoaderFactory.getInstance(StationService.class);
        ComboBoxModel<String> comboBoxModel = stationService.getComboBoxModel();
        comboBoxStationSelect.setModel(comboBoxModel);

        comboBoxStationSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Station changeTo = stationService.getByName((String) comboBoxModel.getSelectedItem());
                if (changeTo != null)
                    setCurrentStation(changeTo);
            }
        });
        comboBoxStationSelect.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("model"))
                    comboBoxStationSelect.setSelectedItem(currentStation.getName());
            }
        });

    }

    private void loadStationDetails() {
        labelStationName.setText("Name: " + currentStation.getName());
        labelStationLevel.setText("Station Level: " + currentStation.getStationSize());
        labelStationPassengers.setText("Passengers queue: " + currentStation.getPassengerQueue().size() + "  ( " + currentStation.getNextPassengersAmount() + " more in " + currentStation.getNextPassengersIn() + " ticks)");
        labelPassengersTotal.setText("Lifetime passengers: " + currentStation.getTotalPassengersIn() + "; Not taken: " + currentStation.getTotalPassengersLeft());
        labelStationVehicles.setText("Vehicles idle: " + currentStation.getVehicles().size());
    }

    private void loadStationDetailsPassengersControls() {
        passengersOnStationTableModel = new PassengersOnStationTableModel((List<Passenger>) currentStation.getPassengerQueue());
        tablePassengersOnStation.setModel(passengersOnStationTableModel);

        tablePassengersOnStation.setDefaultRenderer(Integer.class, new ProgressCellRenderer());
        tablePassengersOnStation.setDefaultRenderer(String.class, new CustomCellRenderer());

        final AtomicInteger selectedRow=new AtomicInteger(-1);
        final AtomicInteger selectedCol=new AtomicInteger(-1);
        tablePassengersOnStation.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                selectedRow.set(tablePassengersOnStation.getSelectedRow());
                selectedCol.set(tablePassengersOnStation.getSelectedColumn());
            }
        });

        tablePassengersOnStation.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e)
            {
                TableCellEditor editor=tablePassengersOnStation.getCellEditor();
                if (editor!=null) editor.cancelCellEditing();

                final int row = selectedRow.get();
                final int col = selectedCol.get();
                if (row < 0 || col < 0) return;

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        tablePassengersOnStation.changeSelection(row,col, false, false);
                    }
                });
            }
        });
    }

    private void loadStationDetailsVehiclesControls() {
        vehiclesOnStationTableModel = new VehiclesOnStationTableModel(currentStation.getVehicles());
        tableVehiclesOnStation.setModel(vehiclesOnStationTableModel);

        tableVehiclesOnStation.setDefaultRenderer(Vehicle.class, new VehicleProgressCellRenderer());
        tableVehiclesOnStation.setDefaultRenderer(String.class, new CustomCellRenderer());
        tableVehiclesOnStation.setDefaultRenderer(Integer.class, new CustomCellRenderer());
    }

    public VehiclesOnStationTableModel getVehiclesOnStationTableModel() {
        return vehiclesOnStationTableModel;
    }

    private void loadSettingsControls() {
        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    MenuFrame.goToMenuFrame();
                    System.out.println("Closing Game window");
                }
            }
        });

        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
        buttonGoToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuFrame.goToMenuFrame();
                System.out.println("Closing Game window");
            }
        });

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog dialog = new ActionLogDialog();
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(
                        JDialog.DO_NOTHING_ON_CLOSE);

                dialog.pack();
                dialog.setVisible(true);
            }
        });

    }

    private void showSpeedControlGroup(){
        ButtonGroup groupSpeedControl = new ButtonGroup();
        radioSpeed1.setAction(new ChangeSpeedAction(GameSpeedEnum.PAUSE, gameLoaderFactory.getInstance(GameData.class).getGameSpeed()));
        radioSpeed2.setAction(new ChangeSpeedAction(GameSpeedEnum.NORMAL, gameLoaderFactory.getInstance(GameData.class).getGameSpeed()));
        radioSpeed3.setAction(new ChangeSpeedAction(GameSpeedEnum.FAST, gameLoaderFactory.getInstance(GameData.class).getGameSpeed()));
        radioSpeed4.setAction(new ChangeSpeedAction(GameSpeedEnum.EPIC, gameLoaderFactory.getInstance(GameData.class).getGameSpeed()));
        groupSpeedControl.add(radioSpeed1);
        groupSpeedControl.add(radioSpeed2);
        groupSpeedControl.add(radioSpeed3);
        groupSpeedControl.add(radioSpeed4);
    }

    private void createUIComponents() {
        City city = new City(gameLoaderFactory.getInstance(GameData.class).getCityLevel());
        mapPanel = new MapPanel(city);
    }


    public void updateInfo() {
        loadStationDetails();
//        passengersOnStationTableModel.fireTableDataChanged();
        passengersOnStationTableModel.fireTableRowsInserted(0, passengersOnStationTableModel.getRowCount());
//        vehiclesOnStationTableModel.fireTableDataChanged();
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
        loadStationDetails();
        loadStationDetailsPassengersControls();
        loadStationDetailsVehiclesControls();
        comboBoxStationSelect.setSelectedItem(currentStation.getName());
    }


}
