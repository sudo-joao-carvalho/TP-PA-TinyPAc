package pt.isec.pa.tinypac.model.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Game {
    private TinyPac tinyPac;
    private ArrayList<Ghost> ghosts;
    private Level level;
    private int lifes;
    private int score;

    public Game(/*String levelFile*/int levelNumber){
        this.level      = new Level(levelNumber);
        this.tinyPac    = new TinyPac(0, 0); //Coordenadas de spawn
        this.ghosts     = new ArrayList<Ghost>();
        for(Ghost ghost: ghosts){
            ghost       = new Ghost(0 ,0); //Coordenadas de spawn
        }
        this.lifes      = 3;
        this.score      = 0;
    }

    public TinyPac getTinyPac() {
        return tinyPac;
    }

    public void setTinyPac(TinyPac tinyPac) {
        this.tinyPac = tinyPac;
    }

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    public void setGhosts(ArrayList<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
