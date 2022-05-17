package it.polimi.ingsw.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientAppGUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientAppGUI.class.getResource("/fxml/setup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MainPage");
        stage.setScene(scene);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/Graphics/title.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
