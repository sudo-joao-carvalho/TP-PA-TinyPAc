package pt.isec.pa.tinypac.model.data.cell;

public class GhostCave extends Cell{

    public GhostCave(int ghostCaveCordY, int ghostCaveCordX){
        super(ghostCaveCordY, ghostCaveCordX);
    }

    @Override
    public char getSymbol(){
        return 'y';
    }
}
