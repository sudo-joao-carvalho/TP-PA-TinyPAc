package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.fsm.GameContext;

public class GameTextUI {

    GameContext gameContextFsm;

    boolean finish;
    public GameTextUI(GameContext gameContext){
        this.gameContextFsm = gameContext;
        this.finish         = false;
    }

    public void start(){
        while(!finish){
            switch(gameContextFsm.getState()){
                case MENU -> menuUI();
                case WAIT_BEGIN -> waitBeginUI();
                case PLAYING_LEVEL -> playingLevelUI();
                case PAUSED -> pauseUI();
                case GAMEOVER_MENU -> gameOverMenuUI();
                case WIN_MENU -> winMenuUI();
                //case NEXT_LEVEL -> nextLevelUI();
            }
        }

        menuUI();
    }

    public void menuUI(){
        System.out.println("MenuUI");

        //System.out.println("MAP\n");

        /*for(int h = 0; h < gameContextFsm.getGame().getLevel().getMapHeight(); h++){  -> DEBUG: ver se esta a ler bem o mapa
            for(int w = 0; w < gameContextFsm.getGame().getLevel().getMapWidth(); w++){
                System.out.print(gameContextFsm.getGame().getLevel().getMaze()[h][w]);
            }
            System.out.println();
        }*/

        //gameContextFsm.getGame().getLevel().printMap(); -> DEBUG: ver se esta a ler bem o mapa
        System.out.println(gameContextFsm.start());
    }

    private void waitBeginUI() {
        System.out.println("Wait_BeginUI");
        System.out.println(gameContextFsm.keyPressed());
    }

    private void playingLevelUI() {
        System.out.println("Playing_levelUI");
        System.out.println(gameContextFsm.pause());
        System.out.println(gameContextFsm.showMenu());
        finish = true;
    }

    private void pauseUI() {
        System.out.println("PauseUI");
        gameContextFsm.resume();
    }

    private void gameOverMenuUI() {
        System.out.println("GameOverUI");
        gameContextFsm.showMenu();
    }

    private void winMenuUI() {
        System.out.println("WinMenuUI");
        gameContextFsm.showMenu();
    }

    private void nextLevelUI() {
        System.out.println("Next_LevelUI");
        gameContextFsm.restart();
    }
}
