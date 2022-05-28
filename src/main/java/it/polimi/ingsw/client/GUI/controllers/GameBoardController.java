package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.client.GUI.ClientGUI;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;

import java.util.Objects;

public class GameBoardController {

    private ClientAppGUI gui;

    private Colour choice;

    private Model model;

    private int dragItem;

    private String nickname;
    private Player myPlayer;

    @FXML
    private ImageView green1;
    @FXML
    private ImageView green2;
    @FXML
    private ImageView green3;
    @FXML
    private ImageView green4;
    @FXML
    private ImageView green5;
    @FXML
    private ImageView green6;
    @FXML
    private ImageView green7;
    @FXML
    private ImageView green8;
    @FXML
    private ImageView green9;
    @FXML
    private ImageView green10;
    @FXML
    private ImageView entrance1;
    @FXML
    private ImageView entrance2;
    @FXML
    private ImageView entrance3;
    @FXML
    private ImageView entrance4;
    @FXML
    private ImageView entrance5;
    @FXML
    private ImageView entrance6;
    @FXML
    private ImageView entrance7;
    @FXML
    private ImageView entrance8;
    @FXML
    private ImageView entrance9;


    @FXML
    protected void diningRoomGreen() {
        System.out.println("hai spostato nella sala verde il colore: " + dragItem);
        int green = 4;
        for (int i = 1; i < green + 1; i++) {
            getGreen(i).setVisible(true);
        }
    }

    private void update() {
        int alreadyUsed = 0;
        int i;


        for (Player p : model.getPlayers()) {

            if (p.getNickname().equals(nickname)) {
                myPlayer = p;
                int k = 0;
                for (Student s : p.getPlayerSchoolBoard().getStudentsEntrance()) {
                    k++;
                    setEntrance(k, s.getColour());
                }
                for(int t=k+1; t < 8; t++){
                    getEntrance(t).setImage(null);
                }

                int green = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Green);
                for (i = 1; i < green + 1; i++) {
                    getGreen(i).setVisible(true);
                }
                for (int j = i; j < 11; j++) {
                    getGreen(j).setVisible(false);
                }
            } else {
                if (alreadyUsed == 0) {
                    alreadyUsed = 1;
                } else {

                }
            }
        }
    }


    private ImageView getGreen(int pos) {
        if (pos == 1) return green1;
        else if (pos == 2) return green2;
        else if (pos == 3) return green3;
        else if (pos == 4) return green4;
        else if (pos == 5) return green5;
        else if (pos == 6) return green6;
        else if (pos == 7) return green7;
        else if (pos == 8) return green8;
        else if (pos == 9) return green9;
        else if (pos == 10) return green10;
        return null;
    }

    private ImageView getEntrance(int pos) {
        if (pos == 1) return entrance1;
        if (pos == 2) return entrance2;
        if (pos == 3) return entrance3;
        if (pos == 4) return entrance4;
        if (pos == 5) return entrance5;
        if (pos == 6) return entrance6;
        if (pos == 7) return entrance7;
        if (pos == 8) return entrance8;
        if (pos == 9) return entrance9;
        return null;
    }

    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
        for (int i = 1; i < 11; i++) {
            getGreen(i).setVisible(false);
        }

    }

    private void setEntrance(int i, Colour colour) {
        if (colour.equals(Colour.Green))
            getEntrance(i).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_green.png"))));
        else if (colour.equals(Colour.Red))
            getEntrance(i).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_red.png"))));
        else if (colour.equals(Colour.Yellow))
            getEntrance(i).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_yellow.png"))));
        else if (colour.equals(Colour.Pink))
            getEntrance(i).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_pink.png"))));
        else if (colour.equals(Colour.Blue))
                getEntrance(i).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_blue.png"))));

    }


    public void setModel(Model model) {
        this.model = model;
        System.out.println("Ricevuto il model");
        update();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


   protected void chooseIsland1(){
        String student = getColorString(choice);
        gui.getClientGUI().asyncWriteToSocket(student + ",1");

    }

    protected void chooseDiningRoom(){
        String student = getColorString(choice);
        gui.getClientGUI().asyncWriteToSocket(student);
    }

    private String getColorString(Colour color){
        int col = color.ordinal();
        switch (col){
            case 0: return "0";
            case 1: return "1";
            case 2: return "2";
            case 3: return "3";
            case 4: return "4";
        }
     return null;
    }

    @FXML
    protected void chooseStudent0(){
        choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour();
        System.out.println("studente 0: " + choice);

    }
    @FXML
    protected void chooseStudent1(){
        choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(1).getColour();
        System.out.println("studente 1: " + choice);
    }
    @FXML
    protected void chooseStudent2(){
        choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(2).getColour();
        System.out.println("studente 2: " + choice);
    }
    @FXML
    protected void chooseStudent3(){
        choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(3).getColour();
        System.out.println("studente 3: " + choice);
    }
    @FXML
    protected void chooseStudent4(){
        if(entrance5.isVisible()){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(4).getColour();
            System.out.println("studente 4: " + choice);
        }
    }
    @FXML
    protected void chooseStudent5(){
        if(entrance6.isVisible()){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(5).getColour();
            System.out.println("studente 5: " + choice);
        }
    }
    @FXML
    protected void chooseStudent6(){
        if(entrance7.isVisible()){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(6).getColour();
            System.out.println("studente 6: " + choice);
        }
    }

    @FXML
    protected void chooseStudent7(){
        if(entrance8.getImage() != null){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(7).getColour();
            System.out.println("studente 7: " + choice);
        }
    }
    @FXML
    protected void chooseStudent8(){
        if(entrance8.getImage() != null){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(8).getColour();
            System.out.println("studente 8: " + choice);
        }
    }


}