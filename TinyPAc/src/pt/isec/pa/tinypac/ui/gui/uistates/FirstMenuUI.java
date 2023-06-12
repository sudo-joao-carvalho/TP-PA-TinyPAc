package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        this.setStyle("-fx-background-color: black;");

        btnStartGame = new Button("Start");
        btnStartGame.setStyle("-fx-background-color: black; -fx-padding: 10px; -fx-border-color: red; -fx-border-radius: 5px; -fx-text-fill: white; -fx-font-family: Arial;");
        btnStartGame.setMinWidth(100);
        btnTopFive = new Button("Top 5");
        btnTopFive.setStyle("-fx-background-color: black; -fx-padding: 10px; -fx-border-color: green; -fx-border-radius: 5px; -fx-text-fill: white; -fx-font-family: Arial;");
        btnTopFive.setMinWidth(100);
        btnExit  = new Button("Exit");
        btnExit.setStyle("-fx-background-color: black; -fx-padding: 10px; -fx-border-color: blue; -fx-border-radius: 5px; -fx-text-fill: white; -fx-font-family: Arial;");
        btnExit.setMinWidth(100);

        Image image = ImageManager.getImage("pacman-logo.png");
        ImageView imageView = new ImageView(image);

        VBox vBox = new VBox(imageView, btnStartGame, btnTopFive, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

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
        /*if (gameCManager.getState() != gameCManager.BEGIN) {
            this.setVisible(false);
            return;
        }*/
        this.setVisible(true);

    }
}
