package it.polimi.ingsw.client;

import it.polimi.ingsw.client.GUI.ClientGUI;
import it.polimi.ingsw.client.GUI.controllers.*;
import it.polimi.ingsw.model.gameboard.GameBoard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClientAppGUI extends Application {

    private Stage stage;

    private ClientGUI clientGUI;

    private MainMenuController mainMenuController;
    private FirstPlayerController firstPlayerController;
    private LoadingController loadingController;
    private NicknameController nicknameController;
    private GameBoardController gameboardController;

    private final Map<String, Scene> sceneMap = new HashMap<>();

    public static final String MENU = "MainMenu";
    public static final String LOAD = "Loading";
    public static final String NICKNAME = "Nickname";
    public static final String FIRST = "FirstPlayer";

    public static final String GAMEBOARD = "GameBoard";

    private boolean startGame = false;


    @Override
    public void init() throws Exception {
        FXMLLoader menu = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        Scene menuScene = new Scene(menu.load());
        mainMenuController = menu.getController();
        mainMenuController.setGui(this);

        FXMLLoader first = new FXMLLoader(getClass().getResource("/fxml/FirstPlayer.fxml"));
        Scene firstScene = new Scene(first.load());
        firstPlayerController = first.getController();
        firstPlayerController.setGui(this);

        FXMLLoader nickname = new FXMLLoader(getClass().getResource("/fxml/Nickname.fxml"));
        Scene nicknameScene = new Scene(nickname.load());
        nicknameController = nickname.getController();
        nicknameController.setGui(this);

        FXMLLoader loading = new FXMLLoader(getClass().getResource("/fxml/Loading.fxml"));
        Scene loadingScene = new Scene(loading.load());
        loadingController = loading.getController();
        loadingController.setGui(this);

        FXMLLoader gameboard = new FXMLLoader(getClass().getResource("/fxml/GameBoard.fxml"));
        Scene gameboardScene = new Scene(gameboard.load());
        gameboardController = gameboard.getController();
        gameboardController.setGui(this);

        /*
        FXMLLoader loading = new FXMLLoader(getClass().getResource(("/fxml/loading.fxml")));
        LoadingScene = new Scene(loading.load());

        FXMLLoader game = new FXMLLoader(getClass().getResource("/fxml/GameScene.fxml"));
        Scene gameScene = new Scene(game.load());
        gameSceneController = game.getController();
        gameSceneController.setGui(this);
        gameScene.getStylesheets().add(Objects.requireNonNull(GUI.class.getResource("/bootstrap3.css")).toExternalForm());
        */

        sceneMap.put(MENU, menuScene);
        sceneMap.put(NICKNAME, nicknameScene);
        sceneMap.put(LOAD, loadingScene);
        sceneMap.put(FIRST, firstScene);
        sceneMap.put(GAMEBOARD, gameboardScene);
    }



    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;
        stage.setTitle("Eriantys");
        stage.centerOnScreen();
        stage.setHeight(800);
        stage.setWidth(1200);
        stage.setResizable(false);
        //stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/graphics/inkwell.png"))));
        stage.setScene(sceneMap.get("MainMenu"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeStage(String newScene){
        Scene currentScene = sceneMap.get(newScene);
        stage.setScene(currentScene);
        stage.show();
    }

    public void setClientGUI(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
    }

    public ClientGUI getClientGUI() {
        return clientGUI;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }
}

