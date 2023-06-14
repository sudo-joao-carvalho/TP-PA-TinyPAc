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

public class PauseUI extends BorderPane {

    GameContextManager gameCManager;
    Button oi;
    public PauseUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //Font pixelFont = Font.loadFont(getClass().getResourceAsStream("images/pixel_emulator/Pixel_Emulator.otf"), 16);

        this.setStyle("-fx-background-color: black;");

        oi = new Button("oi");
        oi.setPrefWidth(250);
        oi.setPrefHeight(80);

        this.setCenter(oi);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });
        oi.setOnAction( event -> {
            gameCManager.unpause();
        });

    }


    private void update() {
        if (gameCManager.getState() != EMobsState.PAUSE) {
            this.setVisible(false);
            return;
        }

        /*if(gameCManager.getFsm() == null) this.setVisible(true);
        else this.setVisible(false);*/

    }
}
