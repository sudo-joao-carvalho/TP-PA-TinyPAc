package pt.isec.pa.tinypac.model.fsm;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
//import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.data.TopFive;
import pt.isec.pa.tinypac.model.fsm.states.WaitBeginState;
import pt.isec.pa.tinypac.model.memento.IMemento;
import pt.isec.pa.tinypac.model.memento.IOriginator;
import pt.isec.pa.tinypac.model.memento.Memento;

import java.io.Serializable;
import java.util.ArrayList;

public class GameContext implements Serializable, IOriginator {
    GameData gameData;

    IMobsState gameState;

    private KeyCode currentKeyType = null;//KeyCode.KP_DOWN;

    /**
     *  Constructor
     */
    public GameContext(){
        gameData        = new GameData(1);
        this.gameState  = new WaitBeginState(this, gameData);
    }

    /**
     *  Sets GameData
     */
    public void setGameData(GameData gameData){this.gameData = gameData;}
    /**
     *  Gets GameData
     */
    public GameData getGameData(){return this.gameData;}

    /**
     *  Returns Current State
     */
    public EMobsState getState(){return gameState.getState();}
    /**
     *  Changes Current State
     */
    void changeState(IMobsState newState){this.gameState = newState;}

    /**
     *  Changes to evovle state
     */
    public boolean evolve(){ return gameState.evolve(); }//evolve de mudança de estado

    /**
     *  Changes to Pause State
     */
    public boolean pause(){return gameState.pause();}

    /**
     *  Changes back to Move State
     */
    public boolean unpause(){return gameState.unpause();}


    /**
     *  GameEngine evolve that allows the gameEngine to make the Elements move
     */
    //@Override
    public void evolve(IGameEngine gameEngine, long currentTime){ //evolve de mudança de estado da engine

        if(this.getState() == EMobsState.MOVE || this.getState() == EMobsState.VULNERABLE){ // isto assegura que so se comecem a mover assim que o estado for MOVE e nao logo quando ainda esta no waitbegin
            if(gameData == null)
                return ;
            gameData.evolve(currentKeyType);
        }
    }

    /**
     *  Returns Score variable from TinyPac
     */
    public int getScore(){return gameData.getTinyPac().getScore();}

    /**
     *  Returns Lifes variable from Pacman
     */
    public int getLifes(){return gameData.getTinyPac().getLifes();}

    /**
     *  Returns Level Number
     */
    public int getLevelNumber(){return gameData.getLevelNumber();}

    /**
     *  Returns NumOfFood variable
     */
    public int getNumOfFood(){return gameData.getNumOfFood();}

    /**
     *  Returns Top5
     */
    public TopFive getTop5(){return gameData.getTop5();}

    /**
     *  Sets Top5
     */
    public void setTop5(TopFive top5){
        gameData.setTop5(top5);
    }

    /**
     *  Returns Top5 ArrayList
     */
    public ArrayList<Integer> getTopFiveArrayList(){return gameData.getTop5().getTop5();}

    /**
     *  Returns the entire board with IMazeElements
     */
    public IMazeElement[][] getMazeWithElements(){return gameData.getMazeWithElements();}

    /**
     *  Retrieves the key that the user pressed
     */
    public void retrieveKey(KeyCode key){
        currentKeyType = key;
    }

    @Override
    public IMemento save(){return new Memento(this);}

    @Override
    public void restore(IMemento memento){
        /*Object obj = memento.getSnapshot();
        if(obj instanceof GameContext g){
            gameData = g.gameData;
            gameState = g.gameState;
            gameData.setTop5(g.gameData.getTop5());
        }*/
    }
}
