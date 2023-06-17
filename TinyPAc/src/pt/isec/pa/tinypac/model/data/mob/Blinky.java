package pt.isec.pa.tinypac.model.data.mob;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.data.cell.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blinky extends Element implements Serializable {

    public record Direction(int dy, int dx) implements Serializable{}
    private List<Direction> possibleDirections;
    private Direction direction;

    private List<GameData.Position> positionHistory;
    private int backIteration;

    public static final char SYMBOL = 'B';
    private boolean insideCave = true;
    private boolean ghostVulnerable = false;

    public Blinky(GameData gameData){
        super(gameData);
        positionHistory         = new ArrayList<>();
        possibleDirections      = new ArrayList<>();
        possibleDirections.add(new Direction(0, -1)); // Cima
        possibleDirections.add(new Direction(0, 1));  // Baixo
        possibleDirections.add(new Direction(-1, 0)); // Esquerda
        possibleDirections.add(new Direction(1, 0));  // Direita

        // Inicialize a direção inicial com um valor válido
        direction               = possibleDirections.get(0);
    }
    @Override
    public boolean getGhostVulnerable(){return this.ghostVulnerable;}
    @Override
    public void setGhostVulnerable(boolean ghostVulnerable){ this.ghostVulnerable = ghostVulnerable;}

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

    public boolean isValidPosition(int y, int x) {
        Element element = gameData.getElement(y, x);
        return !(element instanceof Wall);
    }

    @Override
    public boolean eat(int y, int x){

         if(!ghostVulnerable) {
             int currentLifes = gameData.getTinyPac().getLifes();
             gameData.getTinyPac().setLifes(currentLifes - 1);
             System.out.println("\nComi o pacman");
             System.out.println("\nVidas Pacman: " + gameData.getTinyPac().getLifes());
         }

        return false;

    }

    @Override
    public void evolve(KeyCode key) {

        GameData.Position myPos = gameData.getPositionOf(this);
        int dx = 0;
        int dy = 0;

        // Verifica se Blinky está dentro da GhostCave
        if (insideCave) {
            if(gameData.getTinyPac().isOP())
                ghostVulnerable = true;

            positionHistory.clear(); //da clear do array
            // Verifica se há uma saída disponível
            if (isValidPosition(myPos.y() - 1, myPos.x())) {
                // Remove a GhostCave da posição atual
                gameData.addElement(new GhostCave(gameData), myPos.y(), myPos.x());

                // Verifica se há uma FoodBall na posicao atual, se houver sai da cave, se nao houver vai para uma nova posicao
                if (gameData.getElement(myPos.y() - 1, myPos.x()) instanceof FoodBall || gameData.getElement(myPos.y() - 1, myPos.x()) instanceof EmptyCell) {
                    gameData.addElement(new Portal(gameData), myPos.y(), myPos.x());

                    // Move Blinky para a nova posição
                    GameData.Position nnewPos = new GameData.Position(myPos.y() - 1, myPos.x());
                    gameData.setPositionOf(nnewPos, this);

                    insideCave = false; // Blinky saiu da GhostCave
                    System.out.println("sai da cave");
                }

                // Define a nova posição acima da GhostCave
                GameData.Position newPos = new GameData.Position(myPos.y() - 1, myPos.x());
                gameData.setPositionOf(newPos, this);


            }
        }
        else {

            myPos = gameData.getPositionOf(this);
            dx = direction.dx;
            dy = direction.dy;

            // Verifica se há uma parede na próxima posição
            if (!isValidPosition(myPos.y() + dy, myPos.x() + dx)) {
                // Verifica se há uma interseção (mais de uma direção possível)
                int directionsCount = 0;
                Direction validDirection = null;

                for (Direction dir : possibleDirections) {
                    if (isValidPosition(myPos.y() + dir.dy, myPos.x() + dir.dx)) {
                        Element nextElement = gameData.getElement(myPos.y() + dir.dy, myPos.x() + dir.dx);

                        // Verifica se a próxima posição não é uma parede
                        if (!(nextElement instanceof Wall)) {
                            directionsCount++;
                            validDirection = dir;
                        }
                    }
                }

                // Se há apenas uma direção possível sem uma parede, escolhe essa direção
                if (directionsCount == 1 && validDirection != null) {
                    dx = validDirection.dx;
                    dy = validDirection.dy;
                    direction = validDirection;
                } else if (directionsCount > 1) { // Se é uma interseção, seleciona uma direção aleatória (exceto voltar para trás)
                    List<Direction> availableDirections = new ArrayList<>();

                    for (Direction dir : possibleDirections) {
                        if (isValidPosition(myPos.y() + dir.dy, myPos.x() + dir.dx) && (dx != -dir.dx || dy != -dir.dy)) {
                            availableDirections.add(dir);
                        }
                    }

                    if (!availableDirections.isEmpty()) {
                        Collections.shuffle(availableDirections);
                        Direction newDirection = availableDirections.get(0);
                        dx = newDirection.dx;
                        dy = newDirection.dy;
                        direction = newDirection;
                    }
                }
            }

            // Move Blinky para a nova posição
            GameData.Position newPos = new GameData.Position(myPos.y() + dy, myPos.x() + dx);

            if (isValidPosition(newPos.y(), newPos.x())) {
                // Verifica se a próxima posição não é uma parede
                if (!(gameData.getElement(newPos.y(), newPos.x()) instanceof Wall)) {

                    if(ghostVulnerable){

                        if(positionHistory.size() == 0){
                            //myPos = level.getPositionOf(this);
                            if(gameData.getElement(newPos.y(), newPos.x()) instanceof FoodBall)
                                gameData.addElement(new FoodBall(gameData), myPos.y(), myPos.x());
                            else if(gameData.getElement(newPos.y(), newPos.x()) instanceof PowerBall)
                                gameData.addElement(new PowerBall(gameData), myPos.y(), myPos.x());
                            else if(gameData.getElement(newPos.y(), newPos.x()) instanceof EmptyCell)
                                gameData.addElement(new EmptyCell(gameData), myPos.y(), myPos.x());
                            else if(gameData.getElement(newPos.y(), newPos.x()) instanceof Fruit)
                                gameData.addElement(new Fruit(gameData), myPos.y(), myPos.x());
                            else if(checkWarp(myPos.y() + dy, myPos.x() + dx)){
                                //Level.Position entryWarpPosition = new Level.Position(myPos.y(), myPos.x());
                                for(GameData.Position pos: gameData.getWarps()){

                                    if(pos.y() != myPos.y() + dy || pos.x() != myPos.x() + dx){
                                        gameData.addElement(new EmptyCell(gameData), myPos.y(), myPos.x());

                                        GameData.Position nnewPos = new GameData.Position(pos.y() + dy, pos.x() + dx);
                                        gameData.setPositionOf(nnewPos, this);
                                    }
                                }
                            }

                            // Move Blinky para a nova posição
                            GameData.Position nnewPos = new GameData.Position(newPos.y(), newPos.x());
                            gameData.setPositionOf(nnewPos, this);
                            return;
                        }

                        GameData.Position backPosition = new GameData.Position(positionHistory.get(positionHistory.size() - 1).y(), positionHistory.get(positionHistory.size() - 1).x());

                        gameData.addElement(new FoodBall(gameData), myPos.y(), myPos.x());
                        gameData.setPositionOf(backPosition, this);

                        positionHistory.remove(positionHistory.size() - 1);

                        return;

                    }


                    if(gameData.getElement(newPos.y(), newPos.x()) instanceof TinyPac){
                        if(!ghostVulnerable){
                            gameData.setPositionOf(myPos, gameData.getTinyPac());
                            gameData.addElement(new EmptyCell(gameData), myPos.y(), myPos.x());

                            eat( myPos.y(), myPos.x());

                            // Move Blinky para a nova posição
                            GameData.Position nnewPos = new GameData.Position(newPos.y() + dy, newPos.x() + dx);
                            if(isValidPosition(nnewPos.y(), nnewPos.x())){
                                gameData.setPositionOf(nnewPos, this);
                                positionHistory.add(myPos);
                                //level.getTinyPac().setLostLife(true);
                                System.out.println("Comi o corno");
                            }
                            //FAZER
                            else { //quando ele come o pacman mas na posicao a frente dele esta uma parede
                                System.out.println("parede");

                                // Move Blinky para a nova posição
                                GameData.Position nextPos = new GameData.Position(myPos.y() + dy, myPos.x() + dx);
                                gameData.setPositionOf(nextPos, this);
                            }

                        }else{
                            //ghosts estao vulneraveis -> fazem o movimento contrario
                        }

                    }else if (gameData.getElement(newPos.y(), newPos.x()) instanceof FoodBall)  {
                        gameData.addElement(new FoodBall(gameData), myPos.y(), myPos.x());
                        // Move Blinky para a nova posição
                        GameData.Position nnewPos = new GameData.Position(newPos.y(), newPos.x());
                        gameData.setPositionOf(nnewPos, this);
                        positionHistory.add(myPos);
                        System.out.println("Blinky moveu-se para " + newPos);
                    }else if (gameData.getElement(newPos.y(), newPos.x()) instanceof Fruit) {
                        gameData.addElement(new Fruit(gameData), myPos.y(), myPos.x());
                        // Move Blinky para a nova posição
                        GameData.Position nnewPos = new GameData.Position(newPos.y(), newPos.x());
                        gameData.setPositionOf(nnewPos, this);
                        positionHistory.add(myPos);
                        System.out.println("Blinky moveu-se para " + newPos);
                    }else if(gameData.getElement(newPos.y(), newPos.x()) instanceof EmptyCell){
                        gameData.addElement(new EmptyCell(gameData), myPos.y(), myPos.x());
                        // Move Blinky para a nova posição
                        GameData.Position nnewPos = new GameData.Position(newPos.y(), newPos.x());
                        gameData.setPositionOf(nnewPos, this);
                        positionHistory.add(myPos);
                        System.out.println("Blinky moveu-se para " + newPos);
                    }else if(gameData.getElement(newPos.y(), newPos.x()) instanceof PowerBall){
                        gameData.addElement(new PowerBall(gameData), myPos.y(), myPos.x());
                        // Move Blinky para a nova posição
                        GameData.Position nnewPos = new GameData.Position(newPos.y(), newPos.x());
                        gameData.setPositionOf(nnewPos, this);
                        positionHistory.add(myPos);
                        System.out.println("Blinky moveu-se para " + newPos);
                    }else if(checkWarp(myPos.y() + dy, myPos.x() + dx)){
                        //Level.Position entryWarpPosition = new Level.Position(myPos.y(), myPos.x());
                        for(GameData.Position pos: gameData.getWarps()){

                            if(pos.y() != myPos.y() + dy || pos.x() != myPos.x() + dx){
                                gameData.addElement(new EmptyCell(gameData), myPos.y(), myPos.x());
                                //System.out.println("\nnovo warp na posicao " + myPos);

                                GameData.Position nnewPos = new GameData.Position(pos.y() + dy, pos.x() + dx);
                                gameData.setPositionOf(nnewPos, this);
                                positionHistory.add(myPos);
                                //System.out.println("\n warping to " + newPos);

                            }
                        }
                    }

                }
            }


        }

    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }
}
