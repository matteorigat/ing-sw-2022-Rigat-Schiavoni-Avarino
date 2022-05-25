package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.client.GUI.ClientGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;

public class GameBoardController {

    private ClientAppGUI gui;

    private int dragItem;

    @FXML
    protected void selectStudentGreen() {
         dragItem = 0;
        System.out.println("stai gdraggando");

    }

    @FXML
    protected void diningRoomGreen() {
       System.out.println("hai spostato nella sala verde il colore: " + dragItem);

    }


    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
