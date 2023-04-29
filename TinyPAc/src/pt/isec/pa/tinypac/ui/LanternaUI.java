package pt.isec.pa.tinypac.ui;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.LevelManager;
import pt.isec.pa.tinypac.model.data.TinyPac;
import pt.isec.pa.tinypac.model.data.cell.Wall;


import java.io.IOException;

public class LanternaUI implements IGameEngineEvolve {
    LevelManager level;
    Screen screen;

    public LanternaUI(LevelManager level) throws IOException {
        this.level = level;
        screen = new DefaultTerminalFactory().createScreen();
        screen.setCursorPosition(null);
        show();
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        /*try {
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
        } catch (IOException e) { }*/
    }

    private void show() throws IOException {
        char[][] env = level.getLevel().getMaze();
        screen.startScreen();
        for (int y = 0; y < /*env.length*/level.getLevel().getLevelHeight(); y++) {
            for (int x = 0; x < /*env[0].length*/level.getLevel().getLevelWidth(); x++) {
                TextColor tc = switch(env[y][x]) {
                    case TinyPac.SYMBOL -> TextColor.ANSI.WHITE;
                    case Wall.SYMBOL -> TextColor.ANSI.YELLOW;
                    default -> TextColor.ANSI.BLACK;
                };
                TextColor bc = switch(env[y][x]) {
                    case TinyPac.SYMBOL -> TextColor.ANSI.RED;
                    case Wall.SYMBOL -> TextColor.ANSI.BLUE;
                    default -> TextColor.ANSI.WHITE;
                };
                screen.setCharacter(x,y, TextCharacter.fromCharacter(env[y][x],tc,bc)[0]);
                //screen.setCharacter(x,y, TextCharacter.fromCharacter(env[y][x],tc,bc, SGR.BOLD)[0]);
            }
        }
        screen.refresh();
    }
}
