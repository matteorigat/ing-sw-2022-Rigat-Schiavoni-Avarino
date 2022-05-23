package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import javafx.fxml.FXML;

public class LoadingController {

    private ClientAppGUI gui;

    @FXML
    public void startMatch() {

        if (gui.getClientGUI().isStartgame())
            gui.changeStage("GameBoard");

    }

    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
