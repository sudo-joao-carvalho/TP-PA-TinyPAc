package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.Main;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.GameContextManager;


public class MainJFX extends Application {
    GameContextManager gameCManager;

    @Override
    public void init() throws Exception {
        super.init();

        gameCManager = Main.gameCManager;

    }

    @Override
    public void start(Stage stage) throws Exception {
        firstStage(stage,"PacMan");

        GameEngine gameEngine = new GameEngine();
        gameEngine.registerClient((g, t) -> {
            Platform.runLater(() -> {
                    gameCManager.evolve(g, t);
            });
        });

        gameEngine.start(350);

    }

    private void firstStage(Stage stage, String title) {
        RootPane root = new RootPane(gameCManager);
        Scene scene = new Scene(root,1920,1280);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.show();
    }
}
