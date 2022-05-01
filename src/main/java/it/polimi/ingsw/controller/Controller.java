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

import java.util.ArrayList;

/*
 *  In this class we manage the main actions of the match.
 */

public class Controller implements Observer<PlayerMove> {

    private Model model;

    private synchronized void performMove(PlayerMove move){
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
                //se metto system.out qui la stampa
                System.out.println("MOVE MOTHERNATURE!  1: " + move.getPlayer().getIndex() + " " + move.getParam2()+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                result = moveMotherNature(move.getPlayer().getIndex(), move.getParam2());
                //qui no
                System.out.println("MOVE MOTHERNATURE!  2\n\n");
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

            case 10: {
                if(move.getParam2() == 1){
                    result = playCharacterCard1(move.getPlayer().getIndex(), move.getParam2(), move.getParam3(), move.getParam4());
                } else if(move.getParam2() == 3){
                    result = playCharacterCard3(move.getPlayer().getIndex(), move.getParam2(), move.getParam3());
                } else if(move.getParam2() == 4){
                    result = playCharacterCard4(move.getPlayer().getIndex(), move.getParam2());
                } else if(move.getParam2() == 5){
                    result = playCharacterCard5(move.getPlayer().getIndex(), move.getParam2(), move.getParam3());
                } else if(move.getParam2() == 6){
                    result = playCharacterCard6(move.getPlayer().getIndex(), move.getParam2());
                } else if(move.getParam2() == 8){
                    result = playCharacterCard8(move.getPlayer().getIndex(), move.getParam2());
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

    public void init(){   //sto seguendo l'inizializzazione della partita
        double casual = Math.random()*12; //(PUNTO 2)
        int mn = (int) casual;
        model.setModelParameters(Parameters.numPlayers, Parameters.expertMode);

        model.getGameBoard().setMotherNature(mn);  //posiziono madrenatura

        ArrayList<Student> arr = new ArrayList<>();  //(PUNTO 3) creo studenti per le isole
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

        for (Colour c : Colour.values()) {  //creo gli studenti per il sacchetto
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

        addStudentsOnClouds();

        model.setCurrentPhase(GamePhase.PlayAssistantCard);
    }


    //Fase pianificazione punto 1
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
    //Fase pianificazione punto 2
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

            if(found == false) return -1; //non ha la carta, non ha senso proseguire, tocca ancora lui
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

    private void orderPlayerActionPhase(){  // con algoritmo bubble sort
        for(int i = 0; i < model.getPlayersTurnOrder().length; i++){
            boolean swap = false;
            for(int j = 0; j < model.getPlayersTurnOrder().length-1; j++){
                if(model.getPlayersTurnOrder()[j].getCurrentCard().getValue() > model.getPlayersTurnOrder()[j+1].getCurrentCard().getValue()) {
                    Player k = model.getPlayersTurnOrder()[j];
                    model.getPlayersTurnOrder()[j] = model.getPlayersTurnOrder()[j+1];
                    model.getPlayersTurnOrder()[j+1] = k;
                    swap = true; //segna che è avvenuto uno scambio
                }
            }
            if(!swap) break; // esce prima se ha già ordinato tutto
        }
    }

    //Fase azione punto 1
    public int moveStudentToIsland(int playerIndex, int colour, int IslandPosition){

        boolean checkStudentColor = false; // vedo se ha lo studente di quel colore
        for(Student s: model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance())
            if(Colour.values()[colour] == s.getColour())
                checkStudentColor = true;

        if(model.getCurrentPhase().equals(GamePhase.MoveStudents) && playerIndex == model.getCurrentPlayer() && checkStudentColor && colour >= 0 && colour < Colour.values().length && IslandPosition >= 0 && IslandPosition < model.getGameBoard().getIslands().size()){
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
    //Fase azione punto 1
    public int moveStudentToDiningRoom(int playerIndex, int colour){
        if (model.getCurrentPhase().equals(GamePhase.MoveStudents) && playerIndex == model.getCurrentPlayer() && colour >= 0 && colour < Colour.values().length && model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colour]) < 10){

            boolean checkStudentColor = false; // vedo se ha lo studente di quel colore
            for (Student s : model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance())
                if (Colour.values()[colour] == s.getColour())
                    checkStudentColor = true;

            if(checkStudentColor){
                boolean coin; //ritorna true se il giocatore merita una moneta
                coin = model.getPlayers().get(playerIndex).getPlayerSchoolBoard().moveStudentToDiningRoom(colour);
                if (Parameters.expertMode && coin)
                    if(model.getGameBoard().getOneCoin())
                        model.getPlayers().get(playerIndex).addCoin();

                //controllo chi possiede il professore ora

                Player oldProfessorOwner = null;
                for (Player p : model.getPlayers())  //vedo chi aveva il professore prima
                    for (Professor pr : p.getPlayerSchoolBoard().getProfessors())
                        if (pr.getProfessorColour().equals(Colour.values()[colour])){
                            oldProfessorOwner = p;
                            break;
                        }

                ArrayList<Player> rank = new ArrayList<>();
                int max = 0;
                for (Player p : model.getPlayers()){  //trovo il giocatore con più studenti
                    if(max < p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colour])){
                        max = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colour]);
                        rank.clear();
                        rank.add(p);
                    }else if (max == p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colour]) && max != 0){  //qui ho parità
                        rank.add(p);
                    }
                }

                if(rank.size() == 1){
                    if(oldProfessorOwner == null){
                        rank.get(0).getPlayerSchoolBoard().addProfessor(Colour.values()[colour]);
                    } else if(!oldProfessorOwner.equals(rank.get(0))){
                        oldProfessorOwner.getPlayerSchoolBoard().removeProfessor(Colour.values()[colour]);
                        rank.get(0).getPlayerSchoolBoard().addProfessor(Colour.values()[colour]);
                    }
                }

                model.setPhaseCounter(model.getPhaseCounter() + 1);
                if (model.getPhaseCounter()  == Parameters.numCloudStudents) {
                    model.setCurrentPhase(GamePhase.MoveMotherNature);
                    model.setPhaseCounter(0);
                }

                return 1;
            } else return -1;
        } else return -1;
    }

    //Fase azione punto 2.1
    public int moveMotherNature(int playerIndex, int movements){ //devo controllare se player ha i permessi
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
                System.out.println("Sto entrando in checkinfluence\n\n");
                this.checkIslandInfluence(newPos, playerIndex);
            } else return -1;

            model.setCurrentPhase(GamePhase.ChooseCloud);
            return 1;
        }
        else return -1;
    }
    //Fase azione punto 2.2 //vedo chi controlla l'isola, se il player è cambiato sostituisco le torri
    private void checkIslandInfluence(int islandIndex, int playerIndex){
        boolean noTowerFlag = false;
        int twoMorePointsPlayer = -1;
        if(Parameters.expertMode){
            for(CharacterCard c: model.getGameBoard().getThreeCharacterCards())
                if(c.getIndex() == 5 && model.getGameBoard().getIslands().get(islandIndex).getNoEntry() > 0){
                    model.getGameBoard().getIslands().get(islandIndex).removeNoEntry();
                    ((Character5) c).addNoEntry();
                    return;  // proseguo come se madre natura non fosse capitata qui
                } else if(c.getIndex() == 6 && ((Character6) c).isEffectFlag()){
                    ((Character6) c).disableEffect();
                    noTowerFlag = true;
                } else if(c.getIndex() == 8 && ((Character8) c).isEffectFlag()){
                    ((Character8) c).disableEffect();
                    twoMorePointsPlayer = playerIndex;
                }

        }
        System.out.println("sto entrando in influence\n\n");
        // vedo chi ha più influenza ora
        Player newPlayer = model.getGameBoard().getIslands().get(islandIndex).Influence(model.getPlayers(), noTowerFlag, twoMorePointsPlayer);
        System.out.println("sto uscendo in influence\n\n");

        if (newPlayer!=null){
            Player oldPlayer = null;  // vedo chi controllava l'isola prima
            for (Player pl: model.getPlayers()){
                if(pl.PlayerTowerColor().equals(model.getGameBoard().getIslands().get(islandIndex).getTowerColor()))
                    oldPlayer = pl;
            }
            System.out.println("ho calcolato oldplayer\n\n");

            if(oldPlayer == null || !oldPlayer.equals(newPlayer)){
                //ridò al vecchio giocatore le sue torri e le sostituisco con quelle del nuovo giocatore
                for(int i=0; i<=model.getGameBoard().getIslands().get(islandIndex).getNumTower(); i++){
                    if(oldPlayer != null)
                        oldPlayer.getPlayerSchoolBoard().addTower(oldPlayer.PlayerTowerColor());
                    newPlayer.getPlayerSchoolBoard().getTowers().remove(0);
                }
                if(oldPlayer == null)
                    this.getGameBoard().getIslands().get(islandIndex).setNumTower(1);

                System.out.println("sto cambiando colore torri isola\n\n");
                this.getGameBoard().getIslands().get(islandIndex).changeTowerColor(newPlayer.PlayerTowerColor());

                if(newPlayer.getPlayerSchoolBoard().getTowers().size() == 0){
                    model.setWinner(newPlayer);
                    model.setCurrentPhase(GamePhase.GameEnded);
                    return;
                }
                System.out.println("sto entrando in fusion\n\n");
                checkIslandFusion(islandIndex);
            }
            System.out.println("faccio if? riga 380\n\n"); //spoiler: si, visto che questa riga non viene stampata

        }
        //se nessuno ha l'influenza o in caso di parità non succede nulla
    }
    //Fase azione punto 2.3
    private void checkIslandFusion(int islandIndex){
        //se il colore delle torri sull'isola e su quella successiva sono uguali
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
        //se il colore delle torri sull'isola e su quella precedente sono uguali
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

    //Fase azione punto 3
    public int chooseCloud(int playerIndex, int cloudPosition){
        if(model.getCurrentPhase().equals(GamePhase.ChooseCloud) && playerIndex == model.getCurrentPlayer() && cloudPosition >= 0 && cloudPosition < Parameters.numClouds && !model.getGameBoard().getClouds().get(cloudPosition).isTaken()){
            ArrayList<Student> stud = model.getGameBoard().getClouds().get(cloudPosition).getStudents();

            for (Student s: stud){ //qui e alla fine del metodo init() farei un nuovo metodo add che controlla anche se non viene superato il limite di studenti all'entrata
                model.getPlayers().get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(s);
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
        }

        model.setCurrentPlayer(index);
        for(int i = 0; i<Parameters.numPlayers; i++)
            model.getPlayersTurnOrder()[i] = model.getPlayers().get((index + i)%Parameters.numPlayers);
    }

    private void endGame(){
        //vince il giocatore che ha costruito il maggior numero di torri
        ArrayList<Player> rank = new ArrayList<>();
        int min = Parameters.numTowers;
        for (Player p: model.getPlayers())
            if(p.getPlayerSchoolBoard().getTowers().size() < min){
                min = p.getPlayerSchoolBoard().getTowers().size();
                rank.clear();
                rank.add(p);
            } else if(p.getPlayerSchoolBoard().getTowers().size() == min)
                rank.add(p);

        //in caso di parità chi ha piu professori
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
                            oldProfessorOwner.getPlayerSchoolBoard().removeProfessor(Colour.values()[colorIndex]);
                            rank.get(0).getPlayerSchoolBoard().addProfessor(Colour.values()[colorIndex]);
                        }
                    }

                    ((Character11) c).addStudent(model.getGameBoard().getBag().draw());
                    return 1;
                }
            }
        }
        return -1;
    }

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
                            oldProfessorOwner.getPlayerSchoolBoard().removeProfessor(Colour.values()[colorIndex]);
                            rank.get(0).getPlayerSchoolBoard().addProfessor(Colour.values()[colorIndex]);
                        }
                    }

                    return 1;
                }
            }
        }
        return -1;
    }
}