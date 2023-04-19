package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.IMazeElement;

public class Cell implements IMazeElement {

    private int cordX;

    private int cordY;

    public Cell(int cordX, int cordY){ // depois na herança cada celula vai ter um type diferente
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
    public char getSymbol(){ //default
        return 'x';
    }
}
