package it.polimi.ingsw.client.GUI.controllers.characters;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.gameboard.characters.Character1;
import it.polimi.ingsw.model.player.Student;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Character5gui implements Character{

    public TextField islandIndex;
    private ClientAppGUI gui;
    private ArrayList<Student> students;
    private Model model;
    private Stage dialog;

    private int selectedStudent = -1;

    @FXML private Label effect;

    @FXML
    protected void onButtonClick() {
        int island = Integer.parseInt(islandIndex.getText())-1;

        if(island >= 0 && island < model.getGameBoard().getIslands().size()){
            //System.out.println("100,1," + island + "," + selectedStudent);
            Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,5," + island));
            dialog.close();
        }
    }

    @Override
    public void setGui(ClientAppGUI gui, Stage dialog) {
        this.gui = gui;
        this.dialog = dialog;
    }

    @Override
    public void setModel(Model model, int cardPosition){
        this.model = model;
    }


}
