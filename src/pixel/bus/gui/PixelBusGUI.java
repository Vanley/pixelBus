package pixel.bus.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vanley on 21/05/2017.
 */
public class PixelBusGUI {
    private JPanel mainWindow;
    private JPanel panelMap;
//    private static CityMap cityMap = ;

    public static void main(String[] args) {

        JFrame frame = new JFrame("PIXEL BUS");
        frame.setContentPane(new PixelBusGUI().mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("PIXEL BUS");
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        CityMap cityMap = new CityMap();
        panelMap = cityMap;
        panelMap.setMinimumSize( new Dimension(cityMap.getCityX(), cityMap.getCityY()));
        panelMap.setMaximumSize( new Dimension(cityMap.getCityX(), cityMap.getCityY()));
//        panelMap.setSize();
        // TODO: place custom component creation code here
    }
}
