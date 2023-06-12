package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.Main;
import pt.isec.pa.tinypac.model.GameContextManager;

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
        //firstStage(new Stage(),"PacMan");
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
