package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.client.GUI.ClientGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GameBoardController {

    private ClientAppGUI gui;

    @FXML private TextField ip;
    @FXML private TextField port;


    @FXML
    protected void onButtonClick() {


        gui.changeStage("Nickname");
    }


    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
