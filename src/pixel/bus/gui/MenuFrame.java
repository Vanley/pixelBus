package pixel.bus.gui;

import pixel.bus.gui.engine.GameEngine;
import pixel.bus.model.Game;
import pixel.bus.model.CityLevels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by vanley on 21/05/2017.
 */
public class MenuFrame {
    private static JFrame menuFrame = new JFrame("PIXEL BUS");
    private static JFrame gameFrame = new JFrame("PIXEL BUS The Game");
    private JPanel mainWindow;
    private JPanel cardMenu;
    private JPanel cardLevelDetails;
    private JPanel animateBusPanel;
    private JButton btnContinue;
    private JButton btnStartNew;
    private JButton btnExit;

    public MenuFrame() {


        mainWindow.setFocusable(true);
        mainWindow.requestFocusInWindow();
        cardMenu.setVisible(true);
        cardLevelDetails.setVisible(false);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispatchEvent(new WindowEvent(menuFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //TODO LOAD GAME FROM DB
                ////todo dao station, passanger, gaame, vehicle


                goToGame(CityLevels.cityLevel1);
            }
        });
        btnStartNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                goToGame(CityLevels.cityLevel1);
            }
        });
    }

    public static void goToMenu(){
        gameFrame.dispose();
        menuFrame.setVisible(true);
    }

    public void goToGame(String cityLevel){
        Game game = new Game(cityLevel);

        gameFrame.setContentPane(new GameFrame(game).mainPanel);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setTitle("PIXEL BUS The Game");
        gameFrame.pack();
        gameFrame.setMinimumSize(new Dimension(200, 220));
        gameFrame.setVisible(true);

        menuFrame.setVisible(false);

        GameEngine gameEngine = new GameEngine(game);

        EventQueue.invokeLater(gameEngine);
        gameEngine.unPause();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new MainFrame());

    }

    static class MainFrame implements Runnable {
        @Override
        public void run() {
            menuFrame.setContentPane(new MenuFrame().mainWindow);
            menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            menuFrame.setLocationRelativeTo(null);
            menuFrame.setTitle("PIXEL BUS");
            menuFrame.pack();
            menuFrame.setMinimumSize(new Dimension(200, 220));
            menuFrame.setVisible(true);
        }
    }

    private void createUIComponents() {
        animateBusPanel = new MenuBusPanel();
        animateBusPanel.setMinimumSize(new Dimension(200, 220));
    }

}
