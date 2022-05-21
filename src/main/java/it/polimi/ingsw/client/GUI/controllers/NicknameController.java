package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.client.GUI.ClientGUI;
import it.polimi.ingsw.observer.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NicknameController {

    private ClientAppGUI gui;

    @FXML private TextField nickname;

    @FXML
    protected void onButtonClick() {

       gui.getClientGUI().setNickname(nickname.getText());

       while (gui.getClientGUI().isFirstPlayer() == false && gui.getClientGUI().isLoading() == false){
       }

       if(gui.getClientGUI().isFirstPlayer() == true)
           gui.changeStage("FirstPlayer");
       else if(gui.isStartGame() == false)
           gui.changeStage("Loading");
       else
           gui.changeStage("Home");


    }


    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
