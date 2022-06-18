package it.polimi.ingsw.client.GUI.controllers.characters;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.model.Model;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Character5gui implements Character{

    private ClientAppGUI gui;
    private Model model;
    private Stage dialog;

    @FXML public TextField islandIndex;
    @FXML public Label islandsSize;

    /**
     * onButtonClick method gets the user input written/declared in the button
     */
    @FXML
    protected void onButtonClick() {
        int island = Integer.parseInt(islandIndex.getText())-1;

        if(island >= 0 && island < model.getGameBoard().getIslands().size()){
            Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,5," + island));
            dialog.close();
        }
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
    public void setModel(Model model, int cardPosition){
        this.model = model;
        islandsSize.setText("Insert the island index from 1 to " + model.getGameBoard().getIslands().size());
    }


}
