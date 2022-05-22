package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import javafx.fxml.FXML;

public class LoadingController {

    private ClientAppGUI gui;

    @FXML
    public void startMatch() {

        System.out.println("you are in loading 1");
        while (gui.getClientGUI().isStartgame() == false){}
        System.out.println("you are in loading 2");
        gui.changeStage("GameBoard");

    }

    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
