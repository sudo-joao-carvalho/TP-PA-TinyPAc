package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.states.WaitBeginState;

public class GameContext {

    Game game;

    IMobsState gameState;

    public GameContext(){
        game = new Game(1);
        this.gameState = new WaitBeginState(this, game);
    }

    public EMobsState getState(){return gameState.getState();} //foi dado override no MenuState para ele poder ir buscar o state a propria enumeraçao

    void changeState(IMobsState newState){this.gameState = newState;}

    public boolean move(){return gameState.move();}

    public boolean vulnerable(){return gameState.vulnerable();}

    public boolean endLevel(){return gameState.endLevel();}

    //getData
    public Game getGame(){return game;}
}
