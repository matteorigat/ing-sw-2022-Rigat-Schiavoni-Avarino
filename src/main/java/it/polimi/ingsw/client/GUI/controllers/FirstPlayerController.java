package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import javafx.fxml.FXML;

public class FirstPlayerController {

    private ClientAppGUI gui;

    String numPlayers;

    String expertMode = "n";

    @FXML
    protected void onTwo() {
        numPlayers = "2";
    }

    @FXML
    protected void onThree() {
        numPlayers = "3";
    }

    @FXML
    protected void onCheck() {
        if(expertMode == "n")
            expertMode = "y";
        else
            expertMode = "n";
    }

    @FXML
    protected void onButtonClick() {

        if(numPlayers.equals(""))
            return;

        gui.getClientGUI().asyncWriteToSocket(numPlayers);
        gui.getClientGUI().asyncWriteToSocket(expertMode);

    }

    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
