package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import javafx.fxml.FXML;

/**
 * firstPlayerController class controls the first player settings about the match
 */
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

    /**
     * onCheck method checks the expert mode button
     */
    @FXML
    protected void onCheck() {
        if(expertMode == "n")
            expertMode = "y";
        else
            expertMode = "n";
    }

    /**
     * onButtonClick method gets the user input written in the button
     */
    @FXML
    protected void onButtonClick() {

        if(numPlayers.equals(""))
            return;

        gui.getClientGUI().asyncWriteToSocket(numPlayers);
        gui.getClientGUI().asyncWriteToSocket(expertMode);

    }

    /**
     * setGui method sets GUI
     */
    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }

    @FXML
    protected void onSoundClick() {

        if(gui.getLaserPlayer().isPlaying())
            gui.getLaserPlayer().stop();
        else gui.getLaserPlayer().play();

    }
}
