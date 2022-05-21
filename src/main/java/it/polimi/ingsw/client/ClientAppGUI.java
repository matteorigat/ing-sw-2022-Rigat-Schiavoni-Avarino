package it.polimi.ingsw.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientAppGUI extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientAppGUI.class.getResource("/fxml/setup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MainPage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void changeStage(String newScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientAppGUI.class.getResource("/fxml/firstPlayer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
