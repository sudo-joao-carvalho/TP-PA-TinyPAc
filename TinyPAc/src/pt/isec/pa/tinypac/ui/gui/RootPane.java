package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;
import pt.isec.pa.tinypac.ui.gui.uistates.*;

public class RootPane extends BorderPane {

    GameContextManager gameCManager;

    public RootPane(GameContextManager gameCManager){
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews(){

        /*StackPane stackPane = new StackPane(
            new FirstMenuUI(gameCManager),
            new WaitBeginUI(gameCManager),
            new MoveUI(gameCManager),
            new VulnerableUI(gameCManager),
            new PauseUI(gameCManager),
            new EndLevelUI(gameCManager)
        );*/
        StackPane stackPane = new StackPane(new FirstMenuUI(gameCManager));

        this.setCenter(stackPane);
    }

    private void registerHandlers(){
        gameCManager.addPropertyChangeListener(evt -> update());
    }

    private void update(){

        if(gameCManager.getFsm() != null){
            EMobsState gameState = gameCManager.getState();

            // Remova todas as interfaces de usuário do StackPane
            StackPane stackPane = (StackPane) this.getCenter();
            stackPane.getChildren().clear();

            // Adicione a interface de usuário correspondente ao estado atual
            switch (gameState) {
                case WAIT_BEGIN:
                    var waitBeginUI = new WaitBeginUI(gameCManager);
                    stackPane.getChildren().add(waitBeginUI);
                    waitBeginUI.requestFocus();
                    break;
                case MOVE:
                    var moveUI = new MoveUI(gameCManager);
                    stackPane.getChildren().add(moveUI);
                    moveUI.requestFocus();
                    break;
                case PAUSE:
                    stackPane.getChildren().add(new PauseUI(gameCManager));
                    break;
                case VULNERABLE:
                    stackPane.getChildren().add(new VulnerableUI(gameCManager));
                    break;
                case END_LEVEL:
                    stackPane.getChildren().add(new EndLevelUI(gameCManager));
                    break;
                default:
                    // Caso de fallback caso um novo estado seja adicionado e não tenha sido tratado
                    //stackPane.getChildren().add(new FirstMenuUI(gameCManager));
                    break;
            }
        }

    }

}
