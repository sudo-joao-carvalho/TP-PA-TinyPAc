package pt.isec.pa.tinypac.model.data.cell;

public class Wall extends Cell{

    public Wall(int wallCordY, int wallCordX){
        super(wallCordY, wallCordX);
    }

    @Override
    public char getSymbol(){
        return 'x';
    }
}
