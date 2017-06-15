package pixel.bus.gui;

import pixel.bus.model.CityLevel;
import pixel.bus.service.GameLoaderService;

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

    private GameLoaderService gameLoaderService = new GameLoaderService();

    public MenuFrame() {
        btnContinue.setVisible(gameLoaderService.hasInstanceInDB());

        initActionListeners();

        mainWindow.setFocusable(true);
        mainWindow.requestFocusInWindow();
        cardMenu.setVisible(true);
        cardLevelDetails.setVisible(false);
    }

    public static void goToMenuFrame(){

        gameFrame.dispose();

        menuFrame.repaint();
        menuFrame.setVisible(true);
    }

    private void goToGameFrame(){
        gameFrame.setContentPane(new GameFrame(gameLoaderService).mainPanel);
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
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }

            menuFrame.setContentPane(new MenuFrame().mainWindow);
            menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            menuFrame.setLocationRelativeTo(null);
            menuFrame.setTitle("PIXEL BUS");
            menuFrame.pack();
            menuFrame.setVisible(true);
        }
    }

    private void createUIComponents() {
        animateBusPanel = new MenuBusPanel();
    }

    private void initActionListeners(){
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispatchEvent(new WindowEvent(menuFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoaderService.load();
                goToGameFrame();
            }
        });
        btnStartNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoaderService.load(CityLevel.LEVEL_ONE);
                goToGameFrame();
            }
        });
    }

}
