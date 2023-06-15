package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class WaitBeginUI extends BorderPane {
    GameContextManager gameCManager;

    Text waitText;

    public WaitBeginUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();

        requestFocus();
    }

    private void createViews() {
        this.setStyle("-fx-background-color: black;");

        waitText = new Text("Pressione uma tecla para começar");
        waitText.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        waitText.setFill(Color.WHITE);

        VBox vbox = new VBox(waitText);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter(vbox);
    }

    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });
        setOnKeyPressed(event -> {
            gameCManager.evolve();
            //gameCManager.retrieveKey(KeyCode.ENTER);
        });
    }


    private void update() {
        //this.requestFocus();
        this.setVisible(gameCManager.getState() == EMobsState.WAIT_BEGIN);

    }
}

