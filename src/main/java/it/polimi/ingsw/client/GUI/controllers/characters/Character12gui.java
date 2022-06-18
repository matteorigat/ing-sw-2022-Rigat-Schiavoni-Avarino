package it.polimi.ingsw.client.GUI.controllers.characters;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.model.Model;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Character12gui implements Character{


    @FXML public Label selected0, selected1, selected2, selected3, selected4;

    private int previusSelected = -1;

    private ClientAppGUI gui;
    private Model model;
    private Stage dialog;


    /**
     * onButtonClick method gets the user input written/declared in the button
     */
    @FXML
    protected void onButtonClick() {

        if(previusSelected >= 0 && previusSelected <5){
            Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,12," + previusSelected));
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
    }

    @FXML
    protected void chooseStudent0() {
        removeSelected(previusSelected);
        previusSelected = 0;
        selected0.setText("°");
    }
    @FXML
    protected void chooseStudent1() {
        removeSelected(previusSelected);
        previusSelected = 1;
        selected1.setText("°");
    }
    @FXML
    protected void chooseStudent2() {
        removeSelected(previusSelected);
        previusSelected = 2;
        selected2.setText("°");
    }
    @FXML
    protected void chooseStudent3() {
        removeSelected(previusSelected);
        previusSelected = 3;
        selected3.setText("°");
    }

    @FXML
    protected void chooseStudent4() {
        removeSelected(previusSelected);
        previusSelected = 4;
        selected4.setText("°");
    }


    private void removeSelected(int i){
        if(i == 0)
            selected0.setText("");
        else if(i == 1)
            selected1.setText("");
        else if(i == 2)
            selected2.setText("");
        else if(i == 3)
            selected3.setText("");
        else if(i == 4)
            selected4.setText("");
    }

}
