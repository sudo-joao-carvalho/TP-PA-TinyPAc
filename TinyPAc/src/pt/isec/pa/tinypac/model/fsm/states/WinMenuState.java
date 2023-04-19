package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class WinMenuState extends GameStateAdapter {

    public WinMenuState(GameContext context, Game game){
        super(context, game);

        //SETTERS DAS VARS
    }

    @Override
    public String restart(){

        //APENAS PARA TESTE
        //changeState(EGameState.NEXT_LEVEL);
        changeState(EGameState.WAIT_BEGIN);
        return "PROXIMO NIVEL";
    }

    @Override
    public EGameState getState(){return EGameState.WIN_MENU;}
}
