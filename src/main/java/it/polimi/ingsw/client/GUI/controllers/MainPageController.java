package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.GUI.ClientGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainPageController {

    @FXML private TextField ip;
    @FXML private TextField port;

    public void start() {
        ClientGUI clientGUI = new ClientGUI(ip.getText(), Integer.parseInt(port.getText()));
        try {
            clientGUI.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
