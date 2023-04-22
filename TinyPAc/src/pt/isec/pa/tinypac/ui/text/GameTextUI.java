package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.utils.PAInput;

public class GameTextUI {

    GameContext gameContextFsm;

    boolean finish;

    int flag = 0;
    public GameTextUI(GameContext gameContext){
        this.gameContextFsm = gameContext;
        this.finish         = false;
    }

    public void start(){
            while(!finish){
                switch(gameContextFsm.getState()){
                    case WAIT_BEGIN -> waitBeginUI();
                    case MOVE -> moveUI();
                    case EAT -> eatUI();
                    case END_LEVEL -> endLevelUI();
                    //case NEXT_LEVEL -> nextLevelUI();
                }
            }
    }

    public void menuUI(){
        System.out.printf("MenuUI");

        switch(PAInput.chooseOption("TinyPAc", "Iniciar Jogo", "Consultar Top 5", "Sair")){
            case 1 -> start();
            case 2 -> System.out.println("Top 5:");
            case 3 -> {
                if(PAInput.chooseOption("Deseja mesmo sair?", "Sim", "Não") == 1)
                    finish = true;
                else
                    menuUI();
            }
        }

        /*System.out.println("MAP\n");

        for(int h = 0; h < gameContextFsm.getGame().getLevel().getMapHeight(); h++){  //-> DEBUG: ver se esta a ler bem o mapa
            for(int w = 0; w < gameContextFsm.getGame().getLevel().getMapWidth(); w++){
                System.out.print(gameContextFsm.getGame().getLevel().getMaze()[h][w]);
            }
            System.out.println();
        }*/

        //gameContextFsm.getGame().getLevel().printMap(); -> DEBUG: ver se esta a ler bem o mapa
        //System.out.println(gameContextFsm.start());
    }

    private void waitBeginUI(){
        System.out.println("WaitBeginUI");

        if(PAInput.chooseOption("TinyPAc", "Pressiona uma tecla para começar...") == 1)
            gameContextFsm.move();
        else finish = true;
    }

    private void moveUI(){

        System.out.println("MoveUI");

        /*System.out.println("MAP\n");

        for(int h = 0; h < gameContextFsm.getGame().getLevel().getMapHeight(); h++){  //-> DEBUG: ver se esta a ler bem o mapa
            for(int w = 0; w < gameContextFsm.getGame().getLevel().getMapWidth(); w++){
                System.out.print(gameContextFsm.getGame().getLevel().getMaze()[h][w]);
            }
            System.out.println();
        }

        System.out.println();*/

        switch(PAInput.chooseOption("TinyPAc", "Move", "Eat", "End Level")){
            case 1 -> gameContextFsm.move();
            case 2 -> gameContextFsm.eat();
            case 3 -> gameContextFsm.endLevel();
        }
    }

    private void eatUI(){
        System.out.println("EatUI");

        if(PAInput.chooseOption("TinyPAc", "Eat") == 1)
            gameContextFsm.eat();
        else finish = true;
    }

    private void endLevelUI(){
        System.out.println("EndLevelUI");

        if(PAInput.chooseOption("TinyPAc", "EndLevel") == 1)
            gameContextFsm.endLevel();
        else finish = true;
    }

}
