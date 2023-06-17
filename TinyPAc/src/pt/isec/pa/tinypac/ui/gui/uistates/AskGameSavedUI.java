package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class AskGameSavedUI extends BorderPane {

    GameContextManager gameCManager;

    Text text;
    Button btnYes, btnNo;
    public AskGameSavedUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //Font pixelFont = Font.loadFont(getClass().getResourceAsStream("images/pixel_emulator/Pixel_Emulator.otf"), 16);

        this.setStyle("-fx-background-color: black;");

        text = new Text("Existe um jogo guardado. Deseja retomar?");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        text.setFill(Color.WHITE);

        btnYes = new Button("Yes");
        btnYes.setStyle("-fx-background-color: black; -fx-border-color: green; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnYes.setPrefWidth(250);
        btnYes.setPrefHeight(80);

        btnNo = new Button("No");
        btnNo.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnNo.setPrefWidth(250);
        btnNo.setPrefHeight(80);

        VBox vBox = new VBox(text, btnYes, btnNo);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        this.setCenter(vBox);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });
        btnYes.setOnAction( event -> {
            gameCManager.load();
            //gameCManager.evolve();
        });
        btnNo.setOnAction( event -> {
            Platform.exit();
        });
    }


    private void update() {

        if(gameCManager.getFsm() == null) this.setVisible(true);
        else this.setVisible(false);

    }
}

