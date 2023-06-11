package pt.isec.pa.tinypac.model.fsm.states;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.mob.TinyPac;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class MoveState extends MobsStateAdapter {

    public MoveState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }

    public int checkLevelOver(){

        if(TinyPac.SCORE >= 10){
            game.getLevel().setLevelComplete();
            return 1;
        }

        if(game.getLevel().getTinyPac().getLifes() == 0){
            return 2;
        }

        return 0;
    }

    public boolean checkVulnerable(){return game.getLevel().getTinyPac().isOP();}

    public void setGhostsVulnerable(boolean ghostsVulnerable){

        for(Element element : game.getLevel().getGhosts()){
            element.setGhostVulnerable(ghostsVulnerable);
        }
    }

    @Override
    public boolean evolve(){
        if(checkVulnerable()){
            setGhostsVulnerable(true);
            changeState(EMobsState.VULNERABLE);
            return true;
        }

        /*if(checkLevelOver()){
            if(game.getLevel().getLevelNumber() == 20){
                changeState(EMobsState.END_LEVEL);
            }

            System.out.println("pontuacao atingida");
            TinyPac.SCORE = 0;
            game.setLevel(new Level());
            changeState(EMobsState.WAIT_BEGIN);
            return true;
        }*/
        switch(checkLevelOver()){
            case 1 -> {
                if(game.getLevel().getLevelNumber() == 20){
                    changeState(EMobsState.END_LEVEL);
                }
                changeState(EMobsState.WAIT_BEGIN);

                System.out.println("pontuacao atingida");
                TinyPac.SCORE = 0;

                int currentLevelNumber = game.getLevel().getLevelNumber();
                game.setLevel(new Level(currentLevelNumber + 1));
            }

            case 2 -> {
                changeState(EMobsState.WAIT_BEGIN);
                System.out.println("Pacman perdeu todas as vidas");

                int currentLevelNumber = game.getLevel().getLevelNumber();
                game.setLevel(new Level(currentLevelNumber));
            }
        }

        changeState(EMobsState.MOVE);
        return true;


    }

    @Override
    public boolean pause(){return false;}

    @Override
    public EMobsState getState(){return EMobsState.MOVE;}
}
