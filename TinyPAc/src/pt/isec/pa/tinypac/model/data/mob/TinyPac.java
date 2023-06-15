package pt.isec.pa.tinypac.model.data.mob;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.cell.*;

import java.util.Collections;
import java.util.List;

public class TinyPac extends Element {
    public static final char SYMBOL = 'M';
    private int score = 0;
    private int lifes = 3;

    private boolean isOP = false;
    private boolean lostLife = false;



    public TinyPac(Level level){
        super(level);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

        if(checkFood(y, x) instanceof FoodBall){
            score += 1;
            return true;
        }


        if(checkFood(y, x) instanceof PowerBall){
            score += 10;
            enterOP();
            return true;
        }
        if(checkFood(y, x) instanceof Fruit){
            score += 25;
            return true;
        }


        return false;
    }

    public void enterOP(){
        this.isOP = true;
    }

    public void leaveOP(){
        this.isOP = false;
    }

    public boolean isOP(){return this.isOP;}

    public void setLostLife(boolean lostLife){ this.lostLife = lostLife;}

    @Override
    public void evolve(KeyCode key){ //move

        Level.Position myPos = level.getPositionOf(this);
        int dx = 0;
        int dy = 0;
        switch (key) {
            case UP -> dy = -1;
            case DOWN -> dy = 1;
            case LEFT -> dx = -1;
            case RIGHT -> dx = 1;
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

                            //logica para para o boneco por 1 segundo ate se voltar a mover quando perde uma vida
                            /*while(lostLife){
                                Level.Position myPosTemp = new Level.Position(myPos.y(), myPos.x());
                                Thread timerThread = new Thread(() -> {

                                    int seconds = 0;
                                    while (seconds < 10) {
                                        try {
                                            level.setPositionOf(myPosTemp, this);
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        seconds++;
                                    }

                                    lostLife = false;

                                });
                                timerThread.start();
                            }*/

                        }

                    }else{
                        if(!(level.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Blinky) && !(level.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Clyde) && !(level.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Inky) && !(level.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Pinky)){
                            eat(myPos.y() + dy, myPos.x() + dx);

                            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                            Level.Position newPos = new Level.Position(myPos.y() + dy, myPos.x() + dx);
                            level.setPositionOf(newPos, this);
                            System.out.println("movi me para " + newPos);
                        }else{
                            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                            //mete o fantasma na cave de novo
                            for(Level.Position pos : level.getCave()){
                                Element element = level.getElement(pos.y() - 2, pos.x()); //ver se o elemento é o Portal
                                Level.Position cavePos = new Level.Position(pos.y(), pos.x());
                                if(element instanceof Portal){
                                    level.addElement(new Blinky(level), pos.y(), pos.x());
                                    level.getBlinky().setGhostVulnerable(true);
                                }
                            }

                            Level.Position newPos = new Level.Position(myPos.y() + dy, myPos.x() + dx);
                            level.setPositionOf(newPos, this);

                            score += 50;
                            //FALTA POR O FANTASMA A VOLTAR PARA A CAVE
                            System.out.println("\nComi um fantasma");
                        }
                    }

                }
            }
        }

    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }

}
