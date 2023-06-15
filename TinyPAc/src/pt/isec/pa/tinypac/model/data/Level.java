package pt.isec.pa.tinypac.model.data;

import com.googlecode.lanterna.input.KeyType;
import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.data.cell.*;
import pt.isec.pa.tinypac.model.data.mob.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Level {

    public record Position(int y, int x) {}

    private int height;
    private int width;
    Maze maze;

    //private static int levelNumber = 0;
    private int levelNumber;

    private boolean isLevelComplete = false;

    private static int counter = 0; //usar esta var estatica como multiplier do timer depois no evolve deste ficheiro fazer umif com multiplicadores diferentes para aquele instanceof daquele tipo de mob

    public Level(int levelNumber){
        //levelNumber++;

        this.levelNumber    = levelNumber;
        String filePath     = "files/Level10" + levelNumber + ".txt";
        this.maze           = setMaze(filePath);

    }

    //public void setScore(int num){this.score += num;}
    //public int getScore(){return this.score;}

    public void setLevelComplete(){this.isLevelComplete = true;}
    public boolean getLevelComplete(){return this.isLevelComplete;}

    public int getLevelNumber(){return levelNumber;}
    public Maze setMaze(String filePath) {

        try{
            char[][] auxMap;

            File file = new File(filePath);
            if(!file.exists())
                return null;
            Scanner sc = new Scanner(file);

            String firstLine = sc.nextLine();
            int height = 1;
            int width = firstLine.length();

            while(sc.hasNextLine()){
                height++;
                sc.nextLine();
            }

            this.height = height;
            this.width = width;

            sc.close();

            //ler as letras para o array
            auxMap = new char[height][width];
            sc = new Scanner(file);

            for(int h = 0; h < height; h++){
                String line = sc.nextLine();
                for(int w = 0; w < width; w++){
                    auxMap[h][w] = line.charAt(w);
                }
            }

            //Level auxLevel = new Level(/*levelNumber, */height, width);
            Maze auxMaze = new Maze(height, width);

            for(int h = 0; h < height; h++){
                for(int w = 0; w < width; w++){

                    char symbol = auxMap[h][w];
                    Element element = null;

                     element = switch(symbol){
                         case Wall.SYMBOL -> new Wall(this); //possivelmente vai ser necessario passar as coordenadas
                         case Warp.SYMBOL -> new Warp(this);
                         case FoodBall.SYMBOL -> new FoodBall(this);
                         case Fruit.SYMBOL -> new Fruit(this);
                         case TinyPac.SYMBOL -> new TinyPac(this); //cada uma destas classes tem que dar override ao getSymbol da IMazeElement
                         case PowerBall.SYMBOL -> new PowerBall(this);
                         case Portal.SYMBOL -> new Portal(this);
                         case GhostCave.SYMBOL -> new GhostCave(this);
                         //GHOSTS
                         case Blinky.SYMBOL -> new Blinky(this);
                         case Clyde.SYMBOL -> new Clyde(this);
                         case Inky.SYMBOL -> new Inky(this);
                         case Pinky.SYMBOL -> new Pinky(this);
                         default -> null;
                     };
                    auxMaze.set(h ,w, element);

                }
            }

            //GHOSTS SPAWN
                Thread timerThread1 = new Thread(() -> {
                    int seconds = 0;
                    while (seconds < 5) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        seconds++;
                    }

                    for(Level.Position pos : getCave()){
                        Element element = getElement(pos.y() - 2, pos.x()); //ver se o elemento é o Portal
                        if(element instanceof Portal){
                            addElement(new Blinky(this), pos.y(), pos.x());
                        }
                    }

                    System.out.println("Blinky spawnned");
                });
                timerThread1.start();

            /*Thread timerThread2 = new Thread(() -> {
                int seconds = 0;
                while (seconds < 7) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seconds++;
                }

                for(Level.Position pos : getCave()){
                    Element element = getElement(pos.y() - 2, pos.x()); //ver se o elemento é o Portal
                    if(element instanceof Portal){
                        addElement(new Clyde(this), pos.y(), pos.x());
                    }
                }

                System.out.println("Clyde spawnned");
            });
            timerThread3.start();*/

            /*Thread timerThread3 = new Thread(() -> {
                int seconds = 0;
                while (seconds < 9) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seconds++;
                }

                for(Level.Position pos : getCave()){
                    Element element = getElement(pos.y() - 2, pos.x()); //ver se o elemento é o Portal
                    if(element instanceof Portal){
                        addElement(new Inky(this), pos.y(), pos.x());
                    }
                }

                System.out.println("Inky spawnned");
            });
            timerThread4.start();*/

            /*Thread timerThread4 = new Thread(() -> {
                int seconds = 0;
                while (seconds < 9) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seconds++;
                }

                for(Level.Position pos : getCave()){
                    Element element = getElement(pos.y() - 2, pos.x()); //ver se o elemento é o Portal
                    if(element instanceof Portal){
                        addElement(new Pinky(this), pos.y(), pos.x());
                    }
                }

                System.out.println("Pinky spawnned");
            });
            timerThread4.start();*/

            sc.close();
            return auxMaze;

        }catch(FileNotFoundException e){
            System.out.println("File Not Found: " + filePath);
            return null;
        }

    }

    public void addElement(Element element, int y, int x){maze.set(y, x, element);}

    public char getElementAt(int y, int x){
        return maze.get(y, x).getSymbol();
    }

    public IMazeElement[][] getMazeWithElements() {
        IMazeElement[][] arr = new IMazeElement[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maze.get(y, x) instanceof Element) {
                    Element element = (Element) maze.get(y, x);
                    arr[y][x] = element;
                }
            }
        }
        return arr;
    }

    public TinyPac getTinyPac() {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y, x) instanceof Element element) {
                    if (element instanceof TinyPac){
                        return (TinyPac) element;
                    }
                }
        return null;
    }

    public Blinky getBlinky() {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y, x) instanceof Element element) {
                    if (element instanceof Blinky){
                        return (Blinky) element;
                    }
                }
        return null;
    }

    public List<Element> getGhosts(){

        List<Element> lst = new ArrayList<>();
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y, x) instanceof Element element) {
                    if ((element instanceof Blinky) || (element instanceof Clyde) || (element instanceof Inky) || (element instanceof Pinky)){
                        lst.add(element);
                    }
                }
        return lst;
    }

    public List<Position> getWarps(){

        List<Position> lst = new ArrayList<>();
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y, x) instanceof Element element) {
                    if (element instanceof Warp){
                        lst.add(new Position(y, x));
                    }
                }

        return lst;
    }

    public List<Position> getCave(){

        List<Position> lst = new ArrayList<>();
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y, x) instanceof Element element) {
                    if (element instanceof GhostCave){
                        lst.add(new Position(y, x));
                    }
                }

        return lst;
    }

    public Element getElement(int y,int x) {
        IMazeElement e = maze.get(y,x);
        if (e instanceof Element element)
            return element;
        return null;
    }

    public Position getPositionOf(Element element) {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) == element)
                    return new Position(y,x);
        return null;
    }

    public void setPositionOf(Position position, Element element) {
        maze.set(position.y(), position.x(), element);
    }

    public boolean onlyOneSpecies() {
        int n = 0;

        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) instanceof Element element) {
                    if (element instanceof TinyPac)
                        n++;
                    /*else if (organism instanceof Virus)
                        nr_virus++;*/
                }
        return (n==0);
    }

    public <T extends Element> List<Position> getElementNeighbors(int y, int x, Class<T> type) {
        List<Position> lst = new ArrayList<>();
        for (int yd = -1; yd <= 1; yd++) {
            for (int xd = -1; xd <= 1; xd++) {
                if (yd != 0 || xd != 0) {
                    var element = maze.get(y + yd, x + xd);
                    if (type.isInstance(element)) {
                        lst.add(new Position(y + yd, x + xd));
                    }
                }
            }
        }
        return lst;
    }

    public boolean evolve(KeyCode key) {
        int n = 0;

        List<Element> lst = new ArrayList<>();
        for(int y = 0; y < height;y++) {
            for (int x = 0; x < width; x++) {
                if (maze.get(y, x) instanceof Element element) {
                    lst.add(element);
                    if (element instanceof TinyPac)
                        //maze.set(y, x + 1, element);
                        n++;
                    /*else if (organism instanceof Virus)
                        nr_virus++;*/
                }
            }
        }

        Collections.shuffle(lst);
        for(var element : lst) {
            element.evolve(key);
        }
        return true;
    }

    public char[][] getMaze(){return this.maze.getMaze();}

}
