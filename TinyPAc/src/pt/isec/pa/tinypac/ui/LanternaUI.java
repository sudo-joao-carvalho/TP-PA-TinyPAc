package pt.isec.pa.tinypac.ui;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.LevelManager;
import pt.isec.pa.tinypac.model.data.mob.TinyPac;
import pt.isec.pa.tinypac.model.data.cell.FoodBall;
import pt.isec.pa.tinypac.model.data.cell.Wall;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.utils.PAInput;


import java.io.IOException;

public class LanternaUI implements IGameEngineEvolve {
    LevelManager level;
    GameContext gameContextFsm;
    Screen screen;
    Terminal terminal;

    boolean finish;

    public LanternaUI(LevelManager level, GameContext gameContext) throws IOException {
        this.finish = false;
        this.gameContextFsm = gameContext;
        this.level = level;

        DefaultTerminalFactory terminal = new DefaultTerminalFactory();
        TerminalSize size = new TerminalSize(80, 40);
        terminal.setInitialTerminalSize(size);
        this.terminal = terminal.createTerminal();
        TerminalScreen screen = new TerminalScreen(this.terminal);

        this.screen = screen;
        show();
    }

    public void start() throws IOException {

        try{
            //screen.setCursorPosition(new TerminalPosition(0, 0));
            terminal.setCursorPosition(0,0);
            terminal.putString("Trabalho Académico: DEIS-ISEC   João Alves Pereira de Carvalho 2019131769");

            //terminal.setCursorPosition(1,5);
            //terminal.putString("Trabalho Académico: DEIS-ISEC   João Alves Pereira de Carvalho 2019131769");

        }catch(IOException e){
            System.out.println("ERRO");
        }
        //screen.putString()
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        try {
            show();
            KeyStroke key = screen.pollInput();
            if (level.onlyOneSpecies() ||
                    ( key != null &&
                            (key.getKeyType() == KeyType.Escape ||
                                    (key.getKeyType() == KeyType.Character &&
                                            key.getCharacter().equals('q'))))
            ){
                gameEngine.stop();
                screen.close();
            }
        } catch (IOException e) { }
    }

    private void show() throws IOException {
        start();

        char[][] map = level.getLevel().getMaze();
        screen.startScreen();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                TextColor tc = switch(map[y][x]) {
                    case TinyPac.SYMBOL -> TextColor.ANSI.WHITE;
                    case Wall.SYMBOL -> TextColor.ANSI.BLUE;
                    case FoodBall.SYMBOL -> TextColor.ANSI.YELLOW;
                    default -> TextColor.ANSI.BLACK;
                };
                TextColor bc = switch(map[y][x]) {
                    case TinyPac.SYMBOL -> TextColor.ANSI.RED;
                    case Wall.SYMBOL -> TextColor.ANSI.BLACK;
                    case FoodBall.SYMBOL -> TextColor.ANSI.BLACK;
                    default -> TextColor.ANSI.WHITE;
                };
                screen.setCharacter(x,y, TextCharacter.fromCharacter(map[y][x],tc,bc)[0]);
                //screen.setCharacter(x,y, TextCharacter.fromCharacter(map[y][x],tc,bc, SGR.BOLD)[0]);
            }
        }
        screen.refresh();

        //terminal.setCursorPosition(2,2);
        //terminal.putString("A");

        /*while(!finish){

            switch(gameContextFsm.getState()){
                case WAIT_BEGIN -> waitBeginUI();
                case MOVE -> moveUI();
                case EAT -> eatUI();
                case END_LEVEL -> endLevelUI();
                //case NEXT_LEVEL -> nextLevelUI();
            }
        }*/
    }

    public void menuUI() throws IOException {

        /*System.out.println("MAP\n");

        for(int h = 0; h < gameContextFsm.getGame().getLevelManager().getLevel().getLevelHeight(); h++){  //-> DEBUG: ver se esta a ler bem o mapa
            for(int w = 0; w < gameContextFsm.getGame().getLevelManager().getLevel().getLevelWidth(); w++){
                System.out.print(gameContextFsm.getGame().getLevelManager().getLevel().getElementAt(h, w));
            }
            System.out.println();
        }*/

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
