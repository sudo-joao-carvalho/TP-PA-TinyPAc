package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class MenuState extends GameStateAdapter {
    public MenuState(GameContext context, Game game){
        super(context, game);

        //SET AS VARS INICIAIS DO JOGO
    }

    @Override
    public String start(){

        //PARA DEMONSTRAÇAO
        changeState(EGameState.WAIT_BEGIN);
        return "A ESPERA QUE O JOAO LINDO CARREGUE NUM BOTAO";
    }

    @Override
    public EGameState getState(){return EGameState.MENU;}
}
