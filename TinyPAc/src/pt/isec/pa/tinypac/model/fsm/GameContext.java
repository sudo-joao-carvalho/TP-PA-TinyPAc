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

    public GameContext(){
        gameData        = new GameData(1);
        this.gameState  = new WaitBeginState(this, gameData);
    }

    public void setGameData(GameData gameData){this.gameData = gameData;}
    public GameData getGameData(){return this.gameData;}

    public EMobsState getState(){return gameState.getState();} //foi dado override no MenuState para ele poder ir buscar o state a propria enumeraçao
    void changeState(IMobsState newState){this.gameState = newState;}

    //public boolean start(){return gameState.start();}
    //public boolean save(){};

    //public boolean load(){};

    public boolean evolve(){ return gameState.evolve(); }//evolve de mudança de estado

    public boolean pause(){return gameState.pause();}
    public boolean unpause(){return gameState.unpause();}

    //@Override
    public void evolve(IGameEngine gameEngine, long currentTime){ //evolve de mudança de estado da engine

        if(this.getState() == EMobsState.MOVE || this.getState() == EMobsState.VULNERABLE){ // isto assegura que so se comecem a mover assim que o estado for MOVE e nao logo quando ainda esta no waitbegin
            if(gameData == null)
                return ;
            gameData.evolve(currentKeyType);
        }
    }

    //public GameData getLevel(){return game.getLevel();}
    public char[][] getMap(){return gameData.getMaze();}

    public int getScore(){return gameData.getTinyPac().getScore();}
    public int getLifes(){return gameData.getTinyPac().getLifes();}
    public int getLevelNumber(){return gameData.getLevelNumber();}
    public int getNumOfFood(){return gameData.getNumOfFood();}

    public TopFive getTop5(){return gameData.getTop5();}

    public void setTop5(TopFive top5){
        gameData.setTop5(top5);
    }
    public int getTopFive(int position){
        TopFive auxTop5 = gameData.getTop5();

        for(int i: auxTop5.getTop5()){
            if(i == position)
                return i;
        }

        return 0;
    }

    public ArrayList<Integer> getTopFiveArrayList(){return gameData.getTop5().getTop5();}
    public IMazeElement[][] getMazeWithElements(){return gameData.getMazeWithElements();}
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
