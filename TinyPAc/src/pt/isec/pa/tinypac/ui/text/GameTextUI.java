package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.utils.PAInput;

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
                    case WAIT_BEGIN -> waitBeginUI();
                    case MOVE -> moveUI();
                    case VULNERABLE -> vulnerableUI();
                    case END_LEVEL -> endLevelUI();
                    //case NEXT_LEVEL -> nextLevelUI();
                }
            }
    }

    public void menuUI(){

        /*System.out.println("MAP\n");

        for(int h = 0; h < gameContextFsm.getGame().getLevelManager().getLevel().getLevelHeight(); h++){  //-> DEBUG: ver se esta a ler bem o mapa
            for(int w = 0; w < gameContextFsm.getGame().getLevelManager().getLevel().getLevelWidth(); w++){
                System.out.print(gameContextFsm.getGame().getLevelManager().getLevel().getElementAt(h, w));
            }
            System.out.println();
        }*/

        System.out.println("Trabalho Académico: DEIS-ISEC   João Alves Pereira de Carvalho 2019131769");
        System.out.println();
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

    }

    private void waitBeginUI(){
        System.out.println("WaitBeginUI");

        if(PAInput.chooseOption("TinyPAc", "Pressiona uma tecla para começar...") == 1)
            gameContextFsm.move();
        else finish = true;
    }

    private void moveUI(){

        System.out.println("MoveUI");

        switch(PAInput.chooseOption("TinyPAc", "Move", "Vulnerable", "End Level")){
            case 1 -> gameContextFsm.move();
            case 2 -> gameContextFsm.vulnerable();
            case 3 -> gameContextFsm.endLevel();
        }
    }

    private void vulnerableUI(){
        System.out.println("VulnerableUI");

        switch(PAInput.chooseOption("TinyPAc", "Move", "EndLevel")){
            case 1 -> gameContextFsm.move();
            case 2 -> gameContextFsm.endLevel();
        }
    }

    private void endLevelUI(){
        System.out.println("EndLevelUI");

        if(PAInput.chooseOption("TinyPAc", "EndLevel") == 1)
            gameContextFsm.endLevel();
        else finish = true;
    }

}
