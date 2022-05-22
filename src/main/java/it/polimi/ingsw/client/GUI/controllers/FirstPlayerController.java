package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import javafx.fxml.FXML;

public class FirstPlayerController {

    private ClientAppGUI gui;

    String numPlayers;

    String check = "n";

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
        if(check == "n")
            check = "y";
        else
            check = "n";
    }

    @FXML
    protected void onButtonClick() {

        gui.getClientGUI().asyncWriteToSocket(numPlayers);

        gui.getClientGUI().asyncWriteToSocket(check);

        if(gui.isStartGame() == false){
            gui.changeStage("Loading");
        }
        else
            gui.changeStage("GameBoard");
    }

    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
