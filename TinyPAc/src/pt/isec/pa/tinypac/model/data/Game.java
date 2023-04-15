package pt.isec.pa.tinypac.model.data;

public class Game {

    private String current_level;

    private int lifes;

    private int score;

    public Game(String current_level){
        this.current_level  = current_level;
        this.lifes          = 3;
        this.score          = 0;
    }

    public String getCurrent_level() {return current_level;}

    public void setCurrent_level(String current_level) {
        this.current_level = current_level;
    }

    public int getLifes(){return lifes;}

    public void setLifes(int lifes){
        this.lifes = lifes;
    }

    public int getScore(){return score;}

    public void setScore(int score){
        this.score = score;
    }
}
