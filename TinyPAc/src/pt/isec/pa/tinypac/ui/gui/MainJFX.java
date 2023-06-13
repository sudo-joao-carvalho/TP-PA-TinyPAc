package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.Main;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.EMobsState;

public class MainJFX extends Application {
    //GameBWManager gameBWManager;
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
        // Registre o MainJFX como cliente da GameEngine
        gameEngine.registerClient((g, t) -> {
            // Atualize o estado do jogo no thread da aplicação do JavaFX
            Platform.runLater(() -> {
                // Chame o método evolve do GameContextManager
                if(gameCManager.getFsm() != null)
                    gameCManager.evolve(g, t);
            });
        });

        // Inicie a GameEngine
        gameEngine.start(350);

        //gameEngine.waitForTheEnd();

    }

    private void firstStage(Stage stage, String title) {
        RootPane root = new RootPane(gameCManager);
        //Scene scene = new Scene(root,700,400);
        Scene scene = new Scene(root,1920,1280);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.show();
    }
}
