package pt.isec.pa.tinypac.model.data;

public class TinyPac implements IMazeElement{

    private int cordX;
    private int cordY;

    public TinyPac(int cordX, int cordY){
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

    @Override
    public char getSymbol(){
        return 'M';
    }
}
