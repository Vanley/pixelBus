package pixel.bus.gui;

import pixel.bus.engine.GameEngine;
import pixel.bus.engine.GraphicEngine;
import pixel.bus.model.City;
import pixel.bus.model.CityLevels;
import pixel.bus.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by vanley on 21/05/2017.
 */
public class Game {
    private JPanel mainWindow;
    private JPanel panelMap;
    private JPanel cardGame;
    private JPanel cardMenu;
    private JPanel cardOther;
    private JButton button1;
    private JPanel animateBusPanel;

    private static Player player = new Player();
    private static City city = new City(CityLevels.cityLevel1);

    private static MapPanel mapPanel = new MapPanel(city);
    private static MenuBus menuBus = new MenuBus();

    private static GameEngine gameEngine = new GameEngine(player);
    private static GraphicEngine graphicEngine = new GraphicEngine(mapPanel, menuBus);


    public Game() {
        mainWindow.setFocusable(true);
        mainWindow.requestFocusInWindow();
        cardMenu.setVisible(true);
        cardGame.setVisible(false);

        mainWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.out.println("ESC pressed");
                    cardMenu.setVisible(!cardMenu.isVisible());
                    cardGame.setVisible(!cardGame.isVisible());
                }
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new MainFrame());

        EventQueue.invokeLater(gameEngine);
        EventQueue.invokeLater(graphicEngine);
//        gameEngine.unPause();
        graphicEngine.unPause();
    }

    static class MainFrame implements Runnable {
        @Override
        public void run() {
            JFrame frame = new JFrame("PIXEL BUS");
            frame.setContentPane(new Game().mainWindow);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setTitle("PIXEL BUS");
            frame.pack();
            frame.setVisible(true);
        }
    }

    private void createUIComponents() {
        animateBusPanel = menuBus;
        animateBusPanel.setMinimumSize(new Dimension(100, 100));
        panelMap = mapPanel;
        panelMap.setMinimumSize(new Dimension(city.getCityX(), city.getCityY()));
        panelMap.setMaximumSize(new Dimension(city.getCityX(), city.getCityY()));
        // TODO: place custom component creation code here
    }
}
