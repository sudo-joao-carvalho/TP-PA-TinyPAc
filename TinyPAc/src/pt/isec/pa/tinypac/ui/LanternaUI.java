package pt.isec.pa.tinypac.ui;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.LevelManager;
import pt.isec.pa.tinypac.model.data.mob.TinyPac;
import pt.isec.pa.tinypac.model.data.cell.FoodBall;
import pt.isec.pa.tinypac.model.data.cell.Wall;


import java.io.IOException;

public class LanternaUI implements IGameEngineEvolve {
    LevelManager level;
    Screen screen;

    public LanternaUI(LevelManager level) throws IOException {
        this.level = level;
        /*screen = new DefaultTerminalFactory().createScreen();
        screen.setCursorPosition(null);*/

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        TerminalSize size = new TerminalSize(80, 40);
        terminalFactory.setInitialTerminalSize(size);
        TerminalScreen screen = new TerminalScreen(terminalFactory.createTerminal());

        this.screen = screen;
        show();
    }

    public void start(){

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
    }
}
