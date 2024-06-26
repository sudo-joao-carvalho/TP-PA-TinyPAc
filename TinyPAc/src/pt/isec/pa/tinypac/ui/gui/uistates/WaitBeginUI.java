package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.EMobsState;

public class WaitBeginUI extends BorderPane {
    GameContextManager gameCManager;

    Text waitText;

    public WaitBeginUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();

        this.requestFocus();
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
        });
    }


    private void update() {
        //this.requestFocus();
        if(gameCManager.getFsm() != null){
            this.setVisible(gameCManager.getState() == EMobsState.WAIT_BEGIN);
        }else this.setVisible(false);


    }
}

