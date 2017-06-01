package pixel.bus.gui;

import pixel.bus.engine.GameEngine;
import pixel.bus.engine.GraphicEngine;
import pixel.bus.model.City;
import pixel.bus.model.CityLevels;
import pixel.bus.model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vanley on 21/05/2017.
 */
public class Game {
    private JPanel mainWindow;
    private JPanel panelMap;
    private JTabbedPane tabbedPane1;
    private JRadioButton radioButton1;

    private static Player player = new Player();
    private static City city = new City(CityLevels.cityLevel1);

    private static MapPanel mapPanel = new MapPanel(city);

    private static GameEngine gameEngine = new GameEngine(player);
    private static GraphicEngine graphicEngine = new GraphicEngine(mapPanel);


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new MainFrame());

        EventQueue.invokeLater(gameEngine);
        EventQueue.invokeLater(graphicEngine);
        gameEngine.unPause();
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
        panelMap = mapPanel;
        panelMap.setMinimumSize( new Dimension(city.getCityX(), city.getCityY()));
        panelMap.setMaximumSize( new Dimension(city.getCityX(), city.getCityY()));
        // TODO: place custom component creation code here
    }
}
