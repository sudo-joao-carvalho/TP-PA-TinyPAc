package pt.isec.pa.tinypac.model.fsm.states;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class VulnerableState extends MobsStateAdapter {

    public VulnerableState(GameContext context, Game game){
        super(context, game);
        //SETTERS
        //game.getLevel().getTinyPac().enterOP();
    }

    /*public void changePacmanDirection(KeyType key){
        game.getLevel().evolve(key);
    }*/

    public void notVulnerable(){
        game.getLevel().getTinyPac().leaveOP();
    }

    @Override
    public boolean evolve(){
        notVulnerable();
        changeState(EMobsState.MOVE);
        return true;
    }

    @Override
    public boolean pause(){return false;}

    @Override
    public EMobsState getState(){return EMobsState.VULNERABLE;}
}
