package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.tinypac.model.GameContextManager;

public class VulnerableUI extends BorderPane {

    GameContextManager gameCManager;
    public VulnerableUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;
    }
}
