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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Character1gui implements Character{

    public ImageView student0, student1, student2, student3;
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

        if(island >= 0 && island < model.getGameBoard().getIslands().size() && selectedStudent >=0 && selectedStudent<5){
            //System.out.println("100,1," + island + "," + selectedStudent);
            Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,1," + selectedStudent + "," + island));
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

        Character1 card = (Character1) model.getGameBoard().getThreeCharacterCards().get(cardPosition);

        students = card.getStudents();
        for(int i=0; i<students.size(); i++){
            if(i==0){
                student0.setImage(getStudentImage(students.get(0).getColour()));
            } else if(i==1){
                student1.setImage(getStudentImage(students.get(1).getColour()));
            }  else if(i==2){
                student2.setImage(getStudentImage(students.get(2).getColour()));
            }  else if(i==3){
                student3.setImage(getStudentImage(students.get(3).getColour()));
            }
        }

    }

    private Image getStudentImage(Colour colour) {
        if (colour.equals(Colour.Green))
            return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_green.png")));
        else if (colour.equals(Colour.Red))
            return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_red.png")));
        else if (colour.equals(Colour.Yellow))
            return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_yellow.png")));
        else if (colour.equals(Colour.Pink))
            return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_pink.png")));
        else if (colour.equals(Colour.Blue))
            return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_blue.png")));
        else return null;

    }

    @FXML
    protected void chooseStudent0() {
        selectedStudent = students.get(0).getColour().ordinal();
    }
    @FXML
    protected void chooseStudent1() {
        selectedStudent = students.get(1).getColour().ordinal();
    }
    @FXML
    protected void chooseStudent2() {
        selectedStudent = students.get(2).getColour().ordinal();
    }
    @FXML
    protected void chooseStudent3() {
        selectedStudent = students.get(3).getColour().ordinal();
    }

}
