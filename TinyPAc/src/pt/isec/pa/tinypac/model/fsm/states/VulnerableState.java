package pt.isec.pa.tinypac.model.fsm.states;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.mob.TinyPac;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class VulnerableState extends MobsStateAdapter {

    public VulnerableState(GameContext context, Game game){
        super(context, game);
        //SETTERS
        //game.getLevel().getTinyPac().enterOP();
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

        Thread timerThread = new Thread(() -> { //thread que faz com que esteja neste estado durante 10 segundos
            int seconds = 0;
            while (seconds < 10) {
                try {
                    /*if(checkLevelOver()){
                        if(game.getLevel().getLevelNumber() == 20){
                            changeState(EMobsState.END_LEVEL);
                        }

                        System.out.println("pontuacao atingida");
                        TinyPac.SCORE = 0;
                        game.setLevel(new Level());

                        changeState(EMobsState.WAIT_BEGIN);
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
