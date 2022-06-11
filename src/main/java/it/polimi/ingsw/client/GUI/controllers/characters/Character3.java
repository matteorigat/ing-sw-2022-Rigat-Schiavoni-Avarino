package it.polimi.ingsw.client.GUI.controllers.characters;

import it.polimi.ingsw.client.ClientAppGUI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Character3 implements Character{


    private ClientAppGUI gui;

    @FXML
    private Label effect;

    @FXML
    protected void onButtonClick() {
        //effect.setText("hai giocato questa carta!");
        Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,3"));
    }

    @Override
    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }

}


