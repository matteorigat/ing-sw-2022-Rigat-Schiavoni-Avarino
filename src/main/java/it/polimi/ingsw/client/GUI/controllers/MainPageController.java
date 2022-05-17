package it.polimi.ingsw.client.GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainPageController {

    @FXML private TextField ip;
    @FXML private TextField port;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("ip: " + ip.getText() + "\nport: " + port.getText());
    }
}
