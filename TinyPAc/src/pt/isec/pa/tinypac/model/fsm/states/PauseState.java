package pt.isec.pa.tinypac.model.fsm.states;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class PauseState extends MobsStateAdapter {

    public PauseState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }

    /*@Override
    public boolean evolve(){
        System.out.println("jogo pausado");
        changeState(EMobsState.MOVE);
        return true;
    }*/

    @Override
    public boolean unpause(){
        System.out.println("jogo unpaused");
        changeState(EMobsState.MOVE);
        return true;
    }

    @Override
    public EMobsState getState(){return EMobsState.PAUSE;}

}