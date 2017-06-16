package pixel.bus.gui;

import pixel.bus.model.GameSpeedEnum;
import pixel.bus.service.GameEngineService;
import pixel.bus.service.GameLoaderFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by vanley on 16/06/2017.
 */
public class ChangeSpeedAction extends AbstractAction {

    public ChangeSpeedAction(GameSpeedEnum text, GameSpeedEnum selected) {
        super(text.getSymbol());
        super.putValue(SELECTED_KEY, text.equals(selected));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("key "+ GameSpeedEnum.valueOfAttribute(event.getActionCommand()) +" "+event );
        GameLoaderFactory.getInstance()
                .getInstance(GameEngineService.class)
                .speed(GameSpeedEnum.valueOfAttribute(event.getActionCommand()));
    }

}

