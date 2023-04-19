package pt.isec.pa.tinypac.model.data;

public class Ghost {

    private int cordX;
    private int cordY;

    public Ghost(int cordX, int cordY){ // Na herança cada um vai ter o seu nome e funcoes diferentes
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public int getCordX() {return this.cordX;}

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getCordY(){return this.cordY;}

    public void setCordY(int cordY){
        this.cordY = cordY;
    }
}
