package pixel.bus.gui.engine;

import pixel.bus.gui.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pixel.bus.model.Station.queuePassengers;

/**
 * Created by vanley on 29/05/2017.
 */
public class GameEngineActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Game.tick++;
        queuePassengers();
        System.out.println("Hello Game World Timer");
    }
}