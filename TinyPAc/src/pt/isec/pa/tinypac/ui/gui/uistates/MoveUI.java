package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.tinypac.model.GameContextManager;

public class MoveUI extends BorderPane {

    GameContextManager gameCManager;
    public MoveUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;
    }
}
