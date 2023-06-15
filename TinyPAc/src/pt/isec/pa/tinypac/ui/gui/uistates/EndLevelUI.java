package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.EMobsState;

public class EndLevelUI extends BorderPane {

    GameContextManager gameCManager;
    Button oi;
    public EndLevelUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //Font pixelFont = Font.loadFont(getClass().getResourceAsStream("images/pixel_emulator/Pixel_Emulator.otf"), 16);

        this.setStyle("-fx-background-color: black;");

        oi = new Button("oi");
        oi.setPrefWidth(350);
        oi.setPrefHeight(100);

        this.setCenter(oi);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });
        oi.setOnAction( event -> {
            System.out.println("oi");
        });


    }


    private void update() {
        if (gameCManager.getState() != EMobsState.END_LEVEL) {
            this.setVisible(false);
        }

    }
}

