package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.client.GUI.ClientGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoadingController {

    private ClientAppGUI gui;


    public void startMatch() {

        while (gui.isStartGame() == false){}

        gui.changeStage("Home");

    }

    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
