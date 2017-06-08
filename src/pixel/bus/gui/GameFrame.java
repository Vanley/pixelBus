package pixel.bus.gui;

import pixel.bus.model.Game;
import pixel.bus.model.City;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by vanley on 07/06/2017.
 */
public class GameFrame extends JFrame {
    public JPanel mainPanel;
    private JPanel mapPanel;
    private Game game;


    public GameFrame(Game game) {
        this.game = game;
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();

        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.out.println("ESC pressed");
                    MenuFrame.goToMenu();
                }
            }
        });
    }

    private void createUIComponents() {
        City city = game.getCity();
        mapPanel = new MapPanel(city);
        mapPanel.setMinimumSize(new Dimension(city.getCityX(), city.getCityY()));
        mapPanel.setMaximumSize(new Dimension(city.getCityX(), city.getCityY()));
    }
}
