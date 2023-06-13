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
    //Button botao;
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

        //botao = new Button("COCO");

        VBox vbox = new VBox(waitText/*, botao*/);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter(vbox);
    }

    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                gameCManager.evolve();
                // Aqui você deve trocar para a próxima cena ou atualizar a interface conforme necessário
            }
        });
        /*botao.setOnAction(event -> {
            gameCManager.evolve();
        });*/

    }


    private void update() {

        this.setVisible(gameCManager.getState() == EMobsState.WAIT_BEGIN);

    }
}

