package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.ui.gui.RootPane;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class EndLevelUI extends BorderPane {

    GameContextManager gameCManager;

    Button btnBackToMenu;
    public EndLevelUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();

        this.requestFocus();
    }

    private void createViews() {
        //Font pixelFont = Font.loadFont(getClass().getResourceAsStream("images/pixel_emulator/Pixel_Emulator.otf"), 16);

        this.setStyle("-fx-background-color: black;");

        btnBackToMenu = new Button("Menu");
        btnBackToMenu.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");

        Image image = ImageManager.getImage("gameover.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        VBox vBox = new VBox(imageView, btnBackToMenu);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        this.setCenter(vBox);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });

        btnBackToMenu.setOnAction(event -> {
            gameCManager.saveTop5();
            gameCManager.setFsmNull();

            RootPane rootPane = (RootPane) getScene().getRoot();
            rootPane.showFirstMenuUI();
        });
    }


    private void update() {

        if(gameCManager.getFsm() != null){
            if (gameCManager.getState() == EMobsState.END_LEVEL) {
                this.setVisible(true);
            }else this.setVisible(false);
        }else this.setVisible(false);


    }
}

