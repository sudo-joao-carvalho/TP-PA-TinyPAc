package pt.isec.pa.tinypac.model.data;

public class Game {
    //private LevelManager levelManager;
    private Level level;
    private int lifes;
    private int score;

    public Game(/*String levelFile*/int levelNumber){
        //this.levelManager      = new LevelManager(levelNumber);
        this.level = new Level(levelNumber);
        this.lifes      = 3;
        this.score      = 0;
    }

    //public LevelManager getLevelManager() {
        //return levelManager;
    //}

    /*public void setLevel(Level level) {
        this.level = level;
    }*/

    public Level getLevel() {return this.level;}
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
