package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.ui.gui.RootPane;

import java.util.ArrayList;


public class TopFiveUI extends BorderPane {

    GameContextManager gameCManager;
    Text text;
    Text player1, player2, player3, player4, player5;
    Button btnGoBack;
    public TopFiveUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //Font pixelFont = Font.loadFont(getClass().getResourceAsStream("images/pixel_emulator/Pixel_Emulator.otf"), 16);

        this.setStyle("-fx-background-color: black;");

        text = new Text("TOP 5");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 200));
        text.setFill(Color.WHITE);

        //player1 = new Text("1. " + gameCManager.getTopFive().getTop5().get(1));
        player1 = new Text();
        player1.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        player1.setFill(Color.WHITE);

        player2 = new Text();
        player2.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        player2.setFill(Color.WHITE);

        player3 = new Text();
        player3.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        player3.setFill(Color.WHITE);

        player4 = new Text();
        player4.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        player4.setFill(Color.WHITE);

        player5 = new Text();
        player5.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        player5.setFill(Color.WHITE);

        btnGoBack = new Button("Go Back");
        btnGoBack.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnGoBack.setPrefWidth(250);
        btnGoBack.setPrefHeight(80);

        VBox vBox = new VBox(text, player1, player2, player3, player4, player5, btnGoBack);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);

        this.setCenter(vBox);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });

        btnGoBack.setOnAction( event -> {
            RootPane rootPane = (RootPane) getScene().getRoot();
            rootPane.showFirstMenuUI();
        });
    }

    private void update() {
        if(gameCManager.getFsm() != null){
            if (gameCManager.getTopFiveArrayList() != null) {
                ArrayList<Integer> top5 = gameCManager.getTopFiveArrayList();

                if (!top5.isEmpty()) {
                    player1.setText((1) + ". " + top5.get(0));
                } else {
                    player1.setText("");
                }
                if (top5.size() >= 2) {
                    player2.setText((2) + ". " + top5.get(1));
                } else {
                    player2.setText("");
                }
                if (top5.size() >= 3) {
                    player3.setText((3) + ". " + top5.get(2));
                } else {
                    player3.setText("");
                }
                if (top5.size() >= 4) {
                    player4.setText((4) + ". " + top5.get(3));
                } else {
                    player4.setText("");
                }
                if (top5.size() >= 5) {
                    player5.setText((5) + ". " + top5.get(4));
                } else {
                    player5.setText("");
                }
            }
        }
    }

}
