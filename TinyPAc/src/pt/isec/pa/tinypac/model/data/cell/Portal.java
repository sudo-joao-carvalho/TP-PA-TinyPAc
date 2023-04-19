package pt.isec.pa.tinypac.model.data.cell;

public class Portal extends Cell{

    public Portal(int portalCordY, int portalCordX){
        super(portalCordY, portalCordX);
    }

    @Override
    public char getSymbol(){
        return 'Y';
    }
}
