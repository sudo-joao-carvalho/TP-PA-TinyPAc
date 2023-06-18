package pt.isec.pa.tinypac.model.fsm;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
//import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.fsm.states.WaitBeginState;
import pt.isec.pa.tinypac.model.memento.IMemento;
import pt.isec.pa.tinypac.model.memento.IOriginator;
import pt.isec.pa.tinypac.model.memento.Memento;

import java.io.Serializable;

public class GameContext implements Serializable, IOriginator {
    GameData gameData;

    IMobsState gameState;

    private KeyCode currentKeyType = null;//KeyCode.KP_DOWN;;//KeyType.ArrowRight;

    public GameContext(){
        //game            = new Game(1);
        gameData        = new GameData(1);
        this.gameState  = new WaitBeginState(this, gameData);
    }

    public void setGameData(GameData gameData){this.gameData = gameData;}

    public EMobsState getState(){return gameState.getState();} //foi dado override no MenuState para ele poder ir buscar o state a propria enumeraçao
    void changeState(IMobsState newState){this.gameState = newState;}

    //public boolean start(){return gameState.start();}
    //public boolean save(){};

    //public boolean load(){};

    public boolean evolve(){
        System.out.println("ola2");
        return gameState.evolve();
    }//evolve de mudança de estado

    public boolean pause(){return gameState.pause();}
    public boolean unpause(){return gameState.unpause();}

    //@Override
    public void evolve(IGameEngine gameEngine, long currentTime){ //evolve de mudança de estado da engine

        if(this.getState() == EMobsState.MOVE || this.getState() == EMobsState.VULNERABLE){ // isto assegura que so se comecem a mover assim que o estado for MOVE e nao logo quando ainda esta no waitbegin
            if(/*game.getLevel()*/gameData == null)
                return ;

            //game.getLevel().evolve(currentKeyType);
            gameData.evolve(currentKeyType);
        }

    }

    //public GameData getLevel(){return game.getLevel();}
    public char[][] getMap(){/*return game.getLevel().getMaze();*/return gameData.getMaze();}

    public int getScore(){return gameData.getTinyPac().getScore();}
    public int getLifes(){return gameData.getTinyPac().getLifes();}
    public int getLevelNumber(){return gameData.getLevelNumber();}
    public IMazeElement[][] getMazeWithElements(){/*return game.getLevel().getMazeWithElements();*/return gameData.getMazeWithElements();}
    public void retrieveKey(KeyCode key){
        currentKeyType = key;
    }

    @Override
    public IMemento save(){return new Memento(this);}

    @Override
    public void restore(IMemento memento){
        Object obj = memento.getSnapshot();
        if(obj instanceof GameContext g){
            gameData = g.gameData;
            gameState = g.gameState;
        }
    }
}
