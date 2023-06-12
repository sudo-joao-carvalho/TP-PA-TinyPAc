package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.tinypac.model.GameContextManager;

public class WaitBeginUI extends BorderPane {
    GameContextManager gameCManager;

    Button btnStartGame;
    public WaitBeginUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnStartGame = new Button("WaitBeginUI");
        btnStartGame.setMinWidth(100);
        HBox hBox = new HBox(btnStartGame);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        this.setCenter(hBox);
    }

    private void registerHandlers() {
    }


    private void update() {
        /*if (gameCManager.getState() != gameCManager.BEGIN) {
            this.setVisible(false);
            return;
        }*/
        this.setVisible(true);

    }
}

