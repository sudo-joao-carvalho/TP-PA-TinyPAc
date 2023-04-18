package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class WaitBeginState extends GameStateAdapter {

    public WaitBeginState(GameContext context, Game game){
        super(context, game);
        //SETTERS DOS ELEMENTOS NO WAIT BEGIN
        //setCurrentLevel();
    }

    @Override
    public String keyPressed(){
        //APENAS PARA TESTE
        changeState(EGameState.PLAYING_LEVEL);
        return "O JOGO COMEÇOU";
    }

    @Override
    public EGameState getState(){return EGameState.WAIT_BEGIN;}
}
