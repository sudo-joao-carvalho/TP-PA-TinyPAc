package pt.isec.pa.tinypac.model.data;

public class Game {
    private Level level;

    public Game(int levelNumber){
        this.level = new Level(levelNumber);
    }

    public Level getLevel() {return this.level;}
    public void setLevel(Level newLevel){this.level = newLevel;}

}
