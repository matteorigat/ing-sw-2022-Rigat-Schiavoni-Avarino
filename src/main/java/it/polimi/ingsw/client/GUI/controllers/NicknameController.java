package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.concurrent.TimeUnit;


public class NicknameController {

    private ClientAppGUI gui;

    @FXML private TextField nickname;

    @FXML
    protected void onButtonClick() {

       gui.getClientGUI().setNickname(nickname.getText());

    }

    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }

    public void clearNickname() {
        this.nickname.clear();
    }
}
