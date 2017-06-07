package pixel.bus.gui;

import pixel.bus.gui.engine.GameEngine;
import pixel.bus.gui.engine.GraphicEngine;
import pixel.bus.model.City;
import pixel.bus.model.CityLevels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by vanley on 21/05/2017.
 */
public class Game {
    private static JFrame frame = new JFrame("PIXEL BUS");
    private JPanel mainWindow;
    private JPanel cardGame;
    private JPanel cardMenu;
    private JPanel cardOther;
    private JPanel panelMap;
    private JPanel animateBusPanel;
    private JButton btnContinue;
    private JButton btnStartNew;
    private JButton btnExit;

    public static int tick = 0;
    public static int gameSpeed = 10;

    private static City city = new City(CityLevels.cityLevel1);

    private static MapPanel mapPanel = new MapPanel(city);
    private static MenuBus menuBus = new MenuBus();

    private static GameEngine gameEngine = new GameEngine();
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
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardMenu.setVisible(false);
                cardGame.setVisible(true);
            }
        });
        btnStartNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardMenu.setVisible(false);
                cardGame.setVisible(true);
                EventQueue.invokeLater(gameEngine);
                gameEngine.unPause();
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new MainFrame());

        EventQueue.invokeLater(graphicEngine);
        graphicEngine.unPause();
    }

    static class MainFrame implements Runnable {
        @Override
        public void run() {
            frame.setContentPane(new Game().mainWindow);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
