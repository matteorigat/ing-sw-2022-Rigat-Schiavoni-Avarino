package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Character3 {


    private ClientAppGUI gui;

    @FXML
    private Label effect;

    @FXML
    protected void onButtonClick() {
        Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,3"));
    }


    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }

}


