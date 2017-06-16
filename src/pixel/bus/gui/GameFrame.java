package pixel.bus.gui;

import pixel.bus.gui.renderer.CustomRenderer;
import pixel.bus.model.*;
import pixel.bus.service.GameEngineService;
import pixel.bus.service.GameLoaderFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private JLabel labelStationPassengerCount;
    private JComboBox comboBoxStationSelect;
    private final GameLoaderFactory gameLoaderFactory;

    public GameFrame(final GameLoaderFactory gameLoaderFactory) {
        this.gameLoaderFactory = gameLoaderFactory;
        gameLoaderFactory.getInstance(GameEngineService.class).unPause();


        loadStationDetails();
        loadStationDetailsPassengersControls();


        //TABLE LOADERS
        loadTableAllPassengers();

        loadSettingsControls();
        showSpeedControlGroup();



    }

    private void loadStationDetails() {
//        comboBoxStationSelect.
    }

    private void loadStationDetailsPassengersControls() {
        labelStationPassengerCount.setText("Count: " + 0);
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

}
