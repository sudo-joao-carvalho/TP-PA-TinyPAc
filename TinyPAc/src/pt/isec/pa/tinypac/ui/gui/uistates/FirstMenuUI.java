package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class FirstMenuUI extends BorderPane {
    GameContextManager gameCManager;

    Button btnStartGame, btnTopFive, btnExit;
    public FirstMenuUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //Font pixelFont = Font.loadFont(getClass().getResourceAsStream("images/pixel_emulator/Pixel_Emulator.otf"), 16);

        this.setStyle("-fx-background-color: black;");

        btnStartGame = new Button("Start");
        btnStartGame.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnStartGame.setPrefWidth(250);
        btnStartGame.setPrefHeight(80);

        btnTopFive = new Button("Top 5");
        btnTopFive.setStyle("-fx-background-color: black; -fx-border-color: cyan; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnTopFive.setPrefWidth(250);
        btnTopFive.setPrefHeight(80);

        btnExit  = new Button("Exit");
        btnExit.setStyle("-fx-background-color: black; -fx-border-color: orange; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnExit.setPrefWidth(250);
        btnExit.setPrefHeight(80);

        Image image = ImageManager.getImage("pacman-logo.png");
        ImageView imageView = new ImageView(image);

        VBox vBox = new VBox(imageView, btnStartGame, btnTopFive, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        this.setCenter(vBox);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });
        btnStartGame.setOnAction( event -> {
            gameCManager.start();
        });
        btnExit.setOnAction( event -> {
            Platform.exit();
        });
    }


    private void update() {

        if(gameCManager.getFsm() == null) this.setVisible(true);
        else this.setVisible(false);

    }
}
