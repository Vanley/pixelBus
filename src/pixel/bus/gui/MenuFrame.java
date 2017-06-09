package pixel.bus.gui;

import pixel.bus.dao.DaoFactory;
import pixel.bus.dao.IGameDao;
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
        IGameDao gameDB = DaoFactory.getInstance(IGameDao.class);
        btnContinue.setVisible(gameDB.isSaved());

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

                Game game = gameDB.read();
                goToGame(game);
            }
        });
        btnStartNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Game game = new Game(CityLevels.cityLevel1);
                goToGame(game);
            }
        });

        mainWindow.setFocusable(true);
        mainWindow.requestFocusInWindow();
        cardMenu.setVisible(true);
        cardLevelDetails.setVisible(false);
    }

    public void goToMenu(){
        gameFrame.dispose();

        menuFrame.repaint();
        menuFrame.setVisible(true);
    }

    public void goToGame(Game game){
        gameFrame.setContentPane(new GameFrame(game).mainPanel);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setTitle("PIXEL BUS The Game");
        gameFrame.pack();
        gameFrame.setMinimumSize(new Dimension(200, 220));
        gameFrame.setVisible(true);

        menuFrame.setVisible(false);
        btnContinue.setVisible(true);
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
