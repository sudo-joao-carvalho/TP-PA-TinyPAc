package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class MoveUI extends BorderPane {

    GameContextManager gameCManager;
    Button btnStartGame;
    public MoveUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        this.setStyle("-fx-background-color: black;");

        btnStartGame = new Button("Start");
        btnStartGame.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnStartGame.setPrefWidth(250);
        btnStartGame.setPrefHeight(80);

        VBox vBox = new VBox(btnStartGame);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        this.setCenter(vBox);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });

    }


    private void update() {
        /*if (gameCManager.getState() != gameCManager.BEGIN) {
            this.setVisible(false);
            return;
        }*/

        if(gameCManager.getState() == EMobsState.MOVE) this.setVisible(true);
        else this.setVisible(false);

    }
}
