package pt.isec.pa.tinypac.model.data.mob;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.data.cell.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class TinyPac extends Element implements Serializable {
    public static final char SYMBOL = 'M';
    private int score = 0;
    private int lifes = 3;

    private boolean isOP = false;
    private boolean lostLife = false;



    public TinyPac(GameData gameData){
        super(gameData);

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
        GameData.Position myPos = gameData.getPositionOf(this);

        List<GameData.Position> lst = gameData.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
        if (lst.isEmpty()) {
            return false;
        }
        Collections.shuffle(lst);

        for(GameData.Position pos: lst){
            if(pos.y() == y && pos.x() == x){
                return gameData.getElement(pos.y(), pos.x()) instanceof Warp;
            }
        }

        return false;
    }

    public Element checkFood(int y, int x){
        GameData.Position myPos = gameData.getPositionOf(this);

        List<GameData.Position> lst = gameData.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
        if (lst.isEmpty()) {
            return null;
        }
        Collections.shuffle(lst);

        for(GameData.Position pos: lst){
            if(pos.y() == y && pos.x() == x){
                if(gameData.getElement(pos.y(), pos.x()) instanceof FoodBall || gameData.getElement(pos.y(), pos.x()) instanceof PowerBall || gameData.getElement(pos.y(), pos.x()) instanceof Fruit)
                    return gameData.getElement(pos.y(), pos.x());
                else return null;
            }

        }

        return null;

    }

    public boolean isValidPosition(int y, int x){

        GameData.Position myPos = gameData.getPositionOf(this);

        List<GameData.Position> lst = gameData.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
        if (lst.isEmpty()) {
            return false;
        }
        Collections.shuffle(lst);

        for(GameData.Position pos: lst){
            if(pos.y() == y && pos.x() == x){
                return !(gameData.getElement(pos.y(), pos.x()) instanceof Wall) &&
                        !(gameData.getElement(pos.y(), pos.x()) instanceof Portal);
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
            System.out.println("comi uma bola");
            //System.out.println(isOP);
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

        GameData.Position myPos = gameData.getPositionOf(this);
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
                    for(GameData.Position pos: gameData.getWarps()){

                        if(pos.y() != myPos.y() + dy || pos.x() != myPos.x() + dx){
                            gameData.addElement(new EmptyCell(gameData), myPos.y(), myPos.x());
                            //System.out.println("\nnovo warp na posicao " + myPos);

                            GameData.Position newPos = new GameData.Position(pos.y() + dy, pos.x() + dx);
                            gameData.setPositionOf(newPos, this);
                            //System.out.println("\n warping to " + newPos);

                        }
                    }
                } else{

                    if(!isOP){
                        if(!(gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Blinky) && !(gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Clyde) && !(gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Inky) && !(gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Pinky)){
                            eat(myPos.y() + dy, myPos.x() + dx);

                            gameData.addElement(new EmptyCell(gameData), myPos.y(), myPos.x());

                            GameData.Position newPos = new GameData.Position(myPos.y() + dy, myPos.x() + dx);
                            gameData.setPositionOf(newPos, this);
                            //System.out.println("movi me para " + newPos);
                        }else{
                            //System.out.println("encontrei um fantasma");

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
                        if(!(gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Blinky) && !(gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Clyde) && !(gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Inky) && !(gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Pinky)){
                            eat(myPos.y() + dy, myPos.x() + dx);

                            gameData.addElement(new EmptyCell(gameData), myPos.y(), myPos.x());

                            GameData.Position newPos = new GameData.Position(myPos.y() + dy, myPos.x() + dx);
                            gameData.setPositionOf(newPos, this);
                            //System.out.println("movi me para " + newPos);
                        }else if((gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Blinky) || (gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Clyde) || (gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Inky) || (gameData.getElement(myPos.y() + dy, myPos.x() + dx) instanceof Pinky)){
                            Element e = gameData.getElement(myPos.y() + dy, myPos.x() + dx);


                            gameData.addElement(new EmptyCell(gameData), myPos.y(), myPos.x());


                            //mete o fantasma na cave de novo
                            for(GameData.Position pos : gameData.getCave()){
                                Element element = gameData.getElement(pos.y() - 2, pos.x()); //ver se o elemento é o Portal
                                GameData.Position cavePos = new GameData.Position(pos.y(), pos.x());
                                if(element instanceof Portal){
                                    if(e instanceof Blinky){
                                        gameData.addElement(new Blinky(gameData), pos.y(), pos.x());
                                        gameData.getBlinky().setGhostVulnerable(true);
                                    }else if(e instanceof  Clyde){
                                        gameData.addElement(new Clyde(gameData), pos.y(), pos.x());
                                        gameData.getClyde().setGhostVulnerable(true);
                                    }else if(e instanceof  Inky){
                                        gameData.addElement(new Inky(gameData), pos.y(), pos.x());
                                        gameData.getInky().setGhostVulnerable(true);
                                    }else if(e instanceof  Pinky){
                                        gameData.addElement(new Inky(gameData), pos.y(), pos.x());
                                        gameData.getPinky().setGhostVulnerable(true);
                                    }
                                }
                            }

                            GameData.Position newPos = new GameData.Position(myPos.y() + dy, myPos.x() + dx);
                            gameData.setPositionOf(newPos, this);

                            score += 50;
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
