package pt.isec.pa.tinypac.ui;
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
import pt.isec.pa.tinypac.model.data.cell.*;
import pt.isec.pa.tinypac.model.data.mob.TinyPac;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.states.MoveState;


import java.io.IOException;

public class LanternaUI implements IGameEngineEvolve {

    GameContext gameContext;
    Screen screen;
    Terminal terminal;
    boolean finish = false;

    public LanternaUI(GameContext gameContext) throws IOException {
        this.gameContext    = gameContext;

        this.terminal       = new DefaultTerminalFactory()
                            .setInitialTerminalSize(new TerminalSize(50, 50))
                            .createTerminal();
        this.screen         = new TerminalScreen(terminal);
        show();
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        try {
            show();
            KeyStroke key = terminal.pollInput();
            if (gameContext.getGame().getLevel().onlyOneSpecies() ||
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
        screen.startScreen();
        terminal.setCursorVisible(false);
        try{
            terminal.clearScreen();
            terminal.flush();
            terminal.setCursorPosition(0,0);
            terminal.putString("Trabalho Académico: DEIS-ISEC   João Alves Pereira de Carvalho 2019131769");

            terminal.setCursorPosition(0, 2);
            terminal.putString("TinyPAc");
            terminal.setCursorPosition(0, 6);
            terminal.putString("1 - Iniciar Jogo");
            terminal.setCursorPosition(0, 8);
            terminal.putString("2 - Consultar Top 5");
            terminal.setCursorPosition(0, 10);
            terminal.putString("3 - Sair");
            terminal.setCursorPosition(0, 11);
            terminal.flush();

            KeyStroke key = screen.readInput();
            terminal.clearScreen();
            terminal.flush();
            if(key.getKeyType() == KeyType.Character){
                char c = key.getCharacter();
                int num = c - '0';

                if(num == 1){
                    //terminal.flush();
                    while(!finish){
                        terminal.clearScreen();
                        terminal.flush();
                        System.out.println("Entrou no loop while");
                        System.out.println("finish: " + finish);

                        switch(gameContext.getState()){
                            case WAIT_BEGIN -> waitBeginUI();
                            case MOVE -> moveUI();
                            //case VULNERABLE -> vulnerableUI();
                            //case END_LEVEL -> endLevelUI();
                            //case NEXT_LEVEL -> nextLevelUI();
                        }

                    }
                }else if(num == 2){
                    //terminal.flush();
                    terminal.setCursorPosition(0, 0);
                    terminal.putString("TOP 5:");
                    terminal.flush();
                }else if(num == 3){
                    terminal.clearScreen();
                    terminal.flush();
                    terminal.setCursorPosition(0, 0);
                    terminal.putString("Deseja mesmo sair?");
                    terminal.setCursorPosition(0, 2);
                    terminal.putString("1 - Sim");
                    terminal.setCursorPosition(0, 4);
                    terminal.putString("2 - Não");
                    terminal.flush();//faz as coisas aparecerem no ecra imediatamente

                    KeyStroke key2 = screen.readInput();
                    if(key2.getKeyType() == KeyType.Character){
                        char c2 = key2.getCharacter();
                        int num2 = c2 - '0';

                        if(num2 == 1){
                            terminal.clearScreen();
                            terminal.flush();
                            finish = true;
                        }

                        else{
                            terminal.clearScreen();
                            terminal.flush();
                            show();
                        }
                    }
                }
            }

        }catch(IOException e){
            System.out.println("ERRO");
        }
    }

    private void waitBeginUI() throws IOException {
        terminal.clearScreen();
        terminal.flush();
        terminal.setCursorPosition(0,2);
        terminal.putString("Pressiona uma tecla para começar...");
        terminal.flush();

        KeyStroke key = terminal.readInput();
        terminal.clearScreen();
        terminal.flush();
        if (key.getKeyType() == KeyType.Character) {
            char c = key.getCharacter();
            int num = c - '0';

            if(num == 1){
                gameContext.evolve();
            }
        }else finish = true;

    }

    private void moveUI() throws IOException {

        terminal.clearScreen();
        terminal.flush();
        terminal.setCursorVisible(false);
        terminal.setCursorPosition(0,0);
        terminal.flush();

        KeyStroke currentKey = terminal.readInput();
        KeyType currentKeyType = currentKey.getKeyType();
        terminal.flush();

        while(gameContext.getState() == EMobsState.MOVE) {

            char[][] map = gameContext.getGame().getLevel().getMaze();
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    TextColor tc = switch (map[y][x]) {
                        case TinyPac.SYMBOL, PowerBall.SYMBOL -> TextColor.ANSI.YELLOW;
                        case FoodBall.SYMBOL -> TextColor.ANSI.YELLOW_BRIGHT;
                        case Warp.SYMBOL -> TextColor.ANSI.RED;
                        case Wall.SYMBOL -> TextColor.ANSI.BLACK;
                        case Fruit.SYMBOL -> TextColor.ANSI.MAGENTA_BRIGHT;
                        default -> TextColor.ANSI.WHITE;
                    };
                    TextColor bc = switch (map[y][x]) {
                        case TinyPac.SYMBOL -> TextColor.ANSI.RED;
                        case FoodBall.SYMBOL, PowerBall.SYMBOL, Warp.SYMBOL, Fruit.SYMBOL -> TextColor.ANSI.CYAN;
                        default -> TextColor.ANSI.BLACK;
                    };
                    screen.setCharacter(x + 10, y + 2, TextCharacter.fromCharacter(map[y][x], tc, bc)[0]);
                }
            }
            screen.refresh();

            KeyStroke newKey = terminal.pollInput();
            terminal.flush();

            if (newKey != null) {
                KeyType newKeyType = null;
                switch (newKey.getKeyType()) {
                    case ArrowUp, ArrowDown, ArrowLeft, ArrowRight:
                        newKeyType = newKey.getKeyType();
                        break;
                    default:
                        // Ignore other keys
                        break;
                }

                if(newKeyType != null && newKeyType != currentKeyType){
                    currentKeyType = newKeyType;
                    gameContext.changePacmanDirection(currentKeyType);
                }
            }

            gameContext.changePacmanDirection(currentKeyType);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
