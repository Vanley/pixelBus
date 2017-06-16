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
    private JButton button1;
    private JTable table2;
    private JTable table3;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private final GameLoaderFactory gameLoaderFactory;

    public GameFrame(final GameLoaderFactory gameLoaderFactory) {
        this.gameLoaderFactory = gameLoaderFactory;
        gameLoaderFactory.getInstance(GameEngineService.class).unPause();


        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.out.println("ESC pressed, opening main menu");

                    gameLoaderFactory.unLoad();
                    MenuFrame.goToMenuFrame();
                    System.out.println("Closing Game window");
                }
            }
        });

        //TABLE LOADERS
        loadTableAllPassengers();

        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("repaint tablle" + gameLoaderFactory.getInstance(GameData.class).getGameSpeed() + " -" + Station.getStations().size());
            }
        });

        showSpeedControlGroup();
    }


    private void showSpeedControlGroup(){
        ButtonGroup groupSpeedControl = new ButtonGroup();
//        Action action = new ChangeSpeedAction(gameLoaderFactory.getInstance(GameData.class).getGameSpeed(), true);
        radioButton1.setAction(new ChangeSpeedAction(GameSpeedEnum.PAUSE, gameLoaderFactory.getInstance(GameData.class).getGameSpeed()));
        radioButton2.setAction(new ChangeSpeedAction(GameSpeedEnum.NORMAL, gameLoaderFactory.getInstance(GameData.class).getGameSpeed()));
        radioButton3.setAction(new ChangeSpeedAction(GameSpeedEnum.FAST, gameLoaderFactory.getInstance(GameData.class).getGameSpeed()));
        radioButton4.setAction(new ChangeSpeedAction(GameSpeedEnum.EPIC, gameLoaderFactory.getInstance(GameData.class).getGameSpeed()));
        groupSpeedControl.add(radioButton1);
        groupSpeedControl.add(radioButton2);
        groupSpeedControl.add(radioButton3);
        groupSpeedControl.add(radioButton4);

        //TODO GameEngine service
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
