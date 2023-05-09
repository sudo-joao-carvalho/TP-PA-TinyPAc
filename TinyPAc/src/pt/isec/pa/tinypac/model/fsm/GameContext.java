package pt.isec.pa.tinypac.model.fsm;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.states.MoveState;
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

    public boolean evolve(){return gameState.evolve();}

    public boolean pause(){return gameState.pause();}

    /*@Override
    public void evolve(IGameEngine gameEngine, long currentTime){
        if(game.getLevel() == null)
            return ;
        game.getLevel().evolve();
    }*/

    public Game getGame(){return this.game;}

    public void changePacmanDirection(KeyType key) {
        IMobsState currentState = this.gameState;
        if (currentState instanceof MoveState moveState) {
            moveState.changePacmanDirection(key);
        }
    }
}
