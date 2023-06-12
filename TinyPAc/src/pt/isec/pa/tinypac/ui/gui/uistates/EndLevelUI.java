package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.tinypac.model.GameContextManager;

public class EndLevelUI extends BorderPane {

    GameContextManager gameCManager;
    public EndLevelUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;
    }
}
