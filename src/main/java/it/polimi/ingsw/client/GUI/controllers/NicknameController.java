package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.concurrent.TimeUnit;


public class NicknameController {

    private ClientAppGUI gui;

    @FXML private TextField nickname;

    @FXML
    protected void onButtonClick() throws InterruptedException {

       gui.getClientGUI().setNickname(nickname.getText());

        TimeUnit.MILLISECONDS.sleep(100);
       if(gui.getClientGUI().isFirstPlayer() == false && gui.getClientGUI().isLoading() == false && gui.getClientGUI().isStartgame() == false){
           nickname.clear();
           return;
       }

       System.out.println("nome ok");

       if(gui.getClientGUI().isFirstPlayer() == true){
           gui.changeStage("FirstPlayer");
       } else if(gui.getClientGUI().isStartgame() == false){
           gui.changeStage("Loading");
       } else {
           gui.changeStage("GameBoard");
       }

    }


    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
