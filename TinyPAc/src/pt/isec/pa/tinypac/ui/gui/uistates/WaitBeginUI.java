package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class WaitBeginUI extends BorderPane {
    GameContextManager gameCManager;

    Text waitText;
    public WaitBeginUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
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
    }


    private void update() {
        /*if (gameCManager.getState() != gameCManager.BEGIN) {
            this.setVisible(false);
            return;
        }*/

        /*if(gameCManager.getFsm() != null) this.setVisible(true);
        else this.setVisible(false);*/


    }
}

