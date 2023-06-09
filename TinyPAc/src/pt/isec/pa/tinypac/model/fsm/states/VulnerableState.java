package pt.isec.pa.tinypac.model.fsm.states;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
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

    public void notOP(){
        game.getLevel().getTinyPac().leaveOP();
    }

    public void setGhostsVulnerable(boolean ghostsVulnerable){

        for(Element element : game.getLevel().getGhosts()){
            element.setGhostVulnerable(ghostsVulnerable);
        }
    }


    @Override
    public boolean evolve(){
        setGhostsVulnerable(true);
        Thread timerThread = new Thread(() -> {
            int seconds = 0;
            while (seconds < 10) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds++;
            }
            setGhostsVulnerable(false);
            notOP();
            changeState(EMobsState.MOVE);
        });
        timerThread.start();

        return true;
    }

    @Override
    public boolean pause(){return false;}

    @Override
    public EMobsState getState(){return EMobsState.VULNERABLE;}
}
