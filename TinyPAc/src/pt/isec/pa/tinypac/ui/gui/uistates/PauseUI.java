package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.tinypac.model.GameContextManager;

public class PauseUI extends BorderPane {

    GameContextManager gameCManager;
    public PauseUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;
    }
}
