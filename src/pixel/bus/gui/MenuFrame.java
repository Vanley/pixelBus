package pixel.bus.gui;

import pixel.bus.model.CityLevel;
import pixel.bus.service.GameLoaderFactory;

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

    private static final GameLoaderFactory gameLoaderFactory = GameLoaderFactory.getInstance();

    public MenuFrame() {
        btnContinue.setVisible(gameLoaderFactory.hasInstanceInDB());

        initActionListeners();

        mainWindow.setFocusable(true);
        mainWindow.requestFocusInWindow();
        cardMenu.setVisible(true);
        cardLevelDetails.setVisible(false);
    }

    public static void goToMenuFrame(){
        gameLoaderFactory.unLoad();
        gameFrame.dispose();

        menuFrame.repaint();
        menuFrame.setVisible(true);
    }

    private void goToGameFrame(){
        gameFrame.setContentPane(new GameFrame(gameLoaderFactory).mainPanel);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setTitle("PIXEL BUS The Game");
        gameFrame.pack();
//        gameFrame.setLocationRelativeTo(null);
//        gameFrame.setMinimumSize(new Dimension(200, 220));
        centreWindow(gameFrame);
        gameFrame.setVisible(true);

        menuFrame.setVisible(false);
        btnContinue.setVisible(true);
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
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
                gameLoaderFactory.load();
                goToGameFrame();
            }
        });
        btnStartNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoaderFactory.load(CityLevel.LEVEL_ONE);
                goToGameFrame();
            }
        });
    }

}
