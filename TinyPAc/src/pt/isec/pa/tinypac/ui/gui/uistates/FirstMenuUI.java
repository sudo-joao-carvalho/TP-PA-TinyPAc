package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.ui.gui.RootPane;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

import java.io.File;

public class FirstMenuUI extends BorderPane {
    GameContextManager gameCManager;

    Button btnStartGame, btnTopFive, btnExit;
    public FirstMenuUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();

        this.requestFocus();
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

        GridPane footerGrid = new GridPane();
        footerGrid.setStyle("-fx-background-color: black;");
        footerGrid.setAlignment(Pos.CENTER);
        footerGrid.setHgap(100); // Espaçamento horizontal entre colunas
        footerGrid.setVgap(100); // Espaçamento vertical entre linhas

        Label text1 = new Label("DEIS-ISEC-IPC\n\nLEI\n\nProgramação Avançada\n\n2022/2023");
        text1.setStyle("-fx-text-fill: white");
        Label text2 = new Label("TRABALHO ACADÉMICO\n\nJoão Carvalho 2019131769");
        text2.setStyle("-fx-text-fill: white");
        ImageView footerImage = new ImageView(ImageManager.getImage("isec-logo.png"));
        footerImage.setFitWidth(180); // Defina a largura desejada da imagem
        footerImage.setFitHeight(100); // Defina a altura desejada da imagem

        // Adicione os elementos ao GridPane
        footerGrid.add(text1, 0, 0);
        footerGrid.add(footerImage, 1, 0);
        footerGrid.add(text2, 2, 0);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(150); // Defina a altura desejada

        footerGrid.getRowConstraints().add(rowConstraints);

        this.setCenter(vBox);
        this.setBottom(footerGrid);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });
        btnStartGame.setOnAction( event -> {
            File saveFile = new File("files/save.dat");

            if(saveFile.exists()){
                RootPane rootPane = (RootPane) getScene().getRoot();
                rootPane.showAskGameSavedUI();
            }else{
                gameCManager.start();
            }
        });
        btnTopFive.setOnAction( event -> {
            File saveFile = new File("files/topFive.dat");

            if(saveFile.exists()){
                //System.out.println(gameCManager.loadTop5());
                System.out.println("existe");
                gameCManager.loadTop5();
                RootPane rootPane = (RootPane) getScene().getRoot();
                rootPane.showTop5();
            }
        });
        btnExit.setOnAction( event -> {
            RootPane rootPane = (RootPane) getScene().getRoot();
            rootPane.showConfirmation();
        });
    }


    private void update() {

        if(gameCManager.getFsm() == null) this.setVisible(true);
        else this.setVisible(false);

    }
}
