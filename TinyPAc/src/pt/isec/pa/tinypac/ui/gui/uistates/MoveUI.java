package pt.isec.pa.tinypac.ui.gui.uistates;

import com.googlecode.lanterna.input.KeyType;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.cell.*;
import pt.isec.pa.tinypac.model.data.mob.*;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class MoveUI extends BorderPane {

    GameContextManager gameCManager;

    private GridPane mazePane;
    private VBox sidebar;

    public MoveUI(GameContextManager gameCManager) {
        this.gameCManager = gameCManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        //this.setStyle("-fx-background-color: black;");

        mazePane = new GridPane();
        mazePane.setStyle("-fx-background-color: black;");
        this.setCenter(mazePane);

        sidebar = new VBox();
        sidebar.setPadding(new Insets(20));
        sidebar.setSpacing(10);
        sidebar.setStyle("-fx-background-color: black; -fx-border-color: orange; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold; -fx-padding: 10 10 10 10;");
        sidebar.setPrefWidth(500);
        sidebar.setPrefHeight(800);

        this.setAlignment(mazePane, Pos.CENTER);

        Label livesLabel = new Label("Lifes: ");
        livesLabel.setStyle("-fx-text-fill: white;");
        Label scoreLabel = new Label("Score: ");
        scoreLabel.setStyle("-fx-text-fill: white;");
        Label levelLabel = new Label("Level: ");
        levelLabel.setStyle("-fx-text-fill: white;");

        sidebar.getChildren().addAll(livesLabel, scoreLabel, levelLabel);

        this.setRight(sidebar);
    }

    /** JAVADOC
     *
     */
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> {
            //gameCManager.evolve();
            update();
        });
        setOnKeyPressed(t);
    }

    EventHandler<KeyEvent> t = new EventHandler<>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.RIGHT){
                gameCManager.retrieveKey(KeyCode.RIGHT);
            }
            if(event.getCode() == KeyCode.LEFT){
                gameCManager.retrieveKey(KeyCode.LEFT);
            }
            if(event.getCode() == KeyCode.UP){
                gameCManager.retrieveKey(KeyCode.UP);
            }
            if(event.getCode() == KeyCode.DOWN){
                gameCManager.retrieveKey(KeyCode.DOWN);
            }
            if(event.getCode() == KeyCode.ESCAPE){
                gameCManager.pause();
            }
            gameCManager.evolve();
            System.out.println(gameCManager.getState());
            //update();
        }
    };

    private void update() {
        //this.requestFocus();
        if (gameCManager.getState() == EMobsState.MOVE) {
            this.setVisible(true);
            updateSidebar();
            updateMazePane();

        } else {
            this.setVisible(false);
        }
    }

    private void updateSidebar() {
        // Atualize as informações da barra lateral com os dados relevantes, como vidas, pontuação e nível
        int lives = gameCManager.getLifes();
        int score = gameCManager.getScore();
        int level = gameCManager.getLevelNumber();

        Label livesLabel = (Label) sidebar.getChildren().get(0);
        livesLabel.setText("Lifes: " + lives);

        Label scoreLabel = (Label) sidebar.getChildren().get(1);
        scoreLabel.setText("Score: " + score);

        Label levelLabel = (Label) sidebar.getChildren().get(2);
        levelLabel.setText("Level: " + level);
    }


    private void updateMazePane() {
        mazePane.getChildren().clear(); // Limpa o pane existente

        IMazeElement[][] maze = gameCManager.getMazeWithElements(); // Obtém a estrutura do labirinto

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                IMazeElement element = maze[row][col];

                ImageView imageView = createImageView(element); // Cria um ImageView correspondente ao elemento
                mazePane.add(imageView, col, row); // Adiciona o ImageView ao GridPane
            }
        }
    }

    private ImageView createImageView(IMazeElement element) {
        Image image = getImageForElement(element); // Obtém a imagem correspondente ao elemento

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        return imageView;
    }

    private Image getImageForElement(IMazeElement element) {
        // Defina a lógica para obter a imagem correspondente ao elemento
        // Você pode usar uma estrutura de switch/case ou um mapa para associar os elementos às imagens

        if (element instanceof Wall) {
            return ImageManager.getImage("wall.png");
        } else if (element instanceof Warp) {
            return ImageManager.getImage("background.png");
        } else if (element instanceof PowerBall) {
            return ImageManager.getImage("powerball.png");
        } else if(element instanceof Portal){
            return ImageManager.getImage("background.png");
        }else if(element instanceof GhostCave){
            return ImageManager.getImage("background.png");
        }else if(element instanceof Fruit){
            return ImageManager.getImage("fruit.png");
        }else if(element instanceof FoodBall){
            return ImageManager.getImage("foodball.png");
        }else if(element instanceof EmptyCell){
            return ImageManager.getImage("background.png");
        }else if(element instanceof Blinky){
            return ImageManager.getImage("blinky.png");
        }else if(element instanceof Clyde){
            return ImageManager.getImage("clyde.png");
        }else if(element instanceof Inky){
            return ImageManager.getImage("inky.png");
        }else if(element instanceof Pinky){
            return ImageManager.getImage("pinky.png");
        }else if(element instanceof TinyPac){
            return ImageManager.getImage("pacman.png");
        }else return null;
    }
}
