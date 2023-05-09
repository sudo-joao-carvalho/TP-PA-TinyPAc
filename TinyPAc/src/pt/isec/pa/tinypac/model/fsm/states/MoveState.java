package pt.isec.pa.tinypac.model.fsm.states;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class MoveState extends MobsStateAdapter {

    public MoveState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }


    public void changePacmanDirection(KeyType key){
        game.getLevel().evolve(key);
    }

    @Override
    public boolean evolve(){
        //changeState(EMobsState.VULNERABLE);
        return true;
    }

    @Override
    public boolean pause(){return false;}

    @Override
    public EMobsState getState(){return EMobsState.MOVE;}
}
