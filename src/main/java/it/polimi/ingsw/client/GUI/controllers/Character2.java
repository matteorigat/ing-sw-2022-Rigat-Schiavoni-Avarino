package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.client.GUI.ClientGUI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Character2 {

    private ClientAppGUI gui;

    @FXML private Label effect;

    @FXML
    protected void onButtonClick() {
        Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,2"));
    }


    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
