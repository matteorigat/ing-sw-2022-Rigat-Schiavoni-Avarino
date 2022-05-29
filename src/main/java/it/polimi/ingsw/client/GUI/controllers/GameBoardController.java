package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Professor;
import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.player.Tower;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class GameBoardController {

    private ClientAppGUI gui;
    private Colour choice;
    private Model model;
    private String nickname;
    private Player myPlayer;

    int alreadyUsed = 0;

    @FXML public Pane schoolBoard0, schoolBoard1 ,schoolBoard2;

    @FXML public ImageView entrance00, entrance01, entrance02, entrance03, entrance04, entrance05, entrance06, entrance07, entrance08;
    @FXML public ImageView green00, green01, green02, green03, green04, green05, green06, green07, green08, green09;
    @FXML public ImageView red00, red01, red02, red03, red04, red05, red06, red07, red08, red09;
    @FXML public ImageView yellow00, yellow01, yellow02, yellow03, yellow04, yellow05, yellow06, yellow07, yellow08, yellow09;
    @FXML public ImageView pink00, pink01, pink02, pink03, pink04, pink05, pink06, pink07, pink08, pink09;
    @FXML public ImageView blue00, blue01, blue02, blue03, blue04, blue05, blue06, blue07, blue08, blue09;
    @FXML public ImageView greenProf0, redProf0, yellowProf0, pinkProf0, blueProf0;
    @FXML public ImageView tower00, tower01, tower02, tower03, tower04, tower05, tower06, tower07;

    @FXML public ImageView entrance10, entrance11, entrance12, entrance13, entrance14, entrance15, entrance16, entrance17, entrance18;
    @FXML public ImageView green10, green11, green12, green13, green14, green15, green16, green17, green18, green19;
    @FXML public ImageView red10, red11, red12, red13, red14, red15, red16, red17, red18, red19;
    @FXML public ImageView yellow10, yellow11, yellow12, yellow13, yellow14, yellow15, yellow16, yellow17, yellow18, yellow19;
    @FXML public ImageView pink10, pink11, pink12, pink13, pink14, pink15, pink16, pink17, pink18, pink19;
    @FXML public ImageView blue10, blue11, blue12, blue13, blue14, blue15, blue16, blue17, blue18, blue19;
    @FXML public ImageView greenProf1, redProf1, yellowProf1, pinkProf1, blueProf1;
    @FXML public ImageView tower10, tower11, tower12, tower13, tower14, tower15, tower16, tower17;



    @FXML public ImageView entrance20, entrance21, entrance22, entrance23, entrance24, entrance25, entrance26, entrance27, entrance28;
    @FXML public ImageView green20, green21, green22, green23, green24, green25, green26, green27, green28, green29;
    @FXML public ImageView red20, red21, red22, red23, red24, red25, red26, red27, red28, red29;
    @FXML public ImageView yellow20, yellow21, yellow22, yellow23, yellow24, yellow25, yellow26, yellow27, yellow28, yellow29;
    @FXML public ImageView pink20, pink21, pink22, pink23, pink24, pink25, pink26, pink27, pink28, pink29;
    @FXML public ImageView blue20, blue21, blue22, blue23, blue24, blue25, blue26, blue27, blue28, blue29;
    @FXML public ImageView greenProf2, redProf2, yellowProf2, pinkProf2, blueProf2;
    @FXML public ImageView tower20, tower21, tower22, tower23, tower24, tower25, tower26, tower27;


    @FXML
    protected void diningRoomGreen() {}
    @FXML
    protected void diningRoomRed() {}
    @FXML
    protected void diningRoomYellow() {}
    @FXML
    protected void diningRoomPink() {}
    @FXML
    protected void diningRoomBlue() {}

    private void update() {
        schoolBoard0.setVisible(true);
        schoolBoard1.setVisible(true);
        if(model.getPlayers().size() > 2)
            schoolBoard2.setVisible(true);


        alreadyUsed = 0;
        for (Player p : model.getPlayers()) {
            if (p.getNickname().equals(nickname)) {
                myPlayer = p;
                updatePlayerSchoolBoard(p, true);
            } else {
                updatePlayerSchoolBoard(p, false);
                alreadyUsed++;
            }
        }
    }

    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
        schoolBoard0.setVisible(false);
        schoolBoard1.setVisible(false);
        schoolBoard2.setVisible(false);
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
        return switch (col) {
            case 0 -> "0";
            case 1 -> "1";
            case 2 -> "2";
            case 3 -> "3";
            case 4 -> "4";
            default -> null;
        };
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
        if(entrance04.getImage() != null){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(4).getColour();
            System.out.println("studente 4: " + choice);
        }
    }
    @FXML
    protected void chooseStudent5(){
        if(entrance05.getImage() != null){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(5).getColour();
            System.out.println("studente 5: " + choice);
        }
    }
    @FXML
    protected void chooseStudent6(){
        if(entrance06.getImage() != null){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(6).getColour();
            System.out.println("studente 6: " + choice);
        }
    }

    @FXML
    protected void chooseStudent7(){
        if(entrance07.getImage() != null){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(7).getColour();
            System.out.println("studente 7: " + choice);
        }
    }
    @FXML
    protected void chooseStudent8(){
        if(entrance08.getImage() != null){
            choice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(8).getColour();
            System.out.println("studente 8: " + choice);
        }
    }















    //PLAYER SCHOOLBOARD
    private void updatePlayerSchoolBoard(Player p, boolean mainPlayer){
        int i, k = 0;
        for (Student s : p.getPlayerSchoolBoard().getStudentsEntrance()) {
            setEntrance(k, s.getColour(), mainPlayer);
            k++;
        }
        for(int t=k; t < 9; t++){
            getEntrance(t, mainPlayer).setImage(null);
        }

        int green = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Green);
        for (i = 0; i < green; i++)
            getGreen(i, mainPlayer).setVisible(true);
        for (int j = i; j < 10; j++)
            getGreen(j, mainPlayer).setVisible(false);

        int red = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Green);
        for (i = 0; i < red; i++)
            getRed(i, mainPlayer).setVisible(true);
        for (int j = i; j < 10; j++)
            getRed(j, mainPlayer).setVisible(false);

        int yellow = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Green);
        for (i = 0; i < yellow; i++)
            getYellow(i, mainPlayer).setVisible(true);
        for (int j = i; j < 10; j++)
            getYellow(j, mainPlayer).setVisible(false);

        int pink = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Green);
        for (i = 0; i < pink; i++)
            getPink(i, mainPlayer).setVisible(true);
        for (int j = i; j < 10; j++)
            getPink(j, mainPlayer).setVisible(false);

        int blue = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Green);
        for (i = 0; i < blue; i++)
            getBlue(i, mainPlayer).setVisible(true);
        for (int j = i; j < 10; j++)
            getBlue(j, mainPlayer).setVisible(false);

        k = 0;
        for (Professor pr : p.getPlayerSchoolBoard().getProfessors()) {
            setProfessor(k, pr.getProfessorColour(), mainPlayer);
            k++;
        }
        for(int t=k; t < 5; t++){
            getProfessor(t, mainPlayer).setImage(null);
        }

        k = 0;
        for (Tower tr : p.getPlayerSchoolBoard().getTowers()) {
            setTower(k, tr.getTowerColor(), mainPlayer);
            k++;
        }
        for(int t=k; t < 8; t++){
            getTower(t, mainPlayer).setImage(null);
        }
    }
    private void setEntrance(int i, Colour colour, boolean mainPlayer) {
        if (colour.equals(Colour.Green))
            getEntrance(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_green.png"))));
        else if (colour.equals(Colour.Red))
            getEntrance(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_red.png"))));
        else if (colour.equals(Colour.Yellow))
            getEntrance(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_yellow.png"))));
        else if (colour.equals(Colour.Pink))
            getEntrance(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_pink.png"))));
        else if (colour.equals(Colour.Blue))
            getEntrance(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_blue.png"))));

    }
    private void setProfessor(int i, Colour colour, boolean mainPlayer) {
        if (colour.equals(Colour.Green))
            getProfessor(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/teacher_green.png"))));
        else if (colour.equals(Colour.Red))
            getProfessor(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/teacher_red.png"))));
        else if (colour.equals(Colour.Yellow))
            getProfessor(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/teacher_yellow.png"))));
        else if (colour.equals(Colour.Pink))
            getProfessor(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/teacher_pink.png"))));
        else if (colour.equals(Colour.Blue))
            getProfessor(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/teacher_blue.png"))));

    }
    private void setTower(int i, TowerColour colour, boolean mainPlayer) {
        if (colour.equals(TowerColour.Black))
            getTower(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/black_tower.png"))));
        else if (colour.equals(TowerColour.White))
            getTower(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/white_tower.png"))));
        else if (colour.equals(TowerColour.Grey))
            getTower(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/grey_tower.png"))));

    }
    private ImageView getGreen(int pos, boolean mainPlayer) {
        if(mainPlayer){
            return switch (pos) {
                case 0 -> green00;
                case 1 -> green01;
                case 2 -> green02;
                case 3 -> green03;
                case 4 -> green04;
                case 5 -> green05;
                case 6 -> green06;
                case 7 -> green07;
                case 8 -> green08;
                case 9 -> green09;
                default -> null;
            };
        } else if(alreadyUsed == 0){
            return switch (pos) {
                case 0 -> green10;
                case 1 -> green11;
                case 2 -> green12;
                case 3 -> green13;
                case 4 -> green14;
                case 5 -> green15;
                case 6 -> green16;
                case 7 -> green17;
                case 8 -> green18;
                case 9 -> green19;
                default -> null;
            };
        } else {
            return switch (pos) {
                case 0 -> green20;
                case 1 -> green21;
                case 2 -> green22;
                case 3 -> green23;
                case 4 -> green24;
                case 5 -> green25;
                case 6 -> green26;
                case 7 -> green27;
                case 8 -> green28;
                case 9 -> green29;
                default -> null;
            };
        }
    }
    private ImageView getRed(int pos, boolean mainPlayer) {
        if(mainPlayer){
            return switch (pos) {
                case 0 -> red00;
                case 1 -> red01;
                case 2 -> red02;
                case 3 -> red03;
                case 4 -> red04;
                case 5 -> red05;
                case 6 -> red06;
                case 7 -> red07;
                case 8 -> red08;
                case 9 -> red09;
                default -> null;
            };
        } else if(alreadyUsed == 0){
            return switch (pos) {
                case 0 -> red10;
                case 1 -> red11;
                case 2 -> red12;
                case 3 -> red13;
                case 4 -> red14;
                case 5 -> red15;
                case 6 -> red16;
                case 7 -> red17;
                case 8 -> red18;
                case 9 -> red19;
                default -> null;
            };
        } else {
            return switch (pos) {
                case 0 -> red20;
                case 1 -> red21;
                case 2 -> red22;
                case 3 -> red23;
                case 4 -> red24;
                case 5 -> red25;
                case 6 -> red26;
                case 7 -> red27;
                case 8 -> red28;
                case 9 -> red29;
                default -> null;
            };
        }
    }
    private ImageView getYellow(int pos, boolean mainPlayer) {
        if(mainPlayer){
            return switch (pos) {
                case 0 -> yellow00;
                case 1 -> yellow01;
                case 2 -> yellow02;
                case 3 -> yellow03;
                case 4 -> yellow04;
                case 5 -> yellow05;
                case 6 -> yellow06;
                case 7 -> yellow07;
                case 8 -> yellow08;
                case 9 -> yellow09;
                default -> null;
            };
        } else if(alreadyUsed == 0){
            return switch (pos) {
                case 0 -> yellow10;
                case 1 -> yellow11;
                case 2 -> yellow12;
                case 3 -> yellow13;
                case 4 -> yellow14;
                case 5 -> yellow15;
                case 6 -> yellow16;
                case 7 -> yellow17;
                case 8 -> yellow18;
                case 9 -> yellow19;
                default -> null;
            };
        } else {
            return switch (pos) {
                case 0 -> yellow20;
                case 1 -> yellow21;
                case 2 -> yellow22;
                case 3 -> yellow23;
                case 4 -> yellow24;
                case 5 -> yellow25;
                case 6 -> yellow26;
                case 7 -> yellow27;
                case 8 -> yellow28;
                case 9 -> yellow29;
                default -> null;
            };
        }
    }
    private ImageView getPink(int pos, boolean mainPlayer) {
        if(mainPlayer){
            return switch (pos) {
                case 0 -> pink00;
                case 1 -> pink01;
                case 2 -> pink02;
                case 3 -> pink03;
                case 4 -> pink04;
                case 5 -> pink05;
                case 6 -> pink06;
                case 7 -> pink07;
                case 8 -> pink08;
                case 9 -> pink09;
                default -> null;
            };
        } else if(alreadyUsed == 0){
            return switch (pos) {
                case 0 -> pink10;
                case 1 -> pink11;
                case 2 -> pink12;
                case 3 -> pink13;
                case 4 -> pink14;
                case 5 -> pink15;
                case 6 -> pink16;
                case 7 -> pink17;
                case 8 -> pink18;
                case 9 -> pink19;
                default -> null;
            };
        } else {
            return switch (pos) {
                case 0 -> pink20;
                case 1 -> pink21;
                case 2 -> pink22;
                case 3 -> pink23;
                case 4 -> pink24;
                case 5 -> pink25;
                case 6 -> pink26;
                case 7 -> pink27;
                case 8 -> pink28;
                case 9 -> pink29;
                default -> null;
            };
        }

    }
    private ImageView getBlue(int pos, boolean mainPlayer) {
        if(mainPlayer){
            return switch (pos) {
                case 0 -> blue00;
                case 1 -> blue01;
                case 2 -> blue02;
                case 3 -> blue03;
                case 4 -> blue04;
                case 5 -> blue05;
                case 6 -> blue06;
                case 7 -> blue07;
                case 8 -> blue08;
                case 9 -> blue09;
                default -> null;
            };
        } else if(alreadyUsed == 0){
            return switch (pos) {
                case 0 -> blue10;
                case 1 -> blue11;
                case 2 -> blue12;
                case 3 -> blue13;
                case 4 -> blue14;
                case 5 -> blue15;
                case 6 -> blue16;
                case 7 -> blue17;
                case 8 -> blue18;
                case 9 -> blue19;
                default -> null;
            };
        } else {
            return switch (pos) {
                case 0 -> blue20;
                case 1 -> blue21;
                case 2 -> blue22;
                case 3 -> blue23;
                case 4 -> blue24;
                case 5 -> blue25;
                case 6 -> blue26;
                case 7 -> blue27;
                case 8 -> blue28;
                case 9 -> blue29;
                default -> null;
            };
        }
    }
    private ImageView getEntrance(int pos, boolean mainPlayer) {
        if(mainPlayer){
            return switch (pos) {
                case 0 -> entrance00;
                case 1 -> entrance01;
                case 2 -> entrance02;
                case 3 -> entrance03;
                case 4 -> entrance04;
                case 5 -> entrance05;
                case 6 -> entrance06;
                case 7 -> entrance07;
                case 8 -> entrance08;
                default -> null;
            };
        } else if(alreadyUsed == 0){
            return switch (pos) {
                case 0 -> entrance10;
                case 1 -> entrance11;
                case 2 -> entrance12;
                case 3 -> entrance13;
                case 4 -> entrance14;
                case 5 -> entrance15;
                case 6 -> entrance16;
                case 7 -> entrance17;
                case 8 -> entrance18;
                default -> null;
            };
        } else {
            return switch (pos) {
                case 0 -> entrance20;
                case 1 -> entrance21;
                case 2 -> entrance22;
                case 3 -> entrance23;
                case 4 -> entrance24;
                case 5 -> entrance25;
                case 6 -> entrance26;
                case 7 -> entrance27;
                case 8 -> entrance28;
                default -> null;
            };
        }
    }
    private ImageView getProfessor(int pos, boolean mainPlayer) {
        if(mainPlayer){
            return switch (pos) {
                case 0 -> greenProf0;
                case 1 -> redProf0;
                case 2 -> yellowProf0;
                case 3 -> pinkProf0;
                case 4 -> blueProf0;
                default -> null;
            };
        } else if(alreadyUsed == 0){
            return switch (pos) {
                case 0 -> greenProf1;
                case 1 -> redProf1;
                case 2 -> yellowProf1;
                case 3 -> pinkProf1;
                case 4 -> blueProf1;
                default -> null;
            };
        } else {
            return switch (pos) {
                case 0 -> greenProf2;
                case 1 -> redProf2;
                case 2 -> yellowProf2;
                case 3 -> pinkProf2;
                case 4 -> blueProf2;
                default -> null;
            };
        }

    }
    private ImageView getTower(int pos, boolean mainPlayer) {
        if(mainPlayer){
            return switch (pos) {
                case 0 -> tower00;
                case 1 -> tower01;
                case 2 -> tower02;
                case 3 -> tower03;
                case 4 -> tower04;
                case 5 -> tower05;
                case 6 -> tower06;
                case 7 -> tower07;
                default -> null;
            };
        } else if(alreadyUsed == 0){
            return switch (pos) {
                case 0 -> tower10;
                case 1 -> tower11;
                case 2 -> tower12;
                case 3 -> tower13;
                case 4 -> tower14;
                case 5 -> tower15;
                case 6 -> tower16;
                case 7 -> tower17;
                default -> null;
            };
        } else {
            return switch (pos) {
                case 0 -> tower20;
                case 1 -> tower21;
                case 2 -> tower22;
                case 3 -> tower23;
                case 4 -> tower24;
                case 5 -> tower25;
                case 6 -> tower26;
                case 7 -> tower27;
                default -> null;
            };
        }
    }
















}