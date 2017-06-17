package pixel.bus.gui;

import pixel.bus.gui.action_listener.ChangeSpeedAction;
import pixel.bus.gui.renderer.CustomCellRenderer;
import pixel.bus.gui.renderer.ProgressCellRenderer;
import pixel.bus.model.*;
import pixel.bus.model.enu.GameSpeedEnum;
import pixel.bus.model.gui.PassengerTableModel;
import pixel.bus.model.gui.PassengersOnStationTableModel;
import pixel.bus.service.GameEngineService;
import pixel.bus.service.GameLoaderFactory;
import pixel.bus.service.StationService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

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
    private JTable labelTable;
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
    private final GameLoaderFactory gameLoaderFactory;

    private Station currentStation;
    private PassengersOnStationTableModel passengersOnStationTableModel;
    private static PassengerTableModel dtm;

    public GameFrame(final GameLoaderFactory gameLoaderFactory) {
        this.gameLoaderFactory = gameLoaderFactory;
        gameLoaderFactory.getInstance(GameEngineService.class).unPause();
        currentStation = gameLoaderFactory.getInstance((StationService.class)).getStations().get(0);

        loadStationPicker();
        loadStationDetails();
        loadStationDetailsPassengersControls();

        //TABLE LOADERS
        loadTableAllPassengers();

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
    }

    private void loadStationPicker() {
        StationService stationService = gameLoaderFactory.getInstance(StationService.class);
        ComboBoxModel<String> comboBoxModel = stationService.getComboBoxModel();
        comboBoxStationSelect.setModel(comboBoxModel);

        currentStation = stationService.getByName((String) comboBoxModel.getSelectedItem());

        comboBoxStationSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Station changeTo = stationService.getByName((String) comboBoxModel.getSelectedItem());
                currentStation =  changeTo == null ? currentStation : changeTo;
                loadStationDetails();
                loadStationDetailsPassengersControls();
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
        passengersOnStationTableModel = new PassengersOnStationTableModel((java.util.List<Passenger>) currentStation.getPassengerQueue());
        tablePassengersOnStation.setModel(passengersOnStationTableModel);

        tablePassengersOnStation.setDefaultRenderer(Integer.class, new ProgressCellRenderer());
        tablePassengersOnStation.setDefaultRenderer(String.class, new CustomCellRenderer());
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

    public void loadTableAllPassengers() {
        wrapper.setBorder(
                BorderFactory.createTitledBorder (
                        BorderFactory.createEtchedBorder (),
                        "Table Title",
                        TitledBorder.LEADING,
                        TitledBorder.TOP));

        PassengerTableModel pmodel = new PassengerTableModel(Passenger.passengerList);
        tableAllPassengers.setModel(pmodel);
        dtm = pmodel;

        tableAllPassengers.setDefaultRenderer(Object.class, new CustomCellRenderer());
        tableAllPassengers.setDefaultRenderer(Integer.class, new CustomCellRenderer());
    }

    public static void addToTable(Passenger p) {
        Passenger.passengerList.add(p);
        dtm.fireTableDataChanged();
    }

    public void updateInfo() {
        loadStationDetails();
        passengersOnStationTableModel.fireTableDataChanged();
    }
}
