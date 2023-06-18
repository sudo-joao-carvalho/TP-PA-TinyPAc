package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;
import pt.isec.pa.tinypac.ui.gui.uistates.*;

public class RootPane extends BorderPane {

    GameContextManager gameCManager;

    private StackPane stackPane;
    private FirstMenuUI firstMenuUI;
    private WaitBeginUI waitBeginUI;
    private MoveUI moveUI;
    private VulnerableUI vulnerableUI;
    private PauseUI pauseUI;
    private EndLevelUI endLevelUI;
    public RootPane(GameContextManager gameCManager){
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews(){

        firstMenuUI = new FirstMenuUI(gameCManager);
        /*waitBeginUI = new WaitBeginUI(gameCManager);
        moveUI = new MoveUI(gameCManager);
        vulnerableUI = new VulnerableUI(gameCManager);
        pauseUI = new PauseUI(gameCManager);
        endLevelUI = new EndLevelUI(gameCManager);*/

        //this.stackPane = new StackPane();


        this.stackPane = new StackPane(firstMenuUI);
        //stackPane.getChildren().addAll(firstMenuUI, waitBeginUI, moveUI, vulnerableUI, pauseUI,endLevelUI);

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
                    waitBeginUI = new WaitBeginUI(gameCManager);
                    stackPane.getChildren().add(waitBeginUI);
                    waitBeginUI.requestFocus();
                    break;
                case MOVE:
                    moveUI = new MoveUI(gameCManager);
                    stackPane.getChildren().add(moveUI);
                    moveUI.requestFocus();
                    break;
                case PAUSE:
                    pauseUI = new PauseUI(gameCManager);
                    stackPane.getChildren().add(pauseUI);
                    pauseUI.requestFocus();
                    break;
                case VULNERABLE:
                    vulnerableUI = new VulnerableUI(gameCManager);
                    stackPane.getChildren().add(vulnerableUI);
                    vulnerableUI.requestFocus();
                    break;
                case END_LEVEL:
                    endLevelUI = new EndLevelUI(gameCManager);
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

    public void showAskGameSavedUI(){
        StackPane stackPane = (StackPane) this.getCenter();
        stackPane.getChildren().clear();

        AskGameSavedUI askGameSavedUI = new AskGameSavedUI(gameCManager);
        stackPane.getChildren().add(askGameSavedUI);
        askGameSavedUI.requestFocus();
    }

    public void showConfirmation(){
        StackPane stackPane = (StackPane) this.getCenter();
        stackPane.getChildren().clear();

        ShowConfirmationUI showConfirmationUI = new ShowConfirmationUI(gameCManager);
        stackPane.getChildren().add(showConfirmationUI);
        showConfirmationUI.requestFocus();
    }

    public void showTop5(){
        StackPane stackPane = (StackPane) this.getCenter();
        stackPane.getChildren().clear();

        TopFiveUI showTop5 = new TopFiveUI(gameCManager);
        stackPane.getChildren().add(showTop5);
        showTop5.requestFocus();
    }

}
