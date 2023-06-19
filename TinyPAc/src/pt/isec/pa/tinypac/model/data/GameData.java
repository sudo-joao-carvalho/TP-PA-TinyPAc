package pt.isec.pa.tinypac.model.data;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.data.cell.*;
import pt.isec.pa.tinypac.model.data.mob.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameData implements Serializable{

    public record Position(int y, int x) implements Serializable{}

    private int height;
    private int width;
    Maze maze;
    private int levelNumber;

    private boolean isLevelComplete = false;

    TopFive top5;

    private int numOfFood;

    private int ballsEaten = 0;
    private static int counter = 0; //usar esta var estatica como multiplier do timer depois no evolve deste ficheiro fazer umif com multiplicadores diferentes para aquele instanceof daquele tipo de mob

    /**
     *  Constructor
     */
    public GameData(int levelNumber){

        this.levelNumber    = levelNumber;
        String filePath     = "files/Level10" + levelNumber + ".txt";
        this.maze           = setMaze(filePath);
        this.top5 = new TopFive();
        this.numOfFood = setNumOfFood();

    }

    /**
     *  Sets isLevelComplete boolean value
     */
    public void setLevelComplete(){this.isLevelComplete = true;}
    /**
     *  Returns isLevelComplete boolean value
     */
    public boolean getLevelComplete(){return this.isLevelComplete;}

    /**
     *  Returns levelNumber value
     */
    public int getLevelNumber(){return levelNumber;}

    /**
     *  Returns levelNumber value
     */
    public void setLevelNumber(int levelNumber){this.levelNumber = levelNumber;}

    /**
     *  Sets Maze with IMazeElements
     */
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

                    for(GameData.Position pos : getCave()){
                        Element element = getElement(pos.y() - 2, pos.x()); //ver se o elemento é o Portal
                        if(element instanceof Portal){
                            addElement(new Blinky(this), pos.y(), pos.x());
                        }
                    }

                });
                timerThread1.start();

            /*Thread timerThread2 = new Thread(() -> {
                int seconds = 0;
                while (seconds < 8) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seconds++;
                }

                for(GameData.Position pos : getCave()){
                    Element element = getElement(pos.y() - 2, pos.x()); //ver se o elemento é o Portal
                    if(element instanceof Portal){
                        addElement(new Clyde(this), pos.y(), pos.x());
                    }
                }

                System.out.println("Clyde spawnned");
            });
            timerThread2.start();*/

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
            timerThread3.start();*/

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

    /**
     *  Sets ballsEaten amount
     */
    public void setBallsEaten(int ballsEaten) {
        this.ballsEaten = ballsEaten;
    }

    /**
     *  Returns ballsEaten amount
     */
    public int getBallsEaten() {
        return ballsEaten;
    }

    /**
     *  Return TopFive
     */
    public TopFive getTop5(){return this.top5;}

    /**
     *  Sets TopFive
     */
    public void setTop5(TopFive top5){this.top5 = top5;}

    /**
     *  Adds an element to the maze
     */
    public void addElement(Element element, int y, int x){maze.set(y, x, element);}

    /**
     *  Returns an element at the given Position
     */
    public char getElementAt(int y, int x){
        return maze.get(y, x).getSymbol();
    }

    /**
     *  Returns the Maze with elements (characters)
     */
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

    /**
     *  Returns TinyPac Element
     */
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

    /**
     *  Returns Blinky Element
     */
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

    /**
     *  Returns Clyde Element
     */
    public Clyde getClyde() {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y, x) instanceof Element element) {
                    if (element instanceof Clyde){
                        return (Clyde) element;
                    }
                }
        return null;
    }

    /**
     *  Returns Inky Element
     */
    public Inky getInky() {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y, x) instanceof Element element) {
                    if (element instanceof Inky){
                        return (Inky) element;
                    }
                }
        return null;
    }

    /**
     *  Returns Pinky Element
     */
    public Pinky getPinky() {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y, x) instanceof Element element) {
                    if (element instanceof Pinky){
                        return (Pinky) element;
                    }
                }
        return null;
    }

    /**
     *  Returns a List with Ghosts Elements
     */
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

    /**
     *  Returns a List with Warp Element
     */
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

    /**
     *  Returns a List with Cave Positions
     */
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

    /**
     *  Returns Fruit Element
     */
    public Fruit getFruit(int y,int x) {
        IMazeElement e = maze.get(y,x);
        if (e instanceof Element element)
            if(e instanceof Fruit)
                return (Fruit) element;
        return null;
    }

    /**
     *  Return TinyPac Element
     */
    public Element getElement(int y,int x) {
        IMazeElement e = maze.get(y,x);
        if (e instanceof Element element)
            return element;
        return null;
    }

    /**
     *  Return the Position of an Element
     */
    public Position getPositionOf(Element element) {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) == element)
                    return new Position(y,x);
        return null;
    }

    /**
     *  Increments the variable numOfFood that holds the number o Food in the maze
     */
    public int setNumOfFood(){
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) instanceof Element element)
                    if(element instanceof FoodBall || element instanceof PowerBall){
                        this.numOfFood++;
                    }

        return this.numOfFood;

    }

    /**
     *  Decrements the varibale numOfFood
     */
    public void eatFood(){
        this.numOfFood--;
    }

    /**
     *  Return numOfFood value
     */
    public int getNumOfFood(){return this.numOfFood;}

    /**
     *  Sets the Position of an Element in the Maze
     */
    public void setPositionOf(Position position, Element element) {
        maze.set(position.y(), position.x(), element);
    }

    /**
     *  Returns the neighbors of an Element
     */
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


    /**
     *  Calls the evolve functions from the Elements
     */
    public boolean evolve(KeyCode key) {

        List<Element> lst = new ArrayList<>();
        for(int y = 0; y < height;y++) {
            for (int x = 0; x < width; x++) {
                if (maze.get(y, x) instanceof Element element) {
                    lst.add(element);
                }
            }
        }

        Collections.shuffle(lst);
        for(var element : lst) {
            element.evolve(key);
        }
        return true;
    }

    /**
     *  Returns Maze (characters)
     */
    public char[][] getMaze(){return this.maze.getMaze();}

}
