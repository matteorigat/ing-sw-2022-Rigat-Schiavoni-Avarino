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
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientAppGUI extends Application {

    private Stage stage;
    private ClientGUI clientGUI;

    private AudioClip laserPlayer;

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

        URL laserResource = getClass().getResource("/Graphics/The Lord of the Rings.mp3");
        laserPlayer = new AudioClip(Objects.requireNonNull(laserResource).toString());
        laserPlayer.setCycleCount(10000);
        laserPlayer.play();

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




        sceneMap.put(MENU, menuScene);
        sceneMap.put(NICKNAME, nicknameScene);
        sceneMap.put(LOAD, loadingScene);
        sceneMap.put(FIRST, firstScene);
        sceneMap.put(GAMEBOARD, gameboardScene);


    }

    public String getCurrentFXML() {
        return currentFXML;
    }

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setTitle("Eriantys");
        stage.centerOnScreen();
        stage.setResizable(false);
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


    public void changeSound(){
        if(laserPlayer.isPlaying()){
            laserPlayer.stop();
            nicknameController.sound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/mute.png"))));
            mainMenuController.sound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/mute.png"))));
            firstPlayerController.sound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/mute.png"))));
            gameboardController.sound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/mute.png"))));
        } else {
            laserPlayer.play();
            nicknameController.sound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/volume.png"))));
            mainMenuController.sound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/volume.png"))));
            firstPlayerController.sound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/volume.png"))));
            gameboardController.sound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/volume.png"))));
        }
    }

    public void closeConnection(String message){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(message));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle("Connection closed");
        dialog.show();
    }
}

