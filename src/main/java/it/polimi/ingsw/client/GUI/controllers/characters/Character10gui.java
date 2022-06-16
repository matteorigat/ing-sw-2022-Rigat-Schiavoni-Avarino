package it.polimi.ingsw.client.GUI.controllers.characters;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Student;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Character10gui implements Character{

    @FXML public Label student0, student1, student2, student3, student4;
    @FXML public Label selected0, selected1, selected2, selected3, selected4;
    @FXML public ImageView entrance0, entrance1, entrance2, entrance3, entrance4, entrance5, entrance6, entrance7, entrance8;
    @FXML public Label entranceselected0, entranceselected1, entranceselected2, entranceselected3, entranceselected4, entranceselected5, entranceselected6, entranceselected7, entranceselected8;

    int[] select = new int[2];
    int[][] entranceselect = new int[2][2];

    int counter = 0, counter2 = 0;
    private ClientAppGUI gui;
    private ArrayList<Student> students2;
    private int green, red, yellow, pink, blue;
    private Model model;
    private Stage dialog;

    private int selectedStudent = -1;

    @FXML private Label effect;

    /**
     * onButtonClick method gets the user input written/declared in the button
     */
    @FXML
    protected void onButtonClick() {

        if(select[0] != -1 && entranceselect[0][0] != -1 && select[1] == -1 && entranceselect[0][1] == -1){
            Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,10," + entranceselect[0][0] + "," + select[0]));
            dialog.close();
        } else if(select[0] != -1 && entranceselect[0][0] != -1 && select[1] != -1 && entranceselect[0][1] != -1){
            Platform.runLater(()-> gui.getClientGUI().asyncWriteToSocket("100,10," + entranceselect[0][0] + "," + select[0] + "," + entranceselect[0][1] + "," + select[1]));
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

        for(int i=0; i<2; i++){
            select[i] = -1;
        }

        for(Player p: model.getPlayers())
            if(p.getNickname().equals(gui.getClientGUI().getNickname())){
                green = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Green);
                red = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Red);
                yellow = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Yellow);
                pink = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Pink);
                blue = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Blue);
                students2 = p.getPlayerSchoolBoard().getStudentsEntrance();
                break;
            }

        student0.setText(String.valueOf(green));
        student1.setText(String.valueOf(red));
        student2.setText(String.valueOf(yellow));
        student3.setText(String.valueOf(pink));
        student4.setText(String.valueOf(blue));

        for(int i=0; i<2; i++){
            entranceselect[0][i] = -1;
            entranceselect[1][i] = -1;
        }


        for(int i=0; i<students2.size(); i++){
            if(i==0){
                entrance0.setImage(getStudentImage(students2.get(0).getColour()));
            } else if(i==1){
                entrance1.setImage(getStudentImage(students2.get(1).getColour()));
            } else if(i==2){
                entrance2.setImage(getStudentImage(students2.get(2).getColour()));
            } else if(i==3){
                entrance3.setImage(getStudentImage(students2.get(3).getColour()));
            } else if(i==4){
                entrance4.setImage(getStudentImage(students2.get(4).getColour()));
            } else if(i==5){
                entrance5.setImage(getStudentImage(students2.get(5).getColour()));
            } else if(i==6){
                entrance6.setImage(getStudentImage(students2.get(6).getColour()));
            } else if(i==7){
                entrance7.setImage(getStudentImage(students2.get(7).getColour()));
            } else if(i==8){
                entrance8.setImage(getStudentImage(students2.get(8).getColour()));
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
        if(select[0] == 0 && select[1] == 0)
            return;

        if(counter == 0){
            removeSelected(select[0]);
            if(select[1] == 0)
                selected0.setText("° °");
            else
                selected0.setText("°");

            select[0] = 0;
            counter++;
        }
        else if(counter == 1){
            removeSelected(select[1]);
            if(select[0] == 0)
                selected0.setText("° °");
            else
                selected0.setText("°");

            select[1] = 0;
            counter = 0;
        }
    }
    @FXML
    protected void chooseStudent1() {
        if(select[0] == 1 && select[1] == 1)
            return;

        if(counter == 0){
            removeSelected(select[0]);
            if(select[1] == 1)
                selected1.setText("° °");
            else
                selected1.setText("°");

            select[0] = 1;
            counter++;
        }
        else if(counter == 1){
            removeSelected(select[1]);
            if(select[0] == 1)
                selected1.setText("° °");
            else
                selected1.setText("°");

            select[1] = 1;
            counter = 0;
        }
    }
    @FXML
    protected void chooseStudent2() {
        if(select[0] == 2 && select[1] == 2)
            return;

        if(counter == 0){
            removeSelected(select[0]);
            if(select[1] == 2)
                selected2.setText("° °");
            else
                selected2.setText("°");

            select[0] = 2;
            counter++;
        }
        else if(counter == 1){
            removeSelected(select[1]);
            if(select[0] == 2)
                selected2.setText("° °");
            else
                selected2.setText("°");

            select[1] = 2;
            counter = 0;
        }
    }
    @FXML
    protected void chooseStudent3() {
        if(select[0] == 3 && select[1] == 3)
            return;

        if(counter == 0){
            removeSelected(select[0]);
            if(select[1] == 3)
                selected3.setText("° °");
            else
                selected3.setText("°");

            select[0] = 3;
            counter++;
        }
        else if(counter == 1){
            removeSelected(select[1]);
            if(select[0] == 3)
                selected3.setText("° °");
            else
                selected3.setText("°");

            select[1] = 3;
            counter = 0;
        }
    }
    @FXML
    protected void chooseStudent4() {
        if(select[0] == 4 && select[1] == 4)
            return;

        if(counter == 0){
            removeSelected(select[0]);
            if(select[1] == 4)
                selected4.setText("° °");
            else
                selected4.setText("°");

            select[0] = 4;
            counter++;
        }
        else if(counter == 1){
            removeSelected(select[1]);
            if(select[0] == 4)
                selected4.setText("° °");
            else
                selected4.setText("°");

            select[1] = 4;
            counter = 0;
        }
    }

    private void removeSelected(int i){
        if(select[0] == select[1] && select[0] != -1){
            if(i == 0)
                selected0.setText("°");
            else if(i == 1)
                selected1.setText("°");
            else if(i == 2)
                selected2.setText("°");
            else if(i == 3)
                selected3.setText("°");
            else if(i == 4)
                selected4.setText("°");
        } else {
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















    @FXML
    protected void chooseEntrance0() {
        for(int i=0; i<2; i++)
            if(entranceselect[1][i] == 0)
                return;

        if(counter2 == 0){
            entranceselect[0][0] = students2.get(0).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][0]);
            entranceselected0.setText("°");
            entranceselect[1][0] = 0;
            counter2++;
        }
        else if(counter2 == 1){
            entranceselect[0][1] = students2.get(0).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][1]);
            entranceselected0.setText("°");
            entranceselect[1][1] = 0;
            counter2 = 0;
        }
    }

    @FXML
    protected void chooseEntrance1() {
        for(int i=0; i<2; i++)
            if(entranceselect[1][i] == 1)
                return;

        if(counter2 == 0){
            entranceselect[0][0] = students2.get(1).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][0]);
            entranceselected1.setText("°");
            entranceselect[1][0] = 1;
            counter2++;
        }
        else if(counter2 == 1){
            entranceselect[0][1] = students2.get(1).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][1]);
            entranceselected1.setText("°");
            entranceselect[1][1] = 1;
            counter2 = 0;
        }
    }

    @FXML
    protected void chooseEntrance2() {
        for(int i=0; i<2; i++)
            if(entranceselect[1][i] == 2)
                return;

        if(counter2 == 0){
            entranceselect[0][0] = students2.get(2).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][0]);
            entranceselected2.setText("°");
            entranceselect[1][0] = 2;
            counter2++;
        }
        else if(counter2 == 1){
            entranceselect[0][1] = students2.get(2).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][1]);
            entranceselected2.setText("°");
            entranceselect[1][1] = 2;
            counter2 = 0;
        }
    }

    @FXML
    protected void chooseEntrance3() {
        for(int i=0; i<2; i++)
            if(entranceselect[1][i] == 3)
                return;

        if(counter2 == 0){
            entranceselect[0][0] = students2.get(3).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][0]);
            entranceselected3.setText("°");
            entranceselect[1][0] = 3;
            counter2++;
        }
        else if(counter2 == 1){
            entranceselect[0][1] = students2.get(3).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][1]);
            entranceselected3.setText("°");
            entranceselect[1][1] = 3;
            counter2 = 0;
        }
    }

    @FXML
    protected void chooseEntrance4() {
        for(int i=0; i<2; i++)
            if(entranceselect[1][i] == 4)
                return;

        if(counter2 == 0){
            entranceselect[0][0] = students2.get(4).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][0]);
            entranceselected4.setText("°");
            entranceselect[1][0] = 4;
            counter2++;
        }
        else if(counter2 == 1){
            entranceselect[0][1] = students2.get(4).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][1]);
            entranceselected4.setText("°");
            entranceselect[1][1] = 4;
            counter2 = 0;
        }
    }

    @FXML
    protected void chooseEntrance5() {
        for(int i=0; i<2; i++)
            if(entranceselect[1][i] == 5)
                return;

        if(counter2 == 0){
            entranceselect[0][0] = students2.get(5).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][0]);
            entranceselected5.setText("°");
            entranceselect[1][0] = 5;
            counter2++;
        }
        else if(counter2 == 1){
            entranceselect[0][1] = students2.get(5).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][1]);
            entranceselected5.setText("°");
            entranceselect[1][1] = 5;
            counter2 = 0;
        }
    }

    @FXML
    protected void chooseEntrance6() {
        for(int i=0; i<2; i++)
            if(entranceselect[1][i] == 6)
                return;

        if(counter2 == 0){
            entranceselect[0][0] = students2.get(6).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][0]);
            entranceselected6.setText("°");
            entranceselect[1][0] = 6;
            counter2++;
        }
        else if(counter2 == 1){
            entranceselect[0][1] = students2.get(6).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][1]);
            entranceselected6.setText("°");
            entranceselect[1][1] = 6;
            counter2 = 0;
        }
    }

    @FXML
    protected void chooseEntrance7() {
        if(students2.get(7) == null){
            entrance7.setVisible(false);
            return;
        }

        for(int i=0; i<2; i++)
            if(entranceselect[1][i] == 7)
                return;

        if(counter2 == 0){
            entranceselect[0][0] = students2.get(7).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][0]);
            entranceselected7.setText("°");
            entranceselect[1][0] = 7;
            counter2++;
        }
        else if(counter2 == 1){
            entranceselect[0][1] = students2.get(7).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][1]);
            entranceselected7.setText("°");
            entranceselect[1][1] = 7;
            counter2 = 0;
        }
    }

    @FXML
    protected void chooseEntrance8() {
        if(students2.get(8) == null){
            entrance8.setVisible(false);
            return;
        }

        for(int i=0; i<2; i++)
            if(entranceselect[1][i] == 8)
                return;

        if(counter2 == 0){
            entranceselect[0][0] = students2.get(8).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][0]);
            entranceselected8.setText("°");
            entranceselect[1][0] = 8;
            counter2++;
        }
        else if(counter2 == 1){
            entranceselect[0][1] = students2.get(8).getColour().ordinal();
            removeEntranceSelected(entranceselect[1][1]);
            entranceselected8.setText("°");
            entranceselect[1][1] = 8;
            counter2 = 0;
        }
    }

    private void removeEntranceSelected(int i){
        if(i == 0)
            entranceselected0.setText("");
        else if(i == 1)
            entranceselected1.setText("");
        else if(i == 2)
            entranceselected2.setText("");
        else if(i == 3)
            entranceselected3.setText("");
        else if(i == 4)
            entranceselected4.setText("");
        else if(i == 5)
            entranceselected5.setText("");
        else if(i == 6)
            entranceselected6.setText("");
        else if(i == 7)
            entranceselected7.setText("");
        else if(i == 8)
            entranceselected8.setText("");
    }

}
