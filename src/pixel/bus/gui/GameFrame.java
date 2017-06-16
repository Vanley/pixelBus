package pixel.bus.gui;

import pixel.bus.gui.action_listener.ChangeSpeedAction;
import pixel.bus.gui.renderer.CustomRenderer;
import pixel.bus.model.*;
import pixel.bus.model.enu.GameSpeedEnum;
import pixel.bus.model.gui.PassengerTableModel;
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
    private JTable table2;
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
            }
        });

    }

    private void loadStationDetails() {
        labelStationName.setText(currentStation.getName());
        labelStationLevel.setText("Station Level: " + currentStation.getStationSize());
        labelStationPassengers.setText("Passengers: " + currentStation.getPassengerQueue().size() + " in area:" + currentStation.getNextPassengersAmount() + " (in " + currentStation.getNextPassengersIn() + ")");
        labelPassengersTotal.setText("Passengers totals: " + currentStation.getTotalPassengersIn() + " left: " + currentStation.getTotalPassengersLeft());
        labelStationVehicles.setText("Vehicles: " + currentStation.getVehicles().size());
    }

    private void loadStationDetailsPassengersControls() {

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

    private static PassengerTableModel dtm;
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

        tableAllPassengers.setDefaultRenderer(Object.class, new CustomRenderer());
        tableAllPassengers.setDefaultRenderer(Integer.class, new CustomRenderer());
    }

    public static void addToTable(Passenger p) {
        Passenger.passengerList.add(p);
        dtm.fireTableDataChanged();
    }

    public void updateInfo() {
        loadStationDetails();
    }
}
