package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.client.GUI.controllers.characters.Character;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Cloud;
import it.polimi.ingsw.model.gameboard.Island;
import it.polimi.ingsw.model.gameboard.characters.CharacterCard;
import it.polimi.ingsw.model.player.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


/**
 * GameBoardController class controls every input (GUI side) on the game board
 */

public class GameBoardController {

    private ClientAppGUI gui;
    private int studentChoice = -1;
    private Model model;
    private String nickname;
    private Player myPlayer;

    int alreadyUsed = 0;
    @FXML public Label gamephase, currentplayer;

    @FXML public Pane schoolBoard0, schoolBoard1 ,schoolBoard2, cloud0, cloud1, cloud2, character0, character1, character2;
    @FXML public ImageView characterImg0, characterImg1, characterImg2;
    @FXML public Label characterText0, characterText1, characterText2;
    @FXML public Pane island0, island1, island2, island3, island4, island5, island6, island7, island8, island9, island10, island11;
    @FXML public ImageView cloudStudent00, cloudStudent01, cloudStudent02, cloudStudent03, cloudStudent10, cloudStudent11, cloudStudent12, cloudStudent13, cloudStudent20, cloudStudent21, cloudStudent22, cloudStudent23;
    @FXML public Label nickname0, nickname1, nickname2, coins0, coins1, coins2;
    @FXML public ImageView lastAssistant0, lastAssistant1, lastAssistant2;

    @FXML public ImageView deny0, deny1, deny2, deny3, deny4, deny5, deny6, deny7, deny8, deny9, deny10, deny11;

    @FXML public ImageView assistant1, assistant2, assistant3, assistant4, assistant5, assistant6, assistant7, assistant8, assistant9, assistant10;

    // PLAYER 0
    @FXML public ImageView entrance00, entrance01, entrance02, entrance03, entrance04, entrance05, entrance06, entrance07, entrance08;
    @FXML public ImageView green00, green01, green02, green03, green04, green05, green06, green07, green08, green09;
    @FXML public ImageView red00, red01, red02, red03, red04, red05, red06, red07, red08, red09;
    @FXML public ImageView yellow00, yellow01, yellow02, yellow03, yellow04, yellow05, yellow06, yellow07, yellow08, yellow09;
    @FXML public ImageView pink00, pink01, pink02, pink03, pink04, pink05, pink06, pink07, pink08, pink09;
    @FXML public ImageView blue00, blue01, blue02, blue03, blue04, blue05, blue06, blue07, blue08, blue09;
    @FXML public ImageView greenProf0, redProf0, yellowProf0, pinkProf0, blueProf0;
    @FXML public ImageView tower00, tower01, tower02, tower03, tower04, tower05, tower06, tower07;
    // PLAYER 1
    @FXML public ImageView entrance10, entrance11, entrance12, entrance13, entrance14, entrance15, entrance16, entrance17, entrance18;
    @FXML public ImageView green10, green11, green12, green13, green14, green15, green16, green17, green18, green19;
    @FXML public ImageView red10, red11, red12, red13, red14, red15, red16, red17, red18, red19;
    @FXML public ImageView yellow10, yellow11, yellow12, yellow13, yellow14, yellow15, yellow16, yellow17, yellow18, yellow19;
    @FXML public ImageView pink10, pink11, pink12, pink13, pink14, pink15, pink16, pink17, pink18, pink19;
    @FXML public ImageView blue10, blue11, blue12, blue13, blue14, blue15, blue16, blue17, blue18, blue19;
    @FXML public ImageView greenProf1, redProf1, yellowProf1, pinkProf1, blueProf1;
    @FXML public ImageView tower10, tower11, tower12, tower13, tower14, tower15, tower16, tower17;
    // PLAYER 2
    @FXML public ImageView entrance20, entrance21, entrance22, entrance23, entrance24, entrance25, entrance26, entrance27, entrance28;
    @FXML public ImageView green20, green21, green22, green23, green24, green25, green26, green27, green28, green29;
    @FXML public ImageView red20, red21, red22, red23, red24, red25, red26, red27, red28, red29;
    @FXML public ImageView yellow20, yellow21, yellow22, yellow23, yellow24, yellow25, yellow26, yellow27, yellow28, yellow29;
    @FXML public ImageView pink20, pink21, pink22, pink23, pink24, pink25, pink26, pink27, pink28, pink29;
    @FXML public ImageView blue20, blue21, blue22, blue23, blue24, blue25, blue26, blue27, blue28, blue29;
    @FXML public ImageView greenProf2, redProf2, yellowProf2, pinkProf2, blueProf2;
    @FXML public ImageView tower20, tower21, tower22, tower23, tower24, tower25, tower26, tower27;


    @FXML public ImageView greenIsland0, redIsland0, yellowIsland0, pinkIsland0, blueIsland0, towerIsland0, mothernature0;
    @FXML public Label greenText0, redText0, yellowText0, pinkText0, blueText0, towerText0;
    @FXML public ImageView greenIsland1, redIsland1, yellowIsland1, pinkIsland1, blueIsland1, towerIsland1, mothernature1;
    @FXML public Label greenText1, redText1, yellowText1, pinkText1, blueText1, towerText1;
    @FXML public ImageView greenIsland2, redIsland2, yellowIsland2, pinkIsland2, blueIsland2, towerIsland2, mothernature2;
    @FXML public Label greenText2, redText2, yellowText2, pinkText2, blueText2, towerText2;
    @FXML public ImageView greenIsland3, redIsland3, yellowIsland3, pinkIsland3, blueIsland3, towerIsland3, mothernature3;
    @FXML public Label greenText3, redText3, yellowText3, pinkText3, blueText3, towerText3;
    @FXML public ImageView greenIsland4, redIsland4, yellowIsland4, pinkIsland4, blueIsland4, towerIsland4, mothernature4;
    @FXML public Label greenText4, redText4, yellowText4, pinkText4, blueText4, towerText4;
    @FXML public ImageView greenIsland5, redIsland5, yellowIsland5, pinkIsland5, blueIsland5, towerIsland5, mothernature5;
    @FXML public Label greenText5, redText5, yellowText5, pinkText5, blueText5, towerText5;
    @FXML public ImageView greenIsland6, redIsland6, yellowIsland6, pinkIsland6, blueIsland6, towerIsland6, mothernature6;
    @FXML public Label greenText6, redText6, yellowText6, pinkText6, blueText6, towerText6;
    @FXML public ImageView greenIsland7, redIsland7, yellowIsland7, pinkIsland7, blueIsland7, towerIsland7, mothernature7;
    @FXML public Label greenText7, redText7, yellowText7, pinkText7, blueText7, towerText7;
    @FXML public ImageView greenIsland8, redIsland8, yellowIsland8, pinkIsland8, blueIsland8, towerIsland8, mothernature8;
    @FXML public Label greenText8, redText8, yellowText8, pinkText8, blueText8, towerText8;
    @FXML public ImageView greenIsland9, redIsland9, yellowIsland9, pinkIsland9, blueIsland9, towerIsland9, mothernature9;
    @FXML public Label greenText9, redText9, yellowText9, pinkText9, blueText9, towerText9;
    @FXML public ImageView greenIsland10, redIsland10, yellowIsland10, pinkIsland10, blueIsland10, towerIsland10, mothernature10;
    @FXML public Label greenText10, redText10, yellowText10, pinkText10, blueText10, towerText10;
    @FXML public ImageView greenIsland11, redIsland11, yellowIsland11, pinkIsland11, blueIsland11, towerIsland11, mothernature11;
    @FXML public Label greenText11, redText11, yellowText11, pinkText11, blueText11, towerText11;


    /**
     * cleanParameters method cleans parameters on the gameboard
     */

    private void cleanParameters(){
        alreadyUsed = 0; //non serve qui, ma non fa mai male
        studentChoice = -1;
    }

    /**
     * if it's the right game phase assistant1 method plays assistant card number 1 sending the message to the socket
     */
    @FXML
    protected void assistant1() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("1");
    }

    /**
     *     if it's the right game phase assistant2 method plays assistant card number 2 sending the message to the socket
     */
    @FXML
    protected void assistant2() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("2");
    }

    /**
     * if it's the right game phase assistant3 method plays assistant card number 3 sending the message to the socket
     */
    @FXML
    protected void assistant3() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("3");
    }

    /**
     * if it's the right game phase assistant4 method plays assistant card number 4 sending the message to the socket
     */
    @FXML
    protected void assistant4() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("4");
    }

    /**
     * if it's the right game phase assistant5 method plays assistant card number 5 sending the message to the socket
     */
    @FXML
    protected void assistant5() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("5");
    }

    /**
     * if it's the right game phase assistant6 method plays assistant card number 6 sending the message to the socket
     */
    @FXML
    protected void assistant6() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("6");
    }

    /**
     * if it's the right game phase assistant7 method plays assistant card number 7 sending the message to the socket
     */
    @FXML
    protected void assistant7() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("7");
    }

    /**
     * if it's the right game phase assistant8 method plays assistant card number 8 sending the message to the socket
     */
    @FXML
    protected void assistant8() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("8");
    }

    /**
     * if it's the right game phase assistant9 method plays assistant card number 9 sending the message to the socket
     */
    @FXML
    protected void assistant9() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("9");
    }

    /**
     * if it's the right game phase assistant10 method plays assistant card number 10 sending the message to the socket
     */
    @FXML
    protected void assistant10() {
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && model.getCurrentPlayer() == myPlayer.getIndex())
            gui.getClientGUI().asyncWriteToSocket("10");
    }


    /**
     * update method controls every parameter on the game board and updates them
     */
    private void update() {
        if(model.getPlayers().size() == 2){
            schoolBoard2.setVisible(false);
            cloud2.setVisible(false);
            nickname2.setVisible(false);
            coins2.setVisible(false);
        }

        gamephase.setText("Game phase: " + model.getCurrentPhase().toString());
        currentplayer.setText("Current player: " + model.getPlayers().get(model.getCurrentPlayer()).getNickname());

        alreadyUsed = 0;
        for (Player p : model.getPlayers()) {
            if (p.getNickname().equals(nickname)) {
                nickname0.setText("You: " + p.getNickname());
                coins0.setText("Coins: " + p.getCoins());
                myPlayer = p;
                updatePlayerSchoolBoard(p, true);
                int[] deletedCards = new int[10];
                for(int n=0; n<10; n++){
                    deletedCards[n] = 0;
                }
                for(AssistantCard c: p.getAssistantDeck())
                    deletedCards[c.getValue()-1] = 1;

                for(int i=0; i< 10; i++){
                    if(deletedCards[i] == 0){
                        switch (i+1) {
                            case 1 -> assistant1.setVisible(false);
                            case 2 -> assistant2.setVisible(false);
                            case 3 -> assistant3.setVisible(false);
                            case 4 -> assistant4.setVisible(false);
                            case 5 -> assistant5.setVisible(false);
                            case 6 -> assistant6.setVisible(false);
                            case 7 -> assistant7.setVisible(false);
                            case 8 -> assistant8.setVisible(false);
                            case 9 -> assistant9.setVisible(false);
                            case 10 -> assistant10.setVisible(false);
                        }
                    }
                }
            } else {
                updatePlayerSchoolBoard(p, false);
                if(alreadyUsed == 0){
                    nickname1.setText("Player: " + p.getNickname());
                    coins1.setText("Coins: " + p.getCoins());
                } else {
                    nickname2.setText("Player: " + p.getNickname());
                    coins2.setText("Coins: " + p.getCoins());
                }
                alreadyUsed++;
            }
        }

        int[] deletedIslands = new int[12];
        for(int n=0; n<12; n++){
            deletedIslands[n] = 0;
        }
        for(Island i : model.getGameBoard().getIslands()){
            if(i.getIslandIndex() == 0){
                updateisland0(i);
                deletedIslands[0] = 1;
            } else if(i.getIslandIndex() == 1){
                updateisland1(i);
                deletedIslands[1] = 1;
            } else if(i.getIslandIndex() == 2){
                updateisland2(i);
                deletedIslands[2] = 1;
            } else if(i.getIslandIndex() == 3){
                updateisland3(i);
                deletedIslands[3] = 1;
            }  else if(i.getIslandIndex() == 4){
                updateisland4(i);
                deletedIslands[4] = 1;
            }  else if(i.getIslandIndex() == 5){
                updateisland5(i);
                deletedIslands[5] = 1;
            }  else if(i.getIslandIndex() == 6){
                updateisland6(i);
                deletedIslands[6] = 1;
            }  else if(i.getIslandIndex() == 7){
                updateisland7(i);
                deletedIslands[7] = 1;
            }  else if(i.getIslandIndex() == 8){
                updateisland8(i);
                deletedIslands[8] = 1;
            }  else if(i.getIslandIndex() == 9){
                updateisland9(i);
                deletedIslands[9] = 1;
            }  else if(i.getIslandIndex() == 10){
                updateisland10(i);
                deletedIslands[10] = 1;
            }  else if(i.getIslandIndex() == 11){
                updateisland11(i);
                deletedIslands[11] = 1;
            }
        }

        for(int is=0; is<12; is++){
            if(deletedIslands[is] == 0){
                switch (is) {
                    case 0 -> island0.setVisible(false);
                    case 1 -> island1.setVisible(false);
                    case 2 -> island2.setVisible(false);
                    case 3 -> island3.setVisible(false);
                    case 4 -> island4.setVisible(false);
                    case 5 -> island5.setVisible(false);
                    case 6 -> island6.setVisible(false);
                    case 7 -> island7.setVisible(false);
                    case 8 -> island8.setVisible(false);
                    case 9 -> island9.setVisible(false);
                    case 10 -> island10.setVisible(false);
                    case 11 -> island11.setVisible(false);
                }
            }
        }
        int n=0;
        for(Cloud c: model.getGameBoard().getClouds()){
            if(n==0){
                if(c.isTaken()){
                    cloudStudent00.setImage(null);
                    cloudStudent01.setImage(null);
                    cloudStudent02.setImage(null);
                    if(model.getPlayers().size() ==3)
                        cloudStudent03.setImage(null);

                } else {
                    setCloud(c.seeStudents().get(0).getColour(), cloudStudent00);
                    setCloud(c.seeStudents().get(1).getColour(), cloudStudent01);
                    setCloud(c.seeStudents().get(2).getColour(), cloudStudent02);
                    if(model.getPlayers().size() ==3)
                        setCloud(c.seeStudents().get(3).getColour(), cloudStudent03);
                }
            } else if(n==1){
                if(c.isTaken()){
                    cloudStudent10.setImage(null);
                    cloudStudent11.setImage(null);
                    cloudStudent12.setImage(null);
                    if(model.getPlayers().size() ==3)
                        cloudStudent13.setImage(null);

                } else {
                    setCloud(c.seeStudents().get(0).getColour(), cloudStudent10);
                    setCloud(c.seeStudents().get(1).getColour(), cloudStudent11);
                    setCloud(c.seeStudents().get(2).getColour(), cloudStudent12);
                    if(model.getPlayers().size() ==3)
                        setCloud(c.seeStudents().get(3).getColour(), cloudStudent13);
                }
            } else if(n==2){
                if(c.isTaken()){
                    cloudStudent20.setImage(null);
                    cloudStudent21.setImage(null);
                    cloudStudent22.setImage(null);
                    if(model.getPlayers().size() ==3)
                        cloudStudent23.setImage(null);

                } else {
                    setCloud(c.seeStudents().get(0).getColour(), cloudStudent20);
                    setCloud(c.seeStudents().get(1).getColour(), cloudStudent21);
                    setCloud(c.seeStudents().get(2).getColour(), cloudStudent22);
                    if(model.getPlayers().size() ==3)
                        setCloud(c.seeStudents().get(3).getColour(), cloudStudent23);
                }
            }
            n++;
        }

        if(model.isExpertMode()){
            n=0;
            for(CharacterCard cc: model.getGameBoard().getThreeCharacterCards()){
                if(n==0){
                    character0.setVisible(true);
                    setCharacter(cc.getIndex(), characterImg0);
                    characterText0.setText("Cost:          " + cc.getCost());
                } else if(n==1){
                    character1.setVisible(true);
                    setCharacter(cc.getIndex(), characterImg1);
                    characterText1.setText("Cost:          " + cc.getCost());
                } else if(n==2){
                    character2.setVisible(true);
                    setCharacter(cc.getIndex(), characterImg2);
                    characterText2.setText("Cost:          " + cc.getCost());
                }
                n++;
            }
        }
    }

    /**
     * setGui method sets gui
     * @param gui
     */
    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }


    /**
     * setModel method sets model
     * @param model
     */
    public void setModel(Model model) {
        this.model = model;
        System.out.println("Ricevuto il model");
        update();
    }

    /**
     * setNickname method sets nickname
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * chooseIsland0 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
   @FXML
   protected void chooseIsland0(){
       if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
           gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(0));
       } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
           moveMotherNature(0);
       }
       cleanParameters();
    }
    /**
     * chooseIsland1 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland1(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(1));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(1);
        }
        cleanParameters();
    }
    /**
     * chooseIsland2 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland2(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(2));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(2);
        }
        cleanParameters();
    }

    /**
     * chooseIsland3 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */


    @FXML
    protected void chooseIsland3(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(3));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(3);
        }
        cleanParameters();
    }
    /**
     * chooseIsland4 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland4(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(4));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(4);
        }
        cleanParameters();
    }

    /**
     * chooseIsland5 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland5(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(5));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(5);
        }
        cleanParameters();
    }
    /**
     * chooseIsland6 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland6(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(6));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(6);
        }
        cleanParameters();
    }
    /**
     * chooseIsland7 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland7(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(7));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(7);
        }
        cleanParameters();
    }
    /**
     * chooseIsland8 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland8(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(8));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(8);
        }
        cleanParameters();
    }

    /**
     * chooseIsland9 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland9(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(9));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(9);
        }
        cleanParameters();
    }

    /**
     * chooseIsland10 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland10(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(10));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(10);
        }
        cleanParameters();
    }

    /**
     * chooseIsland11 gets the input from the model (if it's the right game phase), and sent to the socket a
     * message with the right move (move students or move mother nature)
     */
    @FXML
    protected void chooseIsland11(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice >= 0 && studentChoice <= 4){
            gui.getClientGUI().asyncWriteToSocket(studentChoice + "," + islandAbsolutePosition(11));
        } else if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && model.getCurrentPlayer() == myPlayer.getIndex()){
            moveMotherNature(11);
        }
        cleanParameters();
    }

    /**
     * moveMotherNature method moves mother nature on the correct island index parameter set
     * @param islandIndex
     */
    private void moveMotherNature(int islandIndex){
        int islandPosition = islandAbsolutePosition(islandIndex);
        islandPosition -= model.getGameBoard().getMotherNature();
        if(islandPosition<0)
            islandPosition += model.getGameBoard().getIslands().size();

        gui.getClientGUI().asyncWriteToSocket(String.valueOf(islandPosition));
    }

    private int islandAbsolutePosition(int islandIndex){
        for(int i=0; i<model.getGameBoard().getIslands().size(); i++)
            if(model.getGameBoard().getIslands().get(i).getIslandIndex() == islandIndex)
                return i;

        return -1;
    }

    /**
     * chooseStudent0 method gets the student in position 0 from the entrance
     */
    @FXML
    protected void chooseStudent0(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex()){
            studentChoice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        } else
            cleanParameters();
    }
    /**
     * chooseStudent1 method gets the student in position 1 from the entrance
     */
    @FXML
    protected void chooseStudent1(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex()){
            studentChoice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(1).getColour().ordinal();
        } else
            cleanParameters();
    }
    /**
     * chooseStudent2 method gets the student in position 2 from the entrance
     */
    @FXML
    protected void chooseStudent2(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex()){
            studentChoice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(2).getColour().ordinal();
        } else
            cleanParameters();
    }
    /**
     * chooseStudent3 method gets the student in position 3 from the entrance
     */
    @FXML
    protected void chooseStudent3(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex()){
            studentChoice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(3).getColour().ordinal();
        } else
            cleanParameters();
    }
    /**
     * chooseStudent4 method gets the student in position 4 from the entrance
     */
    @FXML
    protected void chooseStudent4(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex()){
            studentChoice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(4).getColour().ordinal();
        } else
            cleanParameters();
    }
    /**
     * chooseStudent5 method gets the student in position 5 from the entrance
     */
    @FXML
    protected void chooseStudent5(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex()){
            studentChoice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(5).getColour().ordinal();
        } else
            cleanParameters();
    }
    /**
     * chooseStudent6 method gets the student in position 6 from the entrance
     */
    @FXML
    protected void chooseStudent6(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex()){
            studentChoice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(6).getColour().ordinal();
        } else
            cleanParameters();
    }

    /**
     * chooseStudent7 method gets the student in position 7 from the entrance
     */
    @FXML
    protected void chooseStudent7(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex()){
            studentChoice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(7).getColour().ordinal();
        } else
            cleanParameters();
    }

    /**
     * chooseStudent8 method gets the student in position 8 from the entrance
     */
    @FXML
    protected void chooseStudent8(){
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex()){
            studentChoice = myPlayer.getPlayerSchoolBoard().getStudentsEntrance().get(8).getColour().ordinal();
        } else
            cleanParameters();
    }


    @FXML
    protected void diningRoomGreen() {
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice == 0){
            gui.getClientGUI().asyncWriteToSocket("0");
        }
        cleanParameters();
    }
    @FXML
    protected void diningRoomRed() {
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice == 1){
            gui.getClientGUI().asyncWriteToSocket("1");
        }
        cleanParameters();
    }
    @FXML
    protected void diningRoomYellow() {
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice == 2){
            gui.getClientGUI().asyncWriteToSocket("2");
        }
        cleanParameters();
    }
    @FXML
    protected void diningRoomPink() {
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice == 3){
            gui.getClientGUI().asyncWriteToSocket("3");
        }
        cleanParameters();
    }
    @FXML
    protected void diningRoomBlue() {
        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && model.getCurrentPlayer() == myPlayer.getIndex() && studentChoice == 4){
            gui.getClientGUI().asyncWriteToSocket("4");
        }
        cleanParameters();
    }

    /**
     * chooseCloud0 method gets the cloud number 0
     */
    @FXML
    protected void chooseCloud0(){
        if(model.getCurrentPhase().equals(GamePhase.ChooseCloud) && model.getCurrentPlayer() == myPlayer.getIndex()){
            gui.getClientGUI().asyncWriteToSocket("0");
        } else
            cleanParameters();
    }

    /**
     * chooseCloud1 method gets the cloud number 1
     */
    @FXML
    protected void chooseCloud1(){
        if(model.getCurrentPhase().equals(GamePhase.ChooseCloud) && model.getCurrentPlayer() == myPlayer.getIndex()){
            gui.getClientGUI().asyncWriteToSocket("1");
        } else
            cleanParameters();
    }

    /**
     * chooseCloud2 method gets the cloud number 2
     */
    @FXML
    protected void chooseCloud2(){
        if(model.getCurrentPhase().equals(GamePhase.ChooseCloud) && model.getCurrentPlayer() == myPlayer.getIndex()){
            gui.getClientGUI().asyncWriteToSocket("2");
        } else
            cleanParameters();
    }


    /**
     * getCharacterFXML method gets the Characters cards resources from fmxl files
     * @param index
     * @return
     */

    private FXMLLoader getCharacterFXML(int index){
        return switch (index) {
            case 1 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character1.fxml"));
            case 2 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character2.fxml"));
            case 3 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character3.fxml"));
            case 4 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character4.fxml"));
            case 5 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character5.fxml"));
            case 6 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character6.fxml"));
            case 7 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character7.fxml"));
            case 8 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character8.fxml"));
            case 9 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character9.fxml"));
            case 10 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character10.fxml"));
            case 11 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character11.fxml"));
            case 12 -> new FXMLLoader(getClass().getResource("/fxml/Characters/Character12.fxml"));
            default -> null;
        };
    }


    /**
     * chooseCharacter0 method sets character card 0 up to be ready to play
     * @throws IOException
     */

    @FXML
    protected void chooseCharacter0() throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gui.getStage());

        FXMLLoader character = getCharacterFXML(model.getGameBoard().getThreeCharacterCards().get(0).getIndex());
        if(character != null){
            Scene dialogScene = new Scene(character.load());
            Character c = character.getController();
            c.setGui(this.gui, dialog);
            c.setModel(model, 0);
            dialog.setScene(dialogScene);
            dialog.show();
        }

        cleanParameters();
    }

    /**
     * chooseCharacter1 method sets character card 1 up to be ready to play
     * @throws IOException
     */
    @FXML
    protected void chooseCharacter1() throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gui.getStage());

        FXMLLoader character = getCharacterFXML(model.getGameBoard().getThreeCharacterCards().get(1).getIndex());
        if(character != null){
            Scene dialogScene = new Scene(character.load());
            Character c = character.getController();
            c.setGui(this.gui, dialog);
            c.setModel(model, 1);
            dialog.setScene(dialogScene);
            dialog.show();
        }

        cleanParameters();
    }

    /**
     * chooseCharacter2 method sets character card 2 up to be ready to play
     * @throws IOException
     */
    @FXML
    protected void chooseCharacter2() throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gui.getStage());

        FXMLLoader character = getCharacterFXML(model.getGameBoard().getThreeCharacterCards().get(2).getIndex());
        if(character != null){
            Scene dialogScene = new Scene(character.load());
            Character c = character.getController();
            c.setGui(this.gui, dialog);
            c.setModel(model, 2);
            dialog.setScene(dialogScene);
            dialog.show();
        }

        cleanParameters();
    }


    /**
     * updatePlayerSchoolBoard method controls every parameter of schoolboard and updates them
     * @param p
     * @param mainPlayer
     */
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

        int red = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Red);
        for (i = 0; i < red; i++)
            getRed(i, mainPlayer).setVisible(true);
        for (int j = i; j < 10; j++)
            getRed(j, mainPlayer).setVisible(false);

        int yellow = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Yellow);
        for (i = 0; i < yellow; i++)
            getYellow(i, mainPlayer).setVisible(true);
        for (int j = i; j < 10; j++)
            getYellow(j, mainPlayer).setVisible(false);

        int pink = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Pink);
        for (i = 0; i < pink; i++)
            getPink(i, mainPlayer).setVisible(true);
        for (int j = i; j < 10; j++)
            getPink(j, mainPlayer).setVisible(false);

        int blue = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.Blue);
        for (i = 0; i < blue; i++)
            getBlue(i, mainPlayer).setVisible(true);
        for (int j = i; j < 10; j++)
            getBlue(j, mainPlayer).setVisible(false);


        int[] visitedProfessors = new int[5];
        for(int n=0; n<5; n++){
            visitedProfessors[n] = 0;
        }
        for (Professor pr : p.getPlayerSchoolBoard().getProfessors()) {
            getProfessor(pr.getProfessorColour().ordinal(), mainPlayer).setVisible(true);
            visitedProfessors[pr.getProfessorColour().ordinal()] = 1;
        }
        for(int n=0; n<5; n++){
            if(visitedProfessors[n] == 0)
                getProfessor(n, mainPlayer).setVisible(false);
        }

        k = 0;
        for (Tower tr : p.getPlayerSchoolBoard().getTowers()) {
            setTower(k, tr.getTowerColor(), mainPlayer);
            k++;
        }
        for(int t=k; t < 8; t++){
            getTower(t, mainPlayer).setImage(null);
        }

        setPlayedAssistantCard(p, mainPlayer);
    }

    /**
     * setEntrance method sets entrance parameters/images/interfaces (GUI side)
     * @param i
     * @param colour
     * @param mainPlayer
     */
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

    /**
     * setTower method sets towers interfaces (GUI side)
     * @param i
     * @param colour
     * @param mainPlayer
     */
    private void setTower(int i, TowerColour colour, boolean mainPlayer) {
        if (colour.equals(TowerColour.Black))
            getTower(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/black_tower.png"))));
        else if (colour.equals(TowerColour.White))
            getTower(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/white_tower.png"))));
        else if (colour.equals(TowerColour.Grey))
            getTower(i, mainPlayer).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/grey_tower.png"))));

    }

    /**
     * setCloud method sets Cloud interfaces (GUI side)
     * @param colour
     * @param img
     */
    private void setCloud(Colour colour, ImageView img) {
        if (colour.equals(Colour.Green))
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_green.png"))));
        else if (colour.equals(Colour.Red))
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_red.png"))));
        else if (colour.equals(Colour.Yellow))
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_yellow.png"))));
        else if (colour.equals(Colour.Pink))
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_pink.png"))));
        else if (colour.equals(Colour.Blue))
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/student_blue.png"))));

    }

    /**
     * setCharacter method sets Character interfaces
     * @param index
     * @param img
     */
    private void setCharacter(int index, ImageView img) {
        if(index == 1)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character1.png"))));
        else if(index == 2)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character2.png"))));
        else if(index == 3)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character3.png"))));
        else if(index == 4)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character4.png"))));
        else if(index == 5)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character5.png"))));
        else if(index == 6)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character6.png"))));
        else if(index == 7)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character7.png"))));
        else if(index == 8)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character8.png"))));
        else if(index == 9)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character9.png"))));
        else if(index == 10)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character10.png"))));
        else if(index == 11)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character11.png"))));
        else if(index == 12)
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/character12.png"))));

    }

    /**
     * setPlayedAssistantCard method sets Images of assistants cards
     * @param p
     * @param mainPlayer
     */
    private void setPlayedAssistantCard(Player p, boolean mainPlayer){
        if(mainPlayer){
            lastAssistant0.setImage(getAssistantCard(p.getCurrentCard().getValue()));
        } else if(alreadyUsed == 0){
            lastAssistant1.setImage(getAssistantCard(p.getCurrentCard().getValue()));
        } else {
            lastAssistant2.setImage(getAssistantCard(p.getCurrentCard().getValue()));
        }
    }

    /**
     * getAssistantCard method gets images and resources of ecery assistantcards
     * @param value
     * @return assistantCard image
     */
    private Image getAssistantCard(int value){
        return switch (value) {
            case 1 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (1).png")));
            case 2 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (2).png")));
            case 3 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (3).png")));
            case 4 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (4).png")));
            case 5 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (5).png")));
            case 6 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (6).png")));
            case 7 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (7).png")));
            case 8 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (8).png")));
            case 9 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (9).png")));
            case 10 -> new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Graphics/Assistente (10).png")));
            default -> null;
        };
    }

    /**
     * getGreen method gets green
     * @param pos
     * @param mainPlayer
     * @return
     */
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