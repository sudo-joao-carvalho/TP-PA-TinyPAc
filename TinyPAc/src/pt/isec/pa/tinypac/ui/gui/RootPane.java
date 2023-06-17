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

        StackPane stackPane = (StackPane) this.getCenter();

        if(gameCManager.getFsm() != null){
            EMobsState gameState = gameCManager.getState();

            // Remove todas as interfaces de usuário do StackPane
            //StackPane stackPane = (StackPane) this.getCenter();
            stackPane.getChildren().clear();

            // Adicione a interface correspondente ao estado atual
            switch (gameState) {
                case WAIT_BEGIN:
                    var waitBeginUI = new WaitBeginUI(gameCManager); //fazer estes news todos logo dentro do stacPane
                    stackPane.getChildren().add(waitBeginUI);
                    waitBeginUI.requestFocus();
                    break;
                case MOVE:
                    var moveUI = new MoveUI(gameCManager);
                    stackPane.getChildren().add(moveUI);
                    moveUI.requestFocus();
                    break;
                case PAUSE:
                    var pauseUI = new PauseUI(gameCManager);
                    stackPane.getChildren().add(pauseUI);
                    pauseUI.requestFocus();
                    break;
                case VULNERABLE:
                    var vulnerableUI = new VulnerableUI(gameCManager);
                    stackPane.getChildren().add(vulnerableUI);
                    vulnerableUI.requestFocus();
                    break;
                case END_LEVEL:
                    var endLevelUI = new EndLevelUI(gameCManager);
                    stackPane.getChildren().add(endLevelUI);
                    endLevelUI.requestFocus();
                    break;
                default:
                    break;
            }
        }

    }

    public void showFirstMenuUI() {
        StackPane stackPane = (StackPane) this.getCenter();
        stackPane.getChildren().clear();

        FirstMenuUI firstMenuUI = new FirstMenuUI(gameCManager);
        stackPane.getChildren().add(firstMenuUI);
        firstMenuUI.requestFocus();
    }

}
