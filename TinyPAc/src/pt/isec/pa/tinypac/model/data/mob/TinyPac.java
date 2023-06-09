package pt.isec.pa.tinypac.model.data.mob;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.cell.*;

import java.util.Collections;
import java.util.List;

public class TinyPac extends Element {
    public static final char SYMBOL = 'M';
    public static int SCORE = 0;
    private int lifes = 3;

    private boolean isOP = false;

    private int counter = 0;

    public TinyPac(Level level){
        super(level);

    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public boolean checkWarp(int y, int x){
        Level.Position myPos = level.getPositionOf(this);

        List<Level.Position> lst = level.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
        if (lst.isEmpty()) {
            return false;
        }
        Collections.shuffle(lst);

        for(Level.Position pos: lst){
            if(pos.y() == y && pos.x() == x){
                return level.getElement(pos.y(), pos.x()) instanceof Warp;
            }
        }

        return false;
    }

    public Element checkFood(int y, int x){
        Level.Position myPos = level.getPositionOf(this);

        List<Level.Position> lst = level.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
        if (lst.isEmpty()) {
            return null;
        }
        Collections.shuffle(lst);

        for(Level.Position pos: lst){
            if(pos.y() == y && pos.x() == x){
                if(level.getElement(pos.y(), pos.x()) instanceof FoodBall || level.getElement(pos.y(), pos.x()) instanceof PowerBall)
                    return level.getElement(pos.y(), pos.x());
                else return null;
            }

        }

        return null;

    }

    public boolean isValidPosition(int y, int x){

        Level.Position myPos = level.getPositionOf(this);

        List<Level.Position> lst = level.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
        if (lst.isEmpty()) {
            return false;
        }
        Collections.shuffle(lst);

        for(Level.Position pos: lst){
            if(pos.y() == y && pos.x() == x){
                return !(level.getElement(pos.y(), pos.x()) instanceof Wall) &&
                        !(level.getElement(pos.y(), pos.x()) instanceof Portal);
            }

        }

        return true;
    }

    @Override
    public boolean eat(int y, int x){

        //void version
        /*if(checkFood(y, x) instanceof FoodBall){
            SCORE += 1;
        }


        if(checkFood(y, x) instanceof PowerBall){
            SCORE += 10;
            enterOP();
        }
        */

        if(checkFood(y, x) instanceof FoodBall){
            SCORE += 1;
            return true;
        }


        if(checkFood(y, x) instanceof PowerBall){
            SCORE += 10;
            enterOP();
            return true;
        }

        /*System.out.println("isOP: " + isOP);
        if(isOP){

            Level.Position myPos = level.getPositionOf(this);

            List<Level.Position> lst = level.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
            if (lst.isEmpty()) {
                return false;
            }
            Collections.shuffle(lst);

            for(Level.Position pos: lst){
                if(pos.y() == y && pos.x() == x){
                    if(!(level.getElement(pos.y(), pos.x()) instanceof Blinky) && !(level.getElement(pos.y(), pos.x()) instanceof Clyde) && !(level.getElement(pos.y(), pos.x()) instanceof Inky) && !(level.getElement(pos.y(), pos.x()) instanceof Pinky)){
                        if((level.getElement(pos.y(), pos.x()).getGhostVulnerable())){
                            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                            Level.Position newPos = new Level.Position(pos.y(), pos.x());
                            level.setPositionOf(newPos, this);

                            SCORE += 50;
                            //FALTA POR O FANTASMA A VOLTAR PARA A CAVE
                            System.out.println("\nComi um fantasma");
                        }
                    }
                }

            }
        }*/

        return false;
    }

    public void enterOP(){
        this.isOP = true;
    }

    public void leaveOP(){
        this.isOP = false;
    }

    public boolean isOP(){return this.isOP;}

    @Override
    public void evolve(KeyType key){ //move

        Level.Position myPos = level.getPositionOf(this);
        int dx = 0;
        int dy = 0;
        switch (key) {
            case ArrowUp -> dy = -1;
            case ArrowDown -> dy = 1;
            case ArrowLeft -> dx = -1;
            case ArrowRight -> dx = 1;
        }
        if (dx != 0 || dy != 0) {
            // Verifica se o movimento é válido
            if (isValidPosition(myPos.y() + dy, myPos.x() + dx)) {

                if(checkWarp(myPos.y() + dy, myPos.x() + dx)){
                    //Level.Position entryWarpPosition = new Level.Position(myPos.y(), myPos.x());
                    for(Level.Position pos: level.getWarps()){

                        if(pos.y() != myPos.y() + dy || pos.x() != myPos.x() + dx){
                            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
                            //System.out.println("\nnovo warp na posicao " + myPos);

                            Level.Position newPos = new Level.Position(pos.y() + dy, pos.x() + dx);
                            level.setPositionOf(newPos, this);
                            //System.out.println("\n warping to " + newPos);

                        }
                    }
                } else{

                    if(!isOP){
                        if(!(level.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Blinky) && !(level.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Clyde) && !(level.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Inky) && !(level.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Pinky)){
                            eat(myPos.y() + dy, myPos.x() + dx);

                            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                            Level.Position newPos = new Level.Position(myPos.y() + dy, myPos.x() + dx);
                            level.setPositionOf(newPos, this);
                            System.out.println("movi me para " + newPos);
                        }else{
                            System.out.println("encontrei um fantasma");
                            //level.setPositionOf(spawnPosition, this);
                        }

                    }
                    /*eat(myPos.y() + dy, myPos.x() + dx);

                    level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                    Level.Position newPos = new Level.Position(myPos.y() + dy, myPos.x() + dx);
                    level.setPositionOf(newPos, this);
                    System.out.println("movi me para " + newPos);*/

                    /*if(!isOP){
                        if(!(level.getElement(myPos.y(), myPos.x()) instanceof Blinky) && !(level.getElement(myPos.y(), myPos.x()) instanceof Clyde) && !(level.getElement(myPos.y(), myPos.x()) instanceof Inky) && !(level.getElement(myPos.y(), myPos.x()) instanceof Pinky)){
                            eat(myPos.y() + dy, myPos.x() + dx);

                            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                            Level.Position newPos = new Level.Position(myPos.y() + dy, myPos.x() + dx);
                            level.setPositionOf(newPos, this);
                            System.out.println("movi me para " + newPos);
                        }else if((level.getElement(myPos.y(), myPos.x()) instanceof Blinky) || (level.getElement(myPos.y(), myPos.x()) instanceof Clyde) || (level.getElement(myPos.y(), myPos.x()) instanceof Inky) || (level.getElement(myPos.y(), myPos.x()) instanceof Pinky)){
                            //level.setPositionOf(spawnPosition, this);

                            level.setPositionOf(spawnPosition, this);
                        }

                    }else{
                        Level.Position mmyPos = level.getPositionOf(this);

                        List<Level.Position> lst = level.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
                        if (lst.isEmpty()) {
                            return ;
                        }
                        Collections.shuffle(lst);

                        for(Level.Position pos: lst){
                            if(pos.y() == mmyPos.y() && pos.x() == mmyPos.x()){
                                if((level.getElement(pos.y(), pos.x()) instanceof Blinky) || (level.getElement(pos.y(), pos.x()) instanceof Clyde) || (level.getElement(pos.y(), pos.x()) instanceof Inky) || (level.getElement(pos.y(), pos.x()) instanceof Pinky)){
                                    if((level.getElement(pos.y(), pos.x()).getGhostVulnerable())){
                                        level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                                        Level.Position newPos = new Level.Position(pos.y(), pos.x());
                                        level.setPositionOf(newPos, this);

                                        SCORE += 50;
                                        //FALTA POR O FANTASMA A VOLTAR PARA A CAVE
                                        System.out.println("\nComi um fantasma");
                                    }
                                }else{
                                    eat(myPos.y() + dy, myPos.x() + dx);

                                    level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                                    Level.Position newPos = new Level.Position(myPos.y() + dy, myPos.x() + dx);
                                    level.setPositionOf(newPos, this);
                                    System.out.println("movi me para " + newPos);
                                }
                            }

                        }
                    }*/
                }
            }
        }

    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }

}
