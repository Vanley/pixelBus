package pixel.bus.gui;

import pixel.bus.dao.DaoFactory;
import pixel.bus.dao.IGameDao;
import pixel.bus.model.Game;
import pixel.bus.model.City;
import pixel.bus.model.Passenger;
import pixel.bus.model.Station;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

/**
 * Created by vanley on 07/06/2017.
 */
public class GameFrame extends JFrame {
    public JPanel mainPanel;
    private JPanel mapPanel;
    private JTable table1;
    private JScrollPane wrapper;
    private JButton button1;
    private final Game game;

    private static PassengerTableModel dtm;

    public GameFrame(final Game game) {
        this.game = game;

        game.unPause();

        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.out.println("ESC pressed");

                    game.pause();

                    IGameDao gameDB = DaoFactory.getInstance(IGameDao.class);
                    gameDB.create(game);

                    MenuFrame.goToMenu();
                    System.out.println("Closing Game window");
                }
            }
        });


        wrapper.setBorder(
                BorderFactory.createTitledBorder (
                        BorderFactory.createEtchedBorder (),
                        "Table Title",
                        TitledBorder.LEADING,
                        TitledBorder.TOP));

        List<Passenger> ppl = new ArrayList<>();
        PassengerTableModel pmodel = new PassengerTableModel(ppl);
        table1.setModel(pmodel);
    dtm = pmodel;

        table1.setDefaultRenderer(Object.class, new CustomRenderer());
        table1.setDefaultRenderer(Integer.class, new CustomRenderer());

        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("repaint tablle" + pmodel.getRowCount() + " -" + Station.getStations().size());
            }
        });
    }

    private void createUIComponents() {
        City city = game.getCity();
        mapPanel = new MapPanel(city);

    }

    public static void addToTable(Passenger p) {
        dtm.customadd(p);
        dtm.fireTableDataChanged();
    }

}
