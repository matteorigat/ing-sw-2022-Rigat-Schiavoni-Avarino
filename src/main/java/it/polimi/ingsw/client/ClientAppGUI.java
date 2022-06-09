package it.polimi.ingsw.client;

import it.polimi.ingsw.client.GUI.ClientGUI;
import it.polimi.ingsw.client.GUI.controllers.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientAppGUI extends Application {

    private Stage stage;
    private ClientGUI clientGUI;

    private MainMenuController mainMenuController;
    private FirstPlayerController firstPlayerController;
    private NicknameController nicknameController;
    private GameBoardController gameboardController;

    private final Map<String, Scene> sceneMap = new HashMap<>();

    private String currentFXML;
    public static final String MENU = "MainMenu";
    public static final String LOAD = "Loading";
    public static final String NICKNAME = "Nickname";
    public static final String FIRST = "FirstPlayer";
    public static final String GAMEBOARD = "GameBoard";


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

        FXMLLoader gameboard = new FXMLLoader(getClass().getResource("/fxml/GameBoard.fxml"));
        Scene gameboardScene = new Scene(gameboard.load());
        gameboardController = gameboard.getController();
        gameboardController.setGui(this);


        /*
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

         /* Image i = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/pointer.png")));
  ImageCursor ic = new ImageCursor(i);
  menuScene.setCursor(ic);
  nicknameScene.setCursor(ic);
  loadingScene.setCursor(ic);
  firstScene.setCursor(ic);
  gameboardScene.setCursor(ic);
//  menuScene.setCursor(Cursor.DEFAULT); //per ripristinare default */

    }

    public String getCurrentFXML() {
        return currentFXML;
    }

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.setTitle("Eriantys");
        stage.centerOnScreen();
        stage.setResizable(false);
        //stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/graphics/inkwell.png"))));
        stage.setScene(sceneMap.get("MainMenu"));
        stage.show();
        currentFXML = "MainMenu";
    }

    public static void main(String[] args) {
        launch();
    }

    public void changeStage(String newScene){
        currentFXML = newScene;
        Scene currentScene = sceneMap.get(newScene);
        stage.setScene(currentScene);
        if(newScene.equals("GameBoard")){
            stage.setResizable(true);
        }
        stage.show();
    }

    public void setClientGUI(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
    }

    public ClientGUI getClientGUI() {
        return clientGUI;
    }

    public NicknameController getNicknameController() {
        return nicknameController;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public GameBoardController getGameboardController() {
        return gameboardController;
    }

    public Stage getStage() {
        return stage;
    }
}

