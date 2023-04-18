package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.states.MenuState;

public class GameContext {

    Game game;

    IGameState gameState;

    public GameContext(){
        game = new Game("Level101.txt");
        this.gameState = new MenuState(this, game);
    }

    public EGameState getState(){return gameState.getState();} //foi dado override no MenuState para ele poder ir buscar o state a propria enumeraçao

    void changeState(IGameState newState){this.gameState = newState;}

    public String start(){return gameState.start();} //faze executar o state

    public String pause(){return gameState.pause();}

    public String resume(){return gameState.resume();}

    public String loadNextLevel(){return gameState.loadNextLevel();}

    public String gameOverMenu(){return gameState.gameOverMenu();}

    public String winMenu(){return gameState.winMenu();}

    //POSTERIORMENTE METER AQUI GET DATA(DADOS DE ELEMENTOS DO JOGO)
}
