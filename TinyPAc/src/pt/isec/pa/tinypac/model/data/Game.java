package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.LevelManager;

public class Game {
    private LevelManager levelManager;
    private int lifes;
    private int score;

    public Game(/*String levelFile*/int levelNumber){
        this.levelManager      = new LevelManager(levelNumber);
        this.lifes      = 3;
        this.score      = 0;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    /*public void setLevel(Level level) {
        this.level = level;
    }*/

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
