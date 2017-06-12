package pixel.bus.gui;

import pixel.bus.dao.DaoFactory;
import pixel.bus.dao.IGameDao;
import pixel.bus.model.Game;
import pixel.bus.model.City;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by vanley on 07/06/2017.
 */
public class GameFrame extends JFrame {
    public JPanel mainPanel;
    private JPanel mapPanel;
    private final Game game;


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

        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
    }

    private void createUIComponents() {
        City city = game.getCity();
        mapPanel = new MapPanel(city);
    }
}
