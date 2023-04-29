package pt.isec.pa.tinypac.model.data;

public abstract class Element implements IMazeElement{

    protected Level level;

    protected Element(Level level) {
        this.level = level;
    } // o facto de ter aqui o construtor eu depois n tenho que repetir nas classes derivadas

    abstract public void evolve();
}
