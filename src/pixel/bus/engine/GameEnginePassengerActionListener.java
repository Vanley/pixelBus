package pixel.bus.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pixel.bus.model.Station.queuePassengers;

/**
 * Created by vanley on 02/06/2017.
 */
public class GameEnginePassengerActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        queuePassengers();
    }
}
