package pt.isec.pa.tinypac.model.data.mob;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import com.googlecode.lanterna.input.KeyStroke;
import pt.isec.pa.tinypac.model.data.cell.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Blinky extends Element {

    public record Direction(int dy, int dx) {}
    private List<Direction> possibleDirections;
    private Direction direction;

    public static final char SYMBOL = 'B';
    private boolean insideCave = true;

    private boolean ghostVulnerable = false;

    public Blinky(Level level){
        super(level);
        possibleDirections = new ArrayList<>();
        possibleDirections.add(new Direction(0, -1)); // Cima
        possibleDirections.add(new Direction(0, 1));  // Baixo
        possibleDirections.add(new Direction(-1, 0)); // Esquerda
        possibleDirections.add(new Direction(1, 0));  // Direita

        // Inicialize a direção inicial com um valor válido
        direction = possibleDirections.get(0);
    }
    @Override
    public boolean getGhostVulnerable(){return this.ghostVulnerable;}
    @Override
    public void setGhostVulnerable(boolean ghostVulnerable){ this.ghostVulnerable = ghostVulnerable;}

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

    public boolean isValidPosition(int y, int x) {
        Element element = level.getElement(y, x);
        return !(element instanceof Wall);
    }

    /*@Override
    public boolean eat(int y, int x){
        Element element = level.getElement(y, x);

        if(!ghostVulnerable){
            if(element instanceof TinyPac){
                level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                Level.Position newPos = new Level.Position(pos.y(), pos.x());
                level.setPositionOf(newPos, this);
            }
        }



    }*/

    @Override
    public void evolve(KeyType key) {
        /*Thread timerThread = new Thread(() -> {
            int seconds = 0;
            while (seconds < 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds++;
            }
        });*/

        Level.Position myPos = level.getPositionOf(this);
        int dx = 0;
        int dy = 0;

        // Verifica se Blinky está dentro da GhostCave
        if (insideCave) {
            // Verifica se há uma saída disponível
            if (isValidPosition(myPos.y() - 1, myPos.x())) {
                // Remove a GhostCave da posição atual
                level.addElement(new GhostCave(level), myPos.y(), myPos.x());

                // Verifica se há uma FoodBall na posicao atual, se houver sai da cave, se nao houver vai para uma nova posicao
                if (level.getElement(myPos.y() - 1, myPos.x()) instanceof FoodBall || level.getElement(myPos.y() - 1, myPos.x()) instanceof EmptyCell) {
                    // Move Blinky para a nova posição
                    Level.Position nnewPos = new Level.Position(myPos.y() - 1, myPos.x());
                    level.setPositionOf(nnewPos, this);

                    insideCave = false; // Blinky saiu da GhostCave
                    System.out.println("sai da cave");
                }

                // Define a nova posição acima da GhostCave
                Level.Position newPos = new Level.Position(myPos.y() - 1, myPos.x());
                level.setPositionOf(newPos, this);

            }
        }
        else {
            myPos = level.getPositionOf(this);
            dx = direction.dx;
            dy = direction.dy;

            // Verifica se há uma parede na próxima posição
            if (!isValidPosition(myPos.y() + dy, myPos.x() + dx)) {
                // Verifica se há uma interseção (mais de uma direção possível)
                int directionsCount = 0;
                Direction validDirection = null;

                for (Direction dir : possibleDirections) {
                    if (isValidPosition(myPos.y() + dir.dy, myPos.x() + dir.dx)) {
                        Element nextElement = level.getElement(myPos.y() + dir.dy, myPos.x() + dir.dx);

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
            Level.Position newPos = new Level.Position(myPos.y() + dy, myPos.x() + dx);

            if (isValidPosition(newPos.y(), newPos.x())) {
                // Verifica se a próxima posição não é uma parede
                if (!(level.getElement(newPos.y(), newPos.x()) instanceof Wall)) {

                    // Verifica se há uma FoodBall na posicao atual
                    if (level.getElement(newPos.y(), newPos.x()) instanceof FoodBall)  {
                        level.addElement(new FoodBall(level), myPos.y(), myPos.x());
                        // Move Blinky para a nova posição
                        Level.Position nnewPos = new Level.Position(newPos.y(), newPos.x());
                        level.setPositionOf(nnewPos, this);
                        System.out.println("Blinky moveu-se para " + newPos);
                    }else if(level.getElement(newPos.y(), newPos.x()) instanceof EmptyCell){
                        level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
                        // Move Blinky para a nova posição
                        Level.Position nnewPos = new Level.Position(newPos.y(), newPos.x());
                        level.setPositionOf(nnewPos, this);
                        System.out.println("Blinky moveu-se para " + newPos);
                    }else if(level.getElement(newPos.y(), newPos.x()) instanceof PowerBall){
                        level.addElement(new PowerBall(level), myPos.y(), myPos.x());
                        // Move Blinky para a nova posição
                        Level.Position nnewPos = new Level.Position(newPos.y(), newPos.x());
                        level.setPositionOf(nnewPos, this);
                        System.out.println("Blinky moveu-se para " + newPos);
                    }else if(checkWarp(myPos.y() + dy, myPos.x() + dx)){
                        //Level.Position entryWarpPosition = new Level.Position(myPos.y(), myPos.x());
                        for(Level.Position pos: level.getWarps()){

                            if(pos.y() != myPos.y() + dy || pos.x() != myPos.x() + dx){
                                level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
                                //System.out.println("\nnovo warp na posicao " + myPos);

                                Level.Position nnewPos = new Level.Position(pos.y() + dy, pos.x() + dx);
                                level.setPositionOf(nnewPos, this);
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
