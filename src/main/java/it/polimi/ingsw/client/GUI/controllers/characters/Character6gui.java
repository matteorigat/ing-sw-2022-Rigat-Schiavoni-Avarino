package it.polimi.ingsw.client.GUI.controllers.characters;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.model.Model;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Character6gui implements Character{
    private ClientAppGUI gui;
    private Stage dialog;

    /**
     * onButtonClick method gets the user input written/declared in the button
     */
    @FXML
    protected void onButtonClick() {
        Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,6"));
        dialog.close();
    }

    /**
     * setGui sets a GUI
     * @param gui
     * @param dialog
     */
    @Override
    public void setGui(ClientAppGUI gui, Stage dialog) {
        this.gui = gui;
        this.dialog = dialog;
    }

    /**
     * setModel method sets the model
     * @param model
     * @param cardPosition
     */
    @Override
    public void setModel(Model model, int cardPosition) {

    }
}

