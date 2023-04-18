package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class GameOverMenuState extends GameStateAdapter {

    public GameOverMenuState(GameContext context, Game game){
        super(context, game);

        //SETTERS DAS VARS
    }

    @Override
    public String restart(){

        //APENAS PARA TESTE
        changeState(EGameState.WAIT_BEGIN);
        return "RECOMEÇAR NIVEL";
    }


    @Override
    public EGameState getState(){return EGameState.GAMEOVER_MENU;}
}
