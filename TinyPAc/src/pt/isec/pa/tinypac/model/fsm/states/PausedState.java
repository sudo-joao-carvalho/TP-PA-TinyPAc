package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class PausedState extends GameStateAdapter {

    public PausedState(GameContext context, Game game){
        super(context, game);

        //SETTERS DE VARS
    }

    @Override
    public String resume(){
        //PARA TESTE
        changeState(EGameState.PLAYING_LEVEL);
        return "JOGANDO NOVAMENTE";
    }

    @Override
    public EGameState getState(){return EGameState.PAUSED;}
}
