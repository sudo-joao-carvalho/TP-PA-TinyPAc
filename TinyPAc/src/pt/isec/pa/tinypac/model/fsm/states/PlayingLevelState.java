package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class PlayingLevelState extends GameStateAdapter {

    public PlayingLevelState(GameContext context, Game game){
        super(context, game);

        //SETTERS DAS VARS
    }

    @Override
    public String pause(){

        //APENAS TESTE
        changeState(EGameState.PAUSED);
        return "JOGO PAUSADO";
    }

    @Override
    public String showMenu(){

        //APENAS TESTE
        if(game.getScore() == 0) {
            changeState(EGameState.WIN_MENU);
            return "VITORIA";
        }else {
            changeState(EGameState.GAMEOVER_MENU);
            return "DERROTA";
        }
    }

    @Override
    public EGameState getState(){return EGameState.PLAYING_LEVEL;}
}
