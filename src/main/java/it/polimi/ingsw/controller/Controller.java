package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.gameboard.characters.*;
import it.polimi.ingsw.model.gameboard.characters.CharacterCard;
import it.polimi.ingsw.model.gameboard.Cloud;
import it.polimi.ingsw.model.gameboard.GameBoard;
import it.polimi.ingsw.model.player.*;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.utils.gameMessage;

import java.util.ArrayList;

/**
 *  In this class we manage the main actions of the match.
 */

public class Controller implements Observer<PlayerMove> {

    private Model model;

    /**
     * performmove Method permits us to filter a move, depending on the number of parameters.
     * There are 6 cases and it's possible to understand which move is played and in
     * which case we are, just analyzing the number of parameters.
     * Every parameters is an integer and it means an indication about the move.
     * for example the move: "movemothernature" will have 1 parameters ( in this case param2) which is
     * the number of the movements of mother nature from her position.
     * So param2 is the parameters of the movements.
     * Others move get more parameters like Character Cards which have differents
     * needs like moving students from school board to islands and at the same time they make others moves.
     *
     * */
    private synchronized void performMove(PlayerMove move){
        if(getCurrentPlayer() != move.getPlayer().getIndex()){
            move.getView().reportError(gameMessage.wrongTurnMessage);
            return;
        }
        //System.out.println("sei in performove " + move.getParam1() + " " + move.getParam2() + " " +  move.getParam3() + " " +  move.getParam4() + " " +  move.getParam5() + " " +  move.getParam6() + " " +  move.getParam7() + " " +  move.getParam8());

        int result = 0;
        switch (move.getParam1()){
            case 0: {
                result = playAssistantCard(move.getPlayer().getIndex(), move.getParam2());
                break;
            }

            case 1: {
                if(move.getParam3() == -1)
                    result = moveStudentToDiningRoom(move.getPlayer().getIndex(), move.getParam2());
                else
                    result = moveStudentToIsland(move.getPlayer().getIndex(), move.getParam2(), move.getParam3());

                break;
            }

            case 2: {
                result = moveMotherNature(move.getPlayer().getIndex(), move.getParam2());
                break;
            }

            case 3: {
                result = chooseCloud(move.getPlayer().getIndex(), move.getParam2());
                break;
            }

            case 5: { //da valutarne il senso
                String winner = getTheWinner();
                System.out.println(winner + "WON THE GAME!\n\n");
                return;
            }

            case 100: {
                if(!Parameters.expertMode){
                    move.getView().reportError(gameMessage.invalidMoveMessage);
                    return;
                }

                if(move.getParam2() == 1){
                    result = playCharacterCard1(move.getPlayer().getIndex(), move.getParam2(), move.getParam3(), move.getParam4());
                } else if(move.getParam2() == 2){
                    result = playCharacterCard2(move.getPlayer().getIndex(), move.getParam2());
                } else if(move.getParam2() == 3){
                    result = playCharacterCard3(move.getPlayer().getIndex(), move.getParam2(), move.getParam3());
                } else if(move.getParam2() == 4){
                    result = playCharacterCard4(move.getPlayer().getIndex(), move.getParam2());
                } else if(move.getParam2() == 5){
                    result = playCharacterCard5(move.getPlayer().getIndex(), move.getParam2(), move.getParam3());
                } else if(move.getParam2() == 6){
                    result = playCharacterCard6(move.getPlayer().getIndex(), move.getParam2());
                } else if(move.getParam2() == 7){
                    if(move.getParam5() == -1)
                        result = playCharacterCard7(move.getPlayer().getIndex(), move.getParam2(), move.getParam3(), move.getParam4());
                    else if(move.getParam7() == -1)
                        result = playCharacterCard7(move.getPlayer().getIndex(), move.getParam2(), move.getParam3(), move.getParam4(), move.getParam5(), move.getParam6());
                    else
                        result = playCharacterCard7(move.getPlayer().getIndex(), move.getParam2(), move.getParam3(), move.getParam4(), move.getParam5(), move.getParam6(), move.getParam7(), move.getParam8());
                } else if(move.getParam2() == 8){
                    result = playCharacterCard8(move.getPlayer().getIndex(), move.getParam2());
                } else if(move.getParam2() == 9){
                    result = playCharacterCard9(move.getPlayer().getIndex(), move.getParam2(), move.getParam3());
                } else if(move.getParam2() == 10){
                    if(move.getParam5() == -1)
                        result = playCharacterCard10(move.getPlayer().getIndex(), move.getParam2(), move.getParam3(), move.getParam4());
                    else
                        result = playCharacterCard10(move.getPlayer().getIndex(), move.getParam2(), move.getParam3(), move.getParam4(), move.getParam5(), move.getParam6());
                } else if(move.getParam2() == 11){
                    result = playCharacterCard11(move.getPlayer().getIndex(), move.getParam2(), move.getParam3());
                } else if(move.getParam2() == 12){
                    result = playCharacterCard12(move.getPlayer().getIndex(), move.getParam2(), move.getParam3());
                }
                break;
            }
        }
        if(result == 1)
           model.performMove(move.getPlayer());
        else
            move.getView().reportError(gameMessage.invalidMoveMessage);
    }



    @Override
    public void update(PlayerMove message) {
        performMove(message);
    }


    //Gets the players of the match
    public ArrayList<Player> getPlayers() {
        return model.getPlayers();
    }

    //Gets the gameBoard instance
    public GameBoard getGameBoard() {
        return model.getGameBoard();
    }

    public void setParameters(int numPlayers, Boolean expertMode){
        Parameters.setParameters(numPlayers, expertMode);
        this.model = new Model();
    }


    public int addPlayer(Player player){

        if(model.getPlayers().size() < Parameters.numPlayers) {
            model.getPlayers().add(player);
            return model.getPlayers().size()-1;
        }
        else{
            //qui bisogna mettere una exception che nella partita ci sono già 4 giocatori
            return -1;
        }
    }

    /**
     * init method initialize the match choosing randomly the disposition of mother
     * nature and students on the board.
     * */
    public void init(){   //initialazing the match
        double casual = Math.random()*12; //(PUNTO 2)
        int mn = (int) casual;
        model.setModelParameters(Parameters.numPlayers, Parameters.expertMode);

        model.getGameBoard().setMotherNature(mn);  //positioning mother nature

        ArrayList<Student> arr = new ArrayList<>();  //(PUNTO 3) creating students
        for (Colour c : Colour.values()) {
            for(int i=0;i<2;i++){
                Student s = new Student(c);
                arr.add(s);
            }
        }
        model.getGameBoard().getBag().fill(arr);

        for (int i=0; i<12; i++){
            if(i != model.getGameBoard().getMotherNature() && i != (model.getGameBoard().getMotherNature() + 6)%12) //doesn't place students on the island with mothernature and the opposite one
                model.getGameBoard().addStudentOnIsland(i, model.getGameBoard().getBag().draw());

        }

        for (Colour c : Colour.values()) {  //creating students for the bag
            for(int i=0;i<24;i++){
                Student s = new Student(c);
                arr.add(s);
            }
        }
        model.getGameBoard().getBag().fill(arr);

        for (Player p: model.getPlayers()){
            for (int i=0; i<Parameters.entranceStudents; i++)
                p.getPlayerSchoolBoard().getStudentsEntrance().add(model.getGameBoard().getBag().draw());
        }

        if(Parameters.expertMode){
            model.getGameBoard().chooseThreeCards();
        }

        model.setCurrentPlayer(0);
        for(int i = 0; i<Parameters.numPlayers; i++){
            model.getPlayersTurnOrder()[i] = model.getPlayers().get(i);
        }

        addStudentsOnClouds(); // adding students on clouds

        model.setCurrentPhase(GamePhase.PlayAssistantCard);
    }


    //pianification phase 1 adds students on clouds
    private void addStudentsOnClouds(){
        int num = 0;
        for(Cloud c: model.getGameBoard().getClouds()){
            for (int i = 0; i < Parameters.numCloudStudents; i++)
                if(model.getGameBoard().getBag().getSize() > 0)
                    model.getGameBoard().addStudentOnCloud(num, model.getGameBoard().getBag().draw());
            num++;
            c.setTaken(false);
        }
    }

    //pianification phase 2 , play an assistant card and control if it is possible to play it (already played or the
    // opposite player played it
    public int playAssistantCard(int playerIndex, int priority){
        if(model.getCurrentPhase().equals(GamePhase.PlayAssistantCard) && playerIndex == model.getCurrentPlayer() && priority > 0 && priority <= 10) {
            boolean alreadyPlayed = false;
            for(int i=0; i<model.getPhaseCounter(); i++) {
                if (priority == model.getPlayersTurnOrder()[i].getCurrentCard().getValue()) {
                    alreadyPlayed = true;
                    break;
                }
            }
            if(alreadyPlayed) {
                int check = 0;
                for (AssistantCard as : model.getPlayers().get(playerIndex).getAssistantDeck()) {
                    for (int i = 0; i < model.getPhaseCounter(); i++) {
                        if (as.getValue() == model.getPlayersTurnOrder()[i].getCurrentCard().getValue()) {
                            check++;
                        }
                    }
                }

                if (check != model.getPlayers().get(playerIndex).getAssistantDeck().size())
                    return -1;
            }

            boolean found = false;
            ArrayList<AssistantCard> clone = (ArrayList<AssistantCard>) model.getPlayers().get(playerIndex).getAssistantDeck().clone();
            for (AssistantCard as : clone)
                if (as.getValue() == priority){
                    model.getPlayers().get(playerIndex).playAssistantCard(as);//QUI ERRORE CUNCURRENT!!!!

                    found = true;
                    break;
                }

            if(found == false) return -1; //card is already played, no sense to play it, wait for an other card
            if(model.getPhaseCounter() < Parameters.numPlayers-1) {   //AGGIUNTA GIUS ALTRIMENTI SFORA LA DIMENSIONE DELL'ARRAY
                model.setCurrentPlayer(model.getPlayersTurnOrder()[model.getPhaseCounter() + 1].getIndex());

            }
            model.setPhaseCounter(model.getPhaseCounter() + 1);
            if (model.getPhaseCounter() == Parameters.numPlayers) {
                model.setCurrentPhase(GamePhase.MoveStudents);
                model.setPhaseCounter(0);
                this.orderPlayerActionPhase();
                model.setCurrentPlayer(model.getPlayersTurnOrder()[0].getIndex());

            }
            return 1;
        }
        else return -1;
    }

    private void orderPlayerActionPhase(){  // bubble sort algorithm
        for(int i = 0; i < model.getPlayersTurnOrder().length; i++){
            boolean swap = false;
            for(int j = 0; j < model.getPlayersTurnOrder().length-1; j++){
                if(model.getPlayersTurnOrder()[j].getCurrentCard().getValue() > model.getPlayersTurnOrder()[j+1].getCurrentCard().getValue()) {
                    Player k = model.getPlayersTurnOrder()[j];
                    model.getPlayersTurnOrder()[j] = model.getPlayersTurnOrder()[j+1];
                    model.getPlayersTurnOrder()[j+1] = k;
                    swap = true; //swap happened
                }
            }
            if(!swap) break;
        }
    }

    //Action phase 1
    public int moveStudentToIsland(int playerIndex, int colour, int IslandPosition){

        boolean checkStudentColor = false; // check if it has that colour
        for(Student s: model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance())
            if(Colour.values()[colour] == s.getColour())
                checkStudentColor = true;

        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && playerIndex == model.getCurrentPlayer() && checkStudentColor && colour >= 0 && colour < Colour.values().length && IslandPosition >= 0 && IslandPosition < model.getGameBoard().getIslands().size()){ // if the phase is correct,the player is correct the colour is correctand the informations are correct then move students to island
            model.getPlayers().get(playerIndex).getPlayerSchoolBoard().moveStudentToIsland(colour, model.getGameBoard().getIslands().get(IslandPosition));

            model.setPhaseCounter(model.getPhaseCounter() + 1);
            if(model.getPhaseCounter() == Parameters.numCloudStudents){
                model.setCurrentPhase(GamePhase.MoveMotherNature);
                model.setPhaseCounter(0);
            }
            return 1;
        }
        else return -1;
    }
    //Action phase 1
    public int moveStudentToDiningRoom(int playerIndex, int colour){
        if (model.getCurrentPhase().equals(GamePhase.MoveStudents) && playerIndex == model.getCurrentPlayer() && colour >= 0 && colour < Colour.values().length && model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colour]) < 10){

            boolean checkStudentColor = false; // check the student's colour
            for (Student s : model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance())
                if (Colour.values()[colour] == s.getColour())
                    checkStudentColor = true;

            if(checkStudentColor){
                boolean coin; //return true id the player deserve a coin
                coin = model.getPlayers().get(playerIndex).getPlayerSchoolBoard().moveStudentToDiningRoom(colour);
                if (Parameters.expertMode && coin)
                    if(model.getGameBoard().getOneCoin())
                        model.getPlayers().get(playerIndex).addCoin();

                //checking who has the professor
                checkProfessorProperty(colour);

                model.setPhaseCounter(model.getPhaseCounter() + 1);
                if (model.getPhaseCounter()  == Parameters.numCloudStudents) {
                    model.setCurrentPhase(GamePhase.MoveMotherNature);
                    model.setPhaseCounter(0);
                }

                return 1;
            } else return -1;
        } else return -1;
    }
    private void checkProfessorProperty(int colorIndex){
        Player oldProfessorOwner = null;
        for (Player p : model.getPlayers())  //checking who had the professor
            for (Professor pr : p.getPlayerSchoolBoard().getProfessors())
                if (pr.getProfessorColour().equals(Colour.values()[colorIndex])){
                    oldProfessorOwner = p;
                    break;
                }

        ArrayList<Player> rank = new ArrayList<>();
        int max = 0;
        for (Player p : model.getPlayers()){  //finding the player with most students
            if(max < p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colorIndex])){
                max = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colorIndex]);
                rank.clear();
                rank.add(p);
            }else if (max == p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colorIndex]) && max != 0){  //qui ho parità
                rank.add(p);
            }
        }

        if(rank.size() == 1){
            if(oldProfessorOwner == null){
                rank.get(0).getPlayerSchoolBoard().addProfessor(Colour.values()[colorIndex]);
            } else if(!oldProfessorOwner.equals(rank.get(0))){
                oldProfessorOwner.getPlayerSchoolBoard().removeProfessor(Colour.values()[colorIndex]);
                rank.get(0).getPlayerSchoolBoard().addProfessor(Colour.values()[colorIndex]);
            }
        }
    }

    //Action Phase punto 2.1
    public int moveMotherNature(int playerIndex, int movements){ //checking if player has the rights
        try {
            if(model.getCurrentPhase().equals(GamePhase.MoveMotherNature) && playerIndex == model.getCurrentPlayer()){
                int maxMovements = model.getPlayers().get(model.getCurrentPlayer()).getCurrentCard().getMovements();
                if(Parameters.expertMode && movements <= maxMovements+2){
                    for(CharacterCard c: model.getGameBoard().getThreeCharacterCards())
                        if(c.getIndex() == 4 && ((Character4) c).isEffectFlag()){
                            maxMovements += 2;
                            ((Character4) c).disableEffect();
                        }
                }
                if(movements > 0 && movements <= maxMovements){
                    int newPos = (model.getGameBoard().getMotherNature() + movements)%model.getGameBoard().getIslands().size();
                    model.getGameBoard().setMotherNature(newPos);//moves motherNature by specified movements
                    System.out.println("stai per entrare in checkInfluence");
                    this.checkIslandInfluence(newPos, playerIndex);
                } else return -1;

                if(!model.getCurrentPhase().equals(GamePhase.GameEnded))
                    model.setCurrentPhase(GamePhase.ChooseCloud);

                return 1;
            }
            else return -1;
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return -1;
    }

    //action phase 2 punto 2.2 //checking who controls the island, if it's a different player i'll change towers
    private void checkIslandInfluence(int islandIndex, int playerIndex){
        boolean card6noTowerFlag = false;
        int card9color = -1;
        int card8twoMorePointsPlayer = -1;
        if(Parameters.expertMode){
            for(CharacterCard c: model.getGameBoard().getThreeCharacterCards())
                if(c.getIndex() == 5 && model.getGameBoard().getIslands().get(islandIndex).getNoEntry() > 0){
                    model.getGameBoard().getIslands().get(islandIndex).removeNoEntry();
                    ((Character5) c).addNoEntry();
                    return;  // moving on like mother nature has never been here
                } else if(c.getIndex() == 6 && ((Character6) c).isEffectFlag()){
                    ((Character6) c).disableEffect();
                    card6noTowerFlag = true;
                } else if(c.getIndex() == 8 && ((Character8) c).isEffectFlag()){
                    ((Character8) c).disableEffect();
                    card8twoMorePointsPlayer = playerIndex;
                } else if(c.getIndex() == 9 && ((Character9) c).isEffectFlag()){
                    ((Character9) c).disableEffect();
                    card9color = ((Character9) c).getColor();
                }

        }
        // checking who has the influences
        Player newPlayer = model.getGameBoard().getIslands().get(islandIndex).Influence(model.getPlayers(), card6noTowerFlag, card8twoMorePointsPlayer, card9color);

        if (newPlayer!=null){
            Player oldPlayer = null;  // checking who owned the island
            for (Player pl: model.getPlayers()){
                if(pl.PlayerTowerColor().equals(model.getGameBoard().getIslands().get(islandIndex).getTowerColor()))
                    oldPlayer = pl;
            }

            if(oldPlayer == null || !oldPlayer.equals(newPlayer)){
                //giving back towers to the previous player and replace them with the towers of the new player
                for(int i=0; i<=model.getGameBoard().getIslands().get(islandIndex).getNumTower(); i++){
                    if(oldPlayer != null)
                        oldPlayer.getPlayerSchoolBoard().addTower(oldPlayer.PlayerTowerColor());
                    newPlayer.getPlayerSchoolBoard().getTowers().remove(0);
                }
                if(oldPlayer == null){
                    this.getGameBoard().getIslands().get(islandIndex).setNumTower(1);
                }

                this.getGameBoard().getIslands().get(islandIndex).changeTowerColor(newPlayer.PlayerTowerColor());

                if(newPlayer.getPlayerSchoolBoard().getTowers().size() < 1){
                    model.setWinner(newPlayer);
                    model.setCurrentPhase(GamePhase.GameEnded);
                    return;
                }

                checkIslandFusion(islandIndex);
            }
        }
        //if none has the influence or if it's a draw, nothing happen
    }
    //action phase punto 2.3
    private void checkIslandFusion(int islandIndex){
        //if the colour of the tower on one island is the same of the next one...
        while (model.getGameBoard().getIslands().get(islandIndex).getTowerColor().equals(model.getGameBoard().getIslands().get((islandIndex+1)%model.getGameBoard().getIslands().size()).getTowerColor())){
            if(Parameters.expertMode) {
                for (CharacterCard c : model.getGameBoard().getThreeCharacterCards())
                    if (c.getIndex() == 3 && ((Character3) c).isEffectFlag() && model.getGameBoard().getMotherNature() >= (islandIndex + 1) % model.getGameBoard().getIslands().size()) {
                        int mn = model.getGameBoard().getMotherNature() - 1;
                        if (mn == -1)
                            mn = model.getGameBoard().getIslands().size() - 1;
                        model.getGameBoard().setMotherNature(mn);
                    }
            }
            model.getGameBoard().islandFusion(islandIndex, (islandIndex+1)%model.getGameBoard().getIslands().size());
        }

        int newPosition = islandIndex;
        int newPosition2 = newPosition-1;
        if (newPosition2 == -1)
            newPosition2 = model.getGameBoard().getIslands().size()-1;
        //if the colour of the tower on one island is the same of the previous one...
        while (model.getGameBoard().getIslands().get(newPosition).getTowerColor().equals(model.getGameBoard().getIslands().get(newPosition2).getTowerColor())){
            model.getGameBoard().islandFusion(newPosition2, newPosition);

            if(!Parameters.expertMode){
                if(newPosition2 == model.getGameBoard().getIslands().size())
                    newPosition2 = model.getGameBoard().getIslands().size()-1;

                model.getGameBoard().setMotherNature(newPosition2);
            } else {
                boolean card3 = false;
                for(CharacterCard c: model.getGameBoard().getThreeCharacterCards())
                    if(c.getIndex() == 3 && ((Character3) c).isEffectFlag())
                        card3 = true;

                if(!card3){
                    if(newPosition2 == model.getGameBoard().getIslands().size())
                        newPosition2 = model.getGameBoard().getIslands().size()-1;

                    model.getGameBoard().setMotherNature(newPosition2);
                }
                else if(model.getGameBoard().getMotherNature() >= (newPosition2+1)%(model.getGameBoard().getIslands().size()+1)){
                    int mn = model.getGameBoard().getMotherNature()-1;
                    if(mn == -1)
                        mn = model.getGameBoard().getIslands().size()-1;
                    model.getGameBoard().setMotherNature(mn);
                }
            }

            newPosition--;
            if(newPosition == -1)
                newPosition = model.getGameBoard().getIslands().size()-1;
            newPosition2 = newPosition-1;
            if (newPosition2 == -1)
                newPosition2 = model.getGameBoard().getIslands().size()-1;
        }

        if(model.getGameBoard().getIslands().size() <= 3){
            endGame();
            return;
        }
    }

    //Action phase punto 3
    public int chooseCloud(int playerIndex, int cloudPosition){
        if(model.getCurrentPhase().equals(GamePhase.ChooseCloud) && playerIndex == model.getCurrentPlayer() && cloudPosition >= 0 && cloudPosition < Parameters.numClouds && !model.getGameBoard().getClouds().get(cloudPosition).isTaken()){
            ArrayList<Student> stud = model.getGameBoard().getClouds().get(cloudPosition).getStudents();

            for (Student s: stud){ //qui e alla fine del metodo init() farei un nuovo metodo add che controlla anche se non viene superato il limite di studenti all'entrata
                model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(s);
            }

            if(Parameters.expertMode){
                for(CharacterCard c: model.getGameBoard().getThreeCharacterCards())
                    if(c.getIndex() == 2 && ((Character2) c).isEffectFlag()){
                        ((Character2) c).disableEffect();
                        for(int i=0; i<5; i++)
                            checkProfessorProperty(i);
                    }
            }

            model.setPlayerPhaseCounter(model.getPlayerPhaseCounter() + 1);
            if(model.getPlayerPhaseCounter() == Parameters.numPlayers){
                model.setPlayerPhaseCounter(0);
                addStudentsOnClouds();
                orderPlayersAssistantCard();
                model.setCurrentPhase(GamePhase.PlayAssistantCard);


                if(model.getGameBoard().getBag().getSize() == 0){//bisogna mettere delle exception dove viene pescato uno studente se non ce ne sono più, poi qui finisco il gioco.
                    endGame();
                    return 1;
                }
                for(Player p: model.getPlayers())
                    if(p.getAssistantDeck().size() == 0){
                        endGame();
                        return 1;
                    }

            } else {
                model.setCurrentPhase(GamePhase.MoveStudents);
                model.setCurrentPlayer(model.getPlayersTurnOrder()[model.getPlayerPhaseCounter()].getIndex());
            }
            return 1;
        } else
            return -1;
    }

    private void orderPlayersAssistantCard(){
        int index = -1;
        int num;
        int min = 11;
        for(Player p: model.getPlayers()){
            num = p.getCurrentCard().getValue();
            if(num < min){
                min = num;
                index = p.getIndex();
            }
            p.setCurrentCard(new AssistantCard(0, 0));
        }

        model.setCurrentPlayer(index);
        for(int i = 0; i<Parameters.numPlayers; i++)
            model.getPlayersTurnOrder()[i] = model.getPlayers().get((index + i)%Parameters.numPlayers);
    }

    private void endGame(){
        //wins the player with most number of towers
        ArrayList<Player> rank = new ArrayList<>();
        int min = Parameters.numTowers;
        for (Player p: model.getPlayers())
            if(p.getPlayerSchoolBoard().getTowers().size() < min){
                min = p.getPlayerSchoolBoard().getTowers().size();
                rank.clear();
                rank.add(p);
            } else if(p.getPlayerSchoolBoard().getTowers().size() == min)
                rank.add(p);

        //if it's a draw the player with the most number of professors will win
        if(rank.size() == 1)
            model.setWinner(rank.get(0));
        else {
            int max = 0;
            for(Player p: rank)
                if(p.getPlayerSchoolBoard().getProfessors().size() > max){
                    max = p.getPlayerSchoolBoard().getProfessors().size();
                    model.setWinner(p);
                }
        }

        model.setCurrentPhase(GamePhase.GameEnded);
    }

    public String getTheWinner(){
        return model.getWinner().getNickname();
    }

    public int getCurrentPhase() {
        return model.getCurrentPhase().ordinal();
    }

    public int getCurrentPlayer(){
        return model.getCurrentPlayer();
    }

    public Model getModel() {
        return model;
    }



    /** character card 1 effect : take 1 student from this card and place it on an island of your choice
     * .Then draw a new student from the bag and place it on this card*/
    public int playCharacterCard1(int playerIndex, int cardIndex, int colorIndex, int islandIndex){
        if(playerIndex == model.getCurrentPlayer()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost() && ((Character1) c).checkColorExists(colorIndex)){
                    System.out.println("STAI GIOCANDO LA CARTA 1");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta
                    c.play();

                    model.getGameBoard().addStudentOnIsland(islandIndex, ((Character1) c).getStudent(colorIndex));
                    ((Character1) c).addStudent(model.getGameBoard().getBag().draw());
                    return 1;
                }
            }
        }
        return -1;
    }


    /** Character card 2 effect : During this turn, you take contol of any number of Professors even
     *  if you have the same number of Students as the player who currently controls them */
    public int playCharacterCard2(int playerIndex, int cardIndex){
        if(playerIndex == model.getCurrentPlayer() && model.getCurrentPhase().ordinal() <= GamePhase.MoveMotherNature.ordinal()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 2");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    ((Character2) c).enableEffect();
                    for(int i=0; i<5; i++)
                        checkProfessorPropertyCard2(i);
                    return 1;
                }
            }
        }
        return -1;
    }


    private void checkProfessorPropertyCard2(int colorIndex){
        Player oldProfessorOwner = null;
        for (Player p : model.getPlayers())  //vedo chi aveva il professore prima
            for (Professor pr : p.getPlayerSchoolBoard().getProfessors())
                if (pr.getProfessorColour().equals(Colour.values()[colorIndex])){
                    oldProfessorOwner = p;
                    break;
                }

        ArrayList<Player> rank = new ArrayList<>();
        int max = 0;
        for (Player p : model.getPlayers()){  //trovo il giocatore con più studenti
            if(max < p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colorIndex])){
                max = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colorIndex]);
                rank.clear();
                rank.add(p);
            }else if (max == p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colorIndex]) && max != 0){  //qui ho parità
                rank.add(p);
            }
        }

        if(rank.size() == 1){
            if(oldProfessorOwner == null){
                rank.get(0).getPlayerSchoolBoard().addProfessor(Colour.values()[colorIndex]);
            } else if(!oldProfessorOwner.equals(rank.get(0))){

            }
        } else {
            for(Player p: rank)
                if(p.getIndex() == getCurrentPlayer()){
                    oldProfessorOwner.getPlayerSchoolBoard().removeProfessor(Colour.values()[colorIndex]);
                    rank.get(0).getPlayerSchoolBoard().addProfessor(Colour.values()[colorIndex]);
                }
        }
    }
    /** Character card 3 effect : Choose an island and resolve the island as if Mother Nature had ended her movement there.
     * Mother nature  will still move and the island where she ends her movement will also be resolved*/
    public int playCharacterCard3(int playerIndex, int cardIndex, int islandIndex){
        if(playerIndex == model.getCurrentPlayer()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 3");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    ((Character3) c).enableEffect();
                    checkIslandInfluence(islandIndex, playerIndex);
                    ((Character3) c).disableEffect();
                    return 1;
                }
            }
        }
        return -1;
    }

    /** Character card 4 effect : You may move Mother Nature up to 2
     * additional Island than is indicated by the Assistant card you've played*/
    public int playCharacterCard4(int playerIndex, int cardIndex){
        if(playerIndex == model.getCurrentPlayer() && model.getCurrentPhase().ordinal() <= GamePhase.MoveMotherNature.ordinal()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 4");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    ((Character4) c).enableEffect();
                    return 1;
                }
            }
        }
        return -1;
    }

    /** Character card 5 effect : Place a no Entry title on an Island of your choice . The first time Mother Nature ends her movement there
     * put the NoEntry title back onto this card DO NOT calculate influence on that island or place any towers*/
    public int playCharacterCard5(int playerIndex, int cardIndex, int islandIndex){
        if(playerIndex == model.getCurrentPlayer()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 5");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    model.getGameBoard().getIslands().get(islandIndex).addNoEntry();
                    return 1;
                }
            }
        }
        return -1;
    }

    /** Character card 6 effect : when resolving a Conquering on an island Towers do not count towards influence */
    public int playCharacterCard6(int playerIndex, int cardIndex){
        if(playerIndex == model.getCurrentPlayer() && model.getCurrentPhase().ordinal() <= GamePhase.MoveMotherNature.ordinal()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 6");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    ((Character6) c).enableEffect();
                    return 1;
                }
            }
        }
        return -1;
    }

    /** Character card 8 effect : during the influence calculation this turn, you count as having 2 more influence */
    public int playCharacterCard8(int playerIndex, int cardIndex){
        if(playerIndex == model.getCurrentPlayer() && model.getCurrentPhase().ordinal() <= GamePhase.MoveMotherNature.ordinal()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 8");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    ((Character8) c).enableEffect();
                    return 1;
                }
            }
        }
        return -1;
    }
    /** Character card 9 effect : Choose a color of student: during the influence calculation this turn, that color adds no influence*/
    public int playCharacterCard9(int playerIndex, int cardIndex, int colorIndex){
        if(playerIndex == model.getCurrentPlayer() && model.getCurrentPhase().ordinal() <= GamePhase.MoveMotherNature.ordinal()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 9");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    ((Character9) c).enableEffect(colorIndex);
                    return 1;
                }
            }
        }
        return -1;
    }

    /** Character card 10 effect : You may exchange up to 2 Students between your entrance and your dining room
     * there are 2 methods because we have to manage 2 cases:
     * 1) when you decide to move 2 students
     * 2) when you decide to move less then 2 students
     * */
    public int playCharacterCard10(int playerIndex, int cardIndex, int entranceStudent1, int diningStudent1){

        if(playerIndex == model.getCurrentPlayer() && model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[diningStudent1]) > 0){
            boolean checkStudentColor = false; // checking if students have the same colour
            for (Student s : model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance())
                if (Colour.values()[entranceStudent1] == s.getColour()) {
                    checkStudentColor = true;
                    break;
                }

            if(checkStudentColor){
                for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                    if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                        System.out.println("STAI GIOCANDO LA CARTA 10");
                        model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                        model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                        c.play();

                        model.getPlayers().get(playerIndex).getPlayerSchoolBoard().moveStudentToDiningRoom(entranceStudent1);
                        model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getDiningRoom().removeOneStudent(diningStudent1);
                        model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(new Student(Colour.values()[diningStudent1]));

                        for(Professor p: model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getProfessors())
                            if(p.getProfessorColour().ordinal() == diningStudent1){
                                model.getPlayers().get(playerIndex).getPlayerSchoolBoard().removeProfessor(Colour.values()[diningStudent1]);
                                break;
                            }

                        checkProfessorProperty(entranceStudent1);
                        checkProfessorProperty(diningStudent1);
                        return 1;
                    }
                }
            }
        }
        return -1;
    }

    public int playCharacterCard10(int playerIndex, int cardIndex, int entranceStudent1, int diningStudent1, int entranceStudent2, int diningStudent2){
        int sameCard = 0;
        if(diningStudent1 == diningStudent2)
            sameCard = 1;

        if(playerIndex == model.getCurrentPlayer() && model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[diningStudent1]) > sameCard && model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[diningStudent2]) > sameCard){
            int checkStudentColor = 0; //checking if students have the same colour
            int checkStudentColor2 = 0;
            for (Student s : model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance()){
                if (Colour.values()[entranceStudent1] == s.getColour())
                    checkStudentColor++;
                if (Colour.values()[entranceStudent2] == s.getColour())
                    checkStudentColor2++;
            }

            sameCard = 0;
            if(entranceStudent1 == entranceStudent2)
                sameCard = 1;
            if(checkStudentColor > sameCard && checkStudentColor2 > sameCard){
                for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                    if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                        System.out.println("STAI GIOCANDO LA CARTA 10");
                        model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                        model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                        c.play();

                        model.getPlayers().get(playerIndex).getPlayerSchoolBoard().moveStudentToDiningRoom(entranceStudent1);
                        model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getDiningRoom().removeOneStudent(diningStudent1);
                        model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(new Student(Colour.values()[diningStudent1]));

                        for(Professor p: model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getProfessors())
                            if(p.getProfessorColour().ordinal() == diningStudent1){
                                model.getPlayers().get(playerIndex).getPlayerSchoolBoard().removeProfessor(Colour.values()[diningStudent1]);
                                break;
                            }

                        checkProfessorProperty(entranceStudent1);
                        checkProfessorProperty(diningStudent1);

                        model.getPlayers().get(playerIndex).getPlayerSchoolBoard().moveStudentToDiningRoom(entranceStudent2);
                        model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getDiningRoom().removeOneStudent(diningStudent2);
                        model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(new Student(Colour.values()[diningStudent2]));

                        for(Professor p: model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getProfessors())
                            if(p.getProfessorColour().ordinal() == diningStudent2){
                                model.getPlayers().get(playerIndex).getPlayerSchoolBoard().removeProfessor(Colour.values()[diningStudent2]);
                                break;
                            }

                        checkProfessorProperty(entranceStudent2);
                        checkProfessorProperty(diningStudent2);
                        return 1;
                    }
                }
            }
        }
        return -1;
    }


    /** Character card 11 effect : Take 1 student from this card and place it in your dining room. Then draw a new student from the bag and place it on this card */

    public int playCharacterCard11(int playerIndex, int cardIndex, int colorIndex){
        if(playerIndex == model.getCurrentPlayer()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost() && ((Character11) c).checkColorExists(colorIndex)){
                    System.out.println("STAI GIOCANDO LA CARTA 11");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    boolean coin; //ritorna true se il giocatore merita una moneta
                    coin = model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getDiningRoom().addStudent(((Character11) c).getStudent(colorIndex));
                    if (Parameters.expertMode && coin)
                        if(model.getGameBoard().getOneCoin())
                            model.getPlayers().get(playerIndex).addCoin();


                    //controllo chi possiede il professore ora
                    checkProfessorProperty(colorIndex);

                    ((Character11) c).addStudent(model.getGameBoard().getBag().draw());
                    return 1;
                }
            }
        }
        return -1;
    }

    /** Character card 12 effect : Choose a type of student: every player (including yourself) must return 3 students of that type from
     * the dining room to the bag . if any player has fewer than 3 students of that type, return as many students as they have */
    public int playCharacterCard12(int playerIndex, int cardIndex, int colorIndex){
        if(playerIndex == model.getCurrentPlayer()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 12");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    for (Player p: model.getPlayers())
                        model.getGameBoard().getBag().fill(p.getPlayerSchoolBoard().getDiningRoom().removeThreeStudents(Colour.values()[colorIndex]));

                    //controllo chi possiede il professore ora
                    checkProfessorProperty(colorIndex);

                    return 1;
                }
            }
        }
        return -1;
    }

    /** Character card 7 effect : In setup draw 6 students and place them on this card.
     * You may take up to 3 students from this card and replace them with the same number of Students from your entrance*/
    public int playCharacterCard7(int playerIndex, int cardIndex, int cardStudent1, int entranceStudent1){
        if(playerIndex == model.getCurrentPlayer()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost() && ((Character7) c).checkColorExists(cardStudent1)){
                    //checking if the student in the entrance is right
                    boolean check1 = false;
                    for(Student s: model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance()){
                        if(entranceStudent1 == s.getColour().ordinal() && entranceStudent1 < 5)
                            check1 = true;
                    }
                    if(!check1)
                        return -1;

                    System.out.println("STAI GIOCANDO LA CARTA 7");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta
                    c.play();

                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().removeStudentFromEntrance(entranceStudent1);
                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(((Character7) c).getStudent(cardStudent1));
                    ((Character7) c).addStudent(new Student(Colour.values()[entranceStudent1]));
                    return 1;
                }
            }
        }
        return -1;
    }

    /**Cases when you will play less than 3 students
     * 2 students case here */
    public int playCharacterCard7(int playerIndex, int cardIndex, int cardStudent1, int entranceStudent1, int cardStudent2, int entranceStudent2){
        if(playerIndex == model.getCurrentPlayer()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost() && ((Character7) c).checkColorExists(cardStudent1) && ((Character7) c).checkColorExists(cardStudent2)){
                    //checking i 2 students in the entrance are right
                    boolean check1 = false;
                    boolean check2 = false;
                    for(Student s: model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance()){
                        if(entranceStudent1 == s.getColour().ordinal() && entranceStudent1 < 5)
                            check1 = true;
                        else if(entranceStudent2 != s.getColour().ordinal() && entranceStudent1 < 5)
                            check2 = true;
                    }
                    if(!(check1 && check2))
                        return -1;

                    System.out.println("STAI GIOCANDO LA CARTA 7");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta
                    c.play();

                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().removeStudentFromEntrance(entranceStudent1);
                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().removeStudentFromEntrance(entranceStudent2);

                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(((Character7) c).getStudent(cardStudent1));
                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(((Character7) c).getStudent(cardStudent2));

                    ((Character7) c).addStudent(new Student(Colour.values()[entranceStudent1]));
                    ((Character7) c).addStudent(new Student(Colour.values()[entranceStudent2]));
                    return 1;
                }
            }
        }
        return -1;
    }


    public int playCharacterCard7(int playerIndex, int cardIndex, int cardStudent1, int entranceStudent1, int cardStudent2, int entranceStudent2, int cardStudent3, int entranceStudent3){
        if(playerIndex == model.getCurrentPlayer()){
            for (CharacterCard c: model.getGameBoard().getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && model.getPlayers().get(playerIndex).getCoins() >= c.getCost() && ((Character7) c).checkColorExists(cardStudent1) && ((Character7) c).checkColorExists(cardStudent2) && ((Character7) c).checkColorExists(cardStudent3)){
                    //checking if the three students in the entrance are right
                    boolean check1 = false;
                    boolean check2 = false;
                    boolean check3 = false;
                    for(Student s: model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance()){
                        if(entranceStudent1 == s.getColour().ordinal() && entranceStudent1 < 5)
                            check1 = true;
                        else if(entranceStudent2 != s.getColour().ordinal() && entranceStudent1 < 5)
                            check2 = true;
                        else if(entranceStudent3 != s.getColour().ordinal() && entranceStudent1 < 5)
                            check3 = true;
                    }
                    if(!(check1 && check2 && check3))
                        return -1;

                    System.out.println("STAI GIOCANDO LA CARTA 7");
                    model.getPlayers().get(playerIndex).removeCoin(c.getCost());
                    model.getGameBoard().addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta
                    c.play();

                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().removeStudentFromEntrance(entranceStudent1);
                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().removeStudentFromEntrance(entranceStudent2);
                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().removeStudentFromEntrance(entranceStudent3);

                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(((Character7) c).getStudent(cardStudent1));
                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(((Character7) c).getStudent(cardStudent2));
                    model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(((Character7) c).getStudent(cardStudent3));

                    ((Character7) c).addStudent(new Student(Colour.values()[entranceStudent1]));
                    ((Character7) c).addStudent(new Student(Colour.values()[entranceStudent2]));
                    ((Character7) c).addStudent(new Student(Colour.values()[entranceStudent3]));
                    return 1;
                }
            }
        }
        return -1;
    }
}