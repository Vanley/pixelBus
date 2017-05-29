package pixel.bus.gui;

import pixel.bus.engine.GameEngine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vanley on 21/05/2017.
 */
public class PixelBusGUI {
    private JPanel mainWindow;
    private JPanel panelMap;
    private JTabbedPane tabbedPane1;
    private JRadioButton radioButton1;

    private static GameEngine gameEngine = new GameEngine();
    private static CityMap cityMap = new CityMap();

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new MainFrame());

        EventQueue.invokeLater(gameEngine);
        gameEngine.unPause();
    }

    static class MainFrame implements Runnable {
        @Override
        public void run() {
            JFrame frame = new JFrame("PIXEL BUS");
            frame.setContentPane(new PixelBusGUI().mainWindow);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setTitle("PIXEL BUS");
            frame.pack();
            frame.setVisible(true);
        }
    }

    private void createUIComponents() {
        panelMap = cityMap;
        panelMap.setMinimumSize( new Dimension(cityMap.getCityX(), cityMap.getCityY()));
        panelMap.setMaximumSize( new Dimension(cityMap.getCityX(), cityMap.getCityY()));
        // TODO: place custom component creation code here
    }
}
