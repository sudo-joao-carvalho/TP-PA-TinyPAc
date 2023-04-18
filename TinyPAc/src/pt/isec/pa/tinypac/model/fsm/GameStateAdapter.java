package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Game;

public abstract class GameStateAdapter implements IGameState {

    protected Game game;

    protected GameContext context;

    protected GameStateAdapter(GameContext context, Game game){
        this.context    = context;
        this.game       = game;
    }

    protected void changeState(EGameState newState){context.changeState(newState.createState(context, game));}

    @Override
    public String start(){return "Game Started";}

    @Override
    public String pause(){return "Game Paused";}

    @Override
    public String resume(){return "Game Resumed";}

    @Override
    public String loadNextLevel(){return "Next Level";}

    @Override
    public String showMenu(){return "Menu";}

    @Override
    public String keyPressed(){return "A key was pressed";}

    //@Override
    //public String changeLevel(){return "New Level";}

    @Override
    public String restart(){return "Restarting Level";}


    /*String start();
    String pause();
    String resume();
    String loadNextLevel();
    String gameOverMenu();
    String winMenu();*/
}
