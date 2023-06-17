package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class PauseUI extends BorderPane {

    GameContextManager gameCManager;
    Button btnResume, btnSaveGame, btnExit;
    public PauseUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //Font pixelFont = Font.loadFont(getClass().getResourceAsStream("images/pixel_emulator/Pixel_Emulator.otf"), 16);

        this.setStyle("-fx-background-color: black;");

        btnResume = new Button("Resume");
        btnResume.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnResume.setPrefWidth(350);
        btnResume.setPrefHeight(100);

        btnSaveGame = new Button("Save Game");
        btnSaveGame.setStyle("-fx-background-color: black; -fx-border-color: cyan; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnSaveGame.setPrefWidth(350);
        btnSaveGame.setPrefHeight(100);

        btnExit  = new Button("Exit");
        btnExit.setStyle("-fx-background-color: black; -fx-border-color: orange; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnExit.setPrefWidth(350);
        btnExit.setPrefHeight(100);

        VBox vBox = new VBox(/*pauseLabel, */btnResume, btnSaveGame, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        this.setCenter(vBox);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });
        setOnKeyPressed( event -> {
            if(event.getCode() == KeyCode.ESCAPE){
                gameCManager.unpause();
            }
        });

        btnResume.setOnAction( event -> {
            gameCManager.unpause();
        });

        btnSaveGame.setOnAction( event -> {
            gameCManager.save();
            System.out.println("jogo guardado");
        });

        btnExit.setOnAction( event -> {
            Platform.exit();
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
