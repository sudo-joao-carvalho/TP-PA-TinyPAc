package pt.isec.pa.tinypac.model.fsm;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.fsm.states.MoveState;
import pt.isec.pa.tinypac.model.fsm.states.VulnerableState;
import pt.isec.pa.tinypac.model.fsm.states.WaitBeginState;

public class GameContext {
    Game game;

    IMobsState gameState;

    private KeyType currentKeyType = KeyType.ArrowDown;;//KeyType.ArrowRight;

    public GameContext(){
        game = new Game(1);
        this.gameState = new WaitBeginState(this, game);
    }

    public EMobsState getState(){return gameState.getState();} //foi dado override no MenuState para ele poder ir buscar o state a propria enumeraçao
    void changeState(IMobsState newState){this.gameState = newState;}

    public boolean evolve(){return gameState.evolve();} //evolve de mudança de estado

    public boolean pause(){return gameState.pause();}

    //@Override
    public void evolve(IGameEngine gameEngine, long currentTime){ //evolve de mudança de estado da engine

        if(this.getState() == EMobsState.MOVE || this.getState() == EMobsState.VULNERABLE){ // isto assegura que so se comecem a mover assim que o estado for MOVE
            if(game.getLevel() == null)
                return ;

            game.getLevel().evolve(currentKeyType);
        }
    }

    public Level getLevel(){return game.getLevel();}
    public char[][] getMap(){return game.getLevel().getMaze();}

    /*public void changePacmanDirection(KeyType key) {
        IMobsState currentState = this.gameState;
        if (currentState instanceof MoveState moveState) {
            moveState.changePacmanDirection(key);
        }

        if (currentState instanceof VulnerableState vulnerableState) {
            vulnerableState.changePacmanDirection(key);
        }

    }*/

    public void retrieveKey(KeyType key){
        currentKeyType = key;
    }
}
