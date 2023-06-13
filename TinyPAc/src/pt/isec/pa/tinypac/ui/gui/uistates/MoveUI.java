package pt.isec.pa.tinypac.ui.gui.uistates;

import com.googlecode.lanterna.input.KeyType;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameContextManager;
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

        this.setStyle("-fx-background-color: black;");

        mazePane = new GridPane();
        this.setCenter(mazePane);

        sidebar = new VBox();
        sidebar.setPadding(new Insets(20));
        sidebar.setSpacing(10);
        sidebar.setStyle("-fx-background-color: gray;");

        Label livesLabel = new Label("Lifes: ");
        Label scoreLabel = new Label("Score: ");
        Label levelLabel = new Label("Level: ");

        sidebar.getChildren().addAll(livesLabel, scoreLabel, levelLabel);

        this.setRight(sidebar);
    }
    private void registerHandlers() {
        gameCManager.addPropertyChangeListener(evt -> { update(); });

        setOnKeyPressed( event -> {
            if(event.getCode() == KeyCode.KP_RIGHT){
                gameCManager.retrieveKey(KeyType.ArrowRight);
            }
            if(event.getCode() == KeyCode.KP_LEFT){
                gameCManager.retrieveKey(KeyType.ArrowLeft);
            }
            if(event.getCode() == KeyCode.KP_UP){
                gameCManager.retrieveKey(KeyType.ArrowUp);
            }
            if(event.getCode() == KeyCode.KP_DOWN){
                gameCManager.retrieveKey(KeyType.ArrowDown);
            }
        });
    }


    private void update() {
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
        int lives = gameCManager.getLevel().getTinyPac().getLifes();
        int score = TinyPac.SCORE;
        int level = gameCManager.getLevel().getLevelNumber();

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
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);

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
