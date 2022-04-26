package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.gameboard.characters.*;
import it.polimi.ingsw.model.gameboard.characters.CharacterCard;
import it.polimi.ingsw.model.gameboard.Cloud;
import it.polimi.ingsw.model.gameboard.GameBoard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.AssistantCard;
import it.polimi.ingsw.model.player.Professor;
import it.polimi.ingsw.model.player.Student;

import java.util.ArrayList;

/*
 *  In this class we manage the main actions of the match.
 */

public class Game {
    private ArrayList<Player> players;
    private GameBoard gameBoard;
    private Player winner;
    private int currentPlayer;
    private GamePhase currentPhase;
    private Player[] playersTurnOrder;
    private int phaseCounter;
    private int playerPhaseCounter;

    //Gets the players of the match
    public ArrayList<Player> getPlayers() {
        return players;
    }

    //Gets the gameBoard instance
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setParameters(int numPlayers, Boolean expertMode){
        Parameters.setParameters(numPlayers, expertMode);
        initGame();
    }

    //Constructor Game creates a new Game instance
    private void initGame() {
        this.players = new ArrayList<>();
        this.gameBoard = new GameBoard();

        this.playersTurnOrder = new Player[Parameters.numPlayers];
        this.phaseCounter = 0;
        this.playerPhaseCounter = 0;
    }

    public int addPlayer(String nickname){

        if(players.size() < Parameters.numPlayers) {
            Player p = new Player(nickname, players.size());
            players.add(p);
            return players.size()-1;
        }
        else{
            //qui bisogna mettere una exception che nella partita ci sono già 4 giocatori
            return -1;
        }
    }

    public void init(){   //sto seguendo l'inizializzazione della partita
        double casual = Math.random()*12; //(PUNTO 2)
        int mn = (int) casual;

        gameBoard.setMotherNature(mn);  //posiziono madrenatura

        ArrayList<Student> arr = new ArrayList<>();  //(PUNTO 3) creo studenti per le isole
        for (Colour c : Colour.values()) {
            for(int i=0;i<2;i++){
                Student s = new Student(c);
                arr.add(s);
            }
        }
        gameBoard.getBag().fill(arr);

        for (int i=0; i<12; i++){
            if(i != gameBoard.getMotherNature() && i != (gameBoard.getMotherNature() + 6)%12) //doesn't place students on the island with mothernature and the opposite one
                gameBoard.addStudentOnIsland(i, gameBoard.getBag().draw());

        }

        for (Colour c : Colour.values()) {  //creo gli studenti per il sacchetto
            for(int i=0;i<24;i++){
                Student s = new Student(c);
                arr.add(s);
            }
        }
        gameBoard.getBag().fill(arr);

        for (Player p: players){
            for (int i=0; i<Parameters.entranceStudents; i++)
                p.getPlayerSchoolBoard().getStudentsEntrance().add(gameBoard.getBag().draw());
        }

        if(Parameters.expertMode){
            gameBoard.chooseThreeCards();
        }

        currentPlayer = 0;
        for(int i = 0; i<Parameters.numPlayers; i++){
            playersTurnOrder[i] = players.get(i);
        }

        addStudentsOnClouds();

        this.currentPhase = GamePhase.PlayAssistantCard;
    }


    //Fase pianificazione punto 1
    private void addStudentsOnClouds(){
        int num = 0;
        for(Cloud c: gameBoard.getClouds()){
            for (int i = 0; i < Parameters.numCloudStudents; i++)
                if(gameBoard.getBag().getSize() > 0)
                    gameBoard.addStudentOnCloud(num, gameBoard.getBag().draw());
            num++;
            c.setTaken(false);
        }
    }
    //Fase pianificazione punto 2
    public int playAssistantCard(int playerIndex, int priority){
        if(currentPhase.equals(GamePhase.PlayAssistantCard) && playerIndex == currentPlayer && priority > 0 && priority <= 10) {
            boolean alreadyPlayed = false;
            for(int i=0; i<phaseCounter; i++) {
                if (priority == playersTurnOrder[i].getCurrentCard().getValue()) {
                    alreadyPlayed = true;
                    break;
                }
            }
            if(alreadyPlayed) {
                int check = 0;
                for (AssistantCard as : players.get(playerIndex).getAssistantDeck()) {
                    for (int i = 0; i < phaseCounter; i++) {
                        if (as.getValue() == playersTurnOrder[i].getCurrentCard().getValue()) {
                            check++;
                        }
                    }
                }

                if (check != players.get(playerIndex).getAssistantDeck().size())
                    return -1;
            }

            boolean found = false;
            ArrayList<AssistantCard> clone = (ArrayList<AssistantCard>) players.get(playerIndex).getAssistantDeck().clone();
            for (AssistantCard as : clone)
                if (as.getValue() == priority){
                    players.get(playerIndex).playAssistantCard(as);//QUI ERRORE CUNCURRENT!!!!

                    found = true;
                    break;
                }

            if(found == false) return -1; //non ha la carta, non ha senso proseguire, tocca ancora lui
            if(phaseCounter < Parameters.numPlayers-1) {   //AGGIUNTA GIUS ALTRIMENTI SFORA LA DIMENSIONE DELL'ARRAY
                currentPlayer = playersTurnOrder[phaseCounter + 1].getIndex();

            }
            phaseCounter++;
            if (phaseCounter == Parameters.numPlayers) {
                currentPhase = GamePhase.MoveStudents;
                phaseCounter = 0;
                this.orderPlayerActionPhase();
                currentPlayer = playersTurnOrder[0].getIndex();

            }
            return 1;
        }
        else return -1;
    }

    private void orderPlayerActionPhase(){  // con algoritmo bubble sort
        for(int i = 0; i < playersTurnOrder.length; i++){
            boolean swap = false;
            for(int j = 0; j < playersTurnOrder.length-1; j++){
                if(playersTurnOrder[j].getCurrentCard().getValue() > playersTurnOrder[j+1].getCurrentCard().getValue()) {
                    Player k = playersTurnOrder[j];
                    playersTurnOrder[j] = playersTurnOrder[j+1];
                    playersTurnOrder[j+1] = k;
                    swap = true; //segna che è avvenuto uno scambio
                }
            }
            if(!swap) break; // esce prima se ha già ordinato tutto
        }
    }

    //Fase azione punto 1
    public int moveStudentToIsland(int playerIndex, int colour, int IslandPosition){

        boolean checkStudentColor = false; // vedo se ha lo studente di quel colore
        for(Student s: players.get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance())
            if(Colour.values()[colour] == s.getColour())
                checkStudentColor = true;

        if(currentPhase.equals(GamePhase.MoveStudents) && playerIndex == currentPlayer && checkStudentColor && colour >= 0 && colour < Colour.values().length && IslandPosition >= 0 && IslandPosition < gameBoard.getIslands().size()){
            players.get(playerIndex).getPlayerSchoolBoard().moveStudentToIsland(colour, this.gameBoard.getIslands().get(IslandPosition));

            phaseCounter++;
            if(phaseCounter == Parameters.numCloudStudents){
                currentPhase = GamePhase.MoveMotherNature;
                phaseCounter = 0;
            }
            return 1;
        }
        else return -1;
    }
    //Fase azione punto 1
    public int moveStudentToDiningRoom(int playerIndex, int colour){
        if (currentPhase.equals(GamePhase.MoveStudents) && playerIndex == currentPlayer && colour >= 0 && colour < Colour.values().length && players.get(playerIndex).getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colour]) < 10){

            boolean checkStudentColor = false; // vedo se ha lo studente di quel colore
            for (Student s : players.get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance())
                if (Colour.values()[colour] == s.getColour())
                    checkStudentColor = true;

            if(checkStudentColor){
                boolean coin; //ritorna true se il giocatore merita una moneta
                coin = players.get(playerIndex).getPlayerSchoolBoard().moveStudentToDiningRoom(colour);
                if (Parameters.expertMode && coin)
                    if(gameBoard.getOneCoin())
                        players.get(playerIndex).addCoin();

                //controllo chi possiede il professore ora

                Player oldProfessorOwner = null;
                for (Player p : players)  //vedo chi aveva il professore prima
                    for (Professor pr : p.getPlayerSchoolBoard().getProfessors())
                        if (pr.getProfessorColour().equals(Colour.values()[colour])){
                            oldProfessorOwner = p;
                            break;
                        }

                ArrayList<Player> rank = new ArrayList<>();
                int max = 0;
                for (Player p : players){  //trovo il giocatore con più studenti
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

                phaseCounter++;
                if (phaseCounter == Parameters.numCloudStudents) {
                    currentPhase = GamePhase.MoveMotherNature;
                    phaseCounter = 0;
                }

                return 1;
            } else return -1;
        } else return -1;
    }

    //Fase azione punto 2.1
    public int moveMotherNature(int playerIndex, int movements){ //devo controllare se player ha i permessi
        if(currentPhase.equals(GamePhase.MoveMotherNature) && playerIndex == currentPlayer){
            int maxMovements = players.get(currentPlayer).getCurrentCard().getMovements();
            if(Parameters.expertMode && movements <= maxMovements+2){
                for(CharacterCard c: gameBoard.getThreeCharacterCards())
                    if(c.getIndex() == 4 && ((Character4) c).isEffectFlag()){
                        maxMovements += 2;
                        ((Character4) c).disableEffect();
                    }
            }
            if(movements > 0 && movements <= maxMovements){
                int newPos = (this.gameBoard.getMotherNature() + movements)%gameBoard.getIslands().size();
                this.gameBoard.setMotherNature(newPos);//moves motherNature by specified movements
                this.checkIslandInfluence(newPos, playerIndex);
            } else return -1;

            currentPhase = GamePhase.ChooseCloud;
            return 1;
        }
        else return -1;
    }
    //Fase azione punto 2.2 //vedo chi controlla l'isola, se il player è cambiato sostituisco le torri
    private void checkIslandInfluence(int islandIndex, int playerIndex){
        boolean noTowerFlag = false;
        int twoMorePointsPlayer = -1;
        if(Parameters.expertMode){
            for(CharacterCard c: gameBoard.getThreeCharacterCards())
                if(c.getIndex() == 5 && gameBoard.getIslands().get(islandIndex).getNoEntry() > 0){
                    gameBoard.getIslands().get(islandIndex).removeNoEntry();
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

        // vedo chi ha più influenza ora
        Player newPlayer = this.gameBoard.getIslands().get(islandIndex).Influence(this.players, noTowerFlag, twoMorePointsPlayer);

        if (newPlayer!=null){
            Player oldPlayer = null;  // vedo chi controllava l'isola prima
            for (Player pl: players){
                if(pl.PlayerTowerColor().equals(this.gameBoard.getIslands().get(islandIndex).getTowerColor()))
                    oldPlayer = pl;
            }

            if(oldPlayer == null || !oldPlayer.equals(newPlayer)){
                //ridò al vecchio giocatore le sue torri e le sostituisco con quelle del nuovo giocatore
                for(int i=0; i<=this.gameBoard.getIslands().get(islandIndex).getNumTower(); i++){
                    if(oldPlayer != null)
                        oldPlayer.getPlayerSchoolBoard().addTower(oldPlayer.PlayerTowerColor());
                    newPlayer.getPlayerSchoolBoard().getTowers().remove(0);
                }
                if(oldPlayer == null)
                    this.getGameBoard().getIslands().get(islandIndex).setNumTower(1);

                this.getGameBoard().getIslands().get(islandIndex).changeTowerColor(newPlayer.PlayerTowerColor());

                if(newPlayer.getPlayerSchoolBoard().getTowers().size() == 0){
                    winner = newPlayer;
                    currentPhase = GamePhase.GameEnded;
                    return;
                }

                checkIslandFusion(islandIndex);
            }

        }
        //se nessuno ha l'influenza o in caso di parità non succede nulla
    }
    //Fase azione punto 2.3
    private void checkIslandFusion(int islandIndex){
        //se il colore delle torri sull'isola e su quella successiva sono uguali
        while (gameBoard.getIslands().get(islandIndex).getTowerColor().equals(gameBoard.getIslands().get((islandIndex+1)%gameBoard.getIslands().size()).getTowerColor())){
            if(Parameters.expertMode) {
                for (CharacterCard c : gameBoard.getThreeCharacterCards())
                    if (c.getIndex() == 3 && ((Character3) c).isEffectFlag() && gameBoard.getMotherNature() >= (islandIndex + 1) % gameBoard.getIslands().size()) {
                        int mn = gameBoard.getMotherNature() - 1;
                        if (mn == -1)
                            mn = gameBoard.getIslands().size() - 1;
                        gameBoard.setMotherNature(mn);
                    }
            }
            this.gameBoard.islandFusion(islandIndex, (islandIndex+1)%gameBoard.getIslands().size());
        }

        int newPosition = islandIndex;
        int newPosition2 = newPosition-1;
        if (newPosition2 == -1)
            newPosition2 = gameBoard.getIslands().size()-1;
        //se il colore delle torri sull'isola e su quella precedente sono uguali
        while (gameBoard.getIslands().get(newPosition).getTowerColor().equals(gameBoard.getIslands().get(newPosition2).getTowerColor())){
            this.gameBoard.islandFusion(newPosition2, newPosition);

            if(!Parameters.expertMode){
                if(newPosition2 == gameBoard.getIslands().size())
                    newPosition2 = gameBoard.getIslands().size()-1;

                gameBoard.setMotherNature(newPosition2);
            } else {
                boolean card3 = false;
                for(CharacterCard c: gameBoard.getThreeCharacterCards())
                    if(c.getIndex() == 3 && ((Character3) c).isEffectFlag())
                        card3 = true;

                if(!card3){
                    if(newPosition2 == gameBoard.getIslands().size())
                        newPosition2 = gameBoard.getIslands().size()-1;

                    gameBoard.setMotherNature(newPosition2);
                }
                else if(gameBoard.getMotherNature() >= (newPosition2+1)%(gameBoard.getIslands().size()+1)){
                    int mn = gameBoard.getMotherNature()-1;
                    if(mn == -1)
                        mn = gameBoard.getIslands().size()-1;
                    gameBoard.setMotherNature(mn);
                }
            }

            newPosition--;
            if(newPosition == -1)
                newPosition = this.gameBoard.getIslands().size()-1;
            newPosition2 = newPosition-1;
            if (newPosition2 == -1)
                newPosition2 = this.gameBoard.getIslands().size()-1;
        }

        if(this.gameBoard.getIslands().size() <= 3){
            endGame();
            return;
        }
    }

    //Fase azione punto 3
    public int chooseCloud(int playerIndex, int cloudPosition){
        if(currentPhase.equals(GamePhase.ChooseCloud) && playerIndex == currentPlayer && cloudPosition >= 0 && cloudPosition < Parameters.numClouds && !gameBoard.getClouds().get(cloudPosition).isTaken()){
            ArrayList<Student> stud = this.gameBoard.getClouds().get(cloudPosition).getStudents();

            for (Student s: stud){ //qui e alla fine del metodo init() farei un nuovo metodo add che controlla anche se non viene superato il limite di studenti all'entrata
                players.get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(s);
            }

            playerPhaseCounter++;
            if(playerPhaseCounter == Parameters.numPlayers){
                playerPhaseCounter = 0;
                addStudentsOnClouds();
                orderPlayersAssistantCard();
                currentPhase = GamePhase.PlayAssistantCard;


                if(gameBoard.getBag().getSize() == 0){//bisogna mettere delle exception dove viene pescato uno studente se non ce ne sono più, poi qui finisco il gioco.
                    endGame();
                    return 1;
                }
                for(Player p: players)
                    if(p.getAssistantDeck().size() == 0){
                        endGame();
                        return 1;
                    }

            } else {
                currentPhase = GamePhase.MoveStudents;
                currentPlayer = playersTurnOrder[playerPhaseCounter].getIndex();
            }
            return 1;
        } else
            return -1;
    }

    private void orderPlayersAssistantCard(){
        int index = -1;
        int num;
        int min = 11;
        for(Player p: players){
            num = p.getCurrentCard().getValue();
            if(num < min){
                min = num;
                index = p.getIndex();
            }
        }

        currentPlayer = index;
        for(int i = 0; i<Parameters.numPlayers; i++)
            playersTurnOrder[i] = players.get((index + i)%Parameters.numPlayers);
    }

    private void endGame(){
        //vince il giocatore che ha costruito il maggior numero di torri
        ArrayList<Player> rank = new ArrayList<>();
        int min = Parameters.numTowers;
        for (Player p: players)
            if(p.getPlayerSchoolBoard().getTowers().size() < min){
                min = p.getPlayerSchoolBoard().getTowers().size();
                rank.clear();
                rank.add(p);
            } else if(p.getPlayerSchoolBoard().getTowers().size() == min)
                rank.add(p);

        //in caso di parità chi ha piu professori
        if(rank.size() == 1)
            winner = rank.get(0);
        else {
            int max = 0;
            for(Player p: rank)
                if(p.getPlayerSchoolBoard().getProfessors().size() > max){
                    max = p.getPlayerSchoolBoard().getProfessors().size();
                    winner = p;
                }
        }

        currentPhase = GamePhase.GameEnded;
    }

    public String getTheWinner(){
        return winner.getNickname();
    }

    public int getCurrentPhase() {
        return currentPhase.ordinal();
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }











    public int playCharacterCard1(int playerIndex, int cardIndex, int colorIndex, int islandIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost() && ((Character1) c).checkColorExists(colorIndex)){
                    System.out.println("STAI GIOCANDO LA CARTA 1");
                    players.get(playerIndex).removeCoin(c.getCost());
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta
                    c.play();

                    gameBoard.addStudentOnIsland(islandIndex, ((Character1) c).getStudent(colorIndex));
                    ((Character1) c).addStudent(gameBoard.getBag().draw());
                    return 1;
                }
            }
        }
        return -1;
    }

    public int playCharacterCard3(int playerIndex, int cardIndex, int islandIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 3");
                    players.get(playerIndex).removeCoin(c.getCost());
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1);
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
        if(playerIndex == currentPlayer && currentPhase.ordinal() <= GamePhase.MoveMotherNature.ordinal()){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 4");
                    players.get(playerIndex).removeCoin(c.getCost());
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    ((Character4) c).enableEffect();
                    return 1;
                }
            }
        }
        return -1;
    }

    public int playCharacterCard5(int playerIndex, int cardIndex, int islandIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 5");
                    players.get(playerIndex).removeCoin(c.getCost());
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    gameBoard.getIslands().get(islandIndex).addNoEntry();
                    return 1;
                }
            }
        }
        return -1;
    }

    public int playCharacterCard6(int playerIndex, int cardIndex){
        if(playerIndex == currentPlayer && currentPhase.ordinal() <= GamePhase.MoveMotherNature.ordinal()){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 6");
                    players.get(playerIndex).removeCoin(c.getCost());
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    ((Character6) c).enableEffect();
                    return 1;
                }
            }
        }
        return -1;
    }

    public int playCharacterCard8(int playerIndex, int cardIndex){
        if(playerIndex == currentPlayer && currentPhase.ordinal() <= GamePhase.MoveMotherNature.ordinal()){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 8");
                    players.get(playerIndex).removeCoin(c.getCost());
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    ((Character8) c).enableEffect();
                    return 1;
                }
            }
        }
        return -1;
    }

    public int playCharacterCard11(int playerIndex, int cardIndex, int colorIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost() && ((Character11) c).checkColorExists(colorIndex)){
                    System.out.println("STAI GIOCANDO LA CARTA 11");
                    players.get(playerIndex).removeCoin(c.getCost());
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    boolean coin; //ritorna true se il giocatore merita una moneta
                    coin = players.get(playerIndex).getPlayerSchoolBoard().getDiningRoom().addStudent(((Character11) c).getStudent(colorIndex));
                    if (Parameters.expertMode && coin)
                        if(gameBoard.getOneCoin())
                            players.get(playerIndex).addCoin();


                    //controllo chi possiede il professore ora

                    Player oldProfessorOwner = null;
                    for (Player p : players)  //vedo chi aveva il professore prima
                        for (Professor pr : p.getPlayerSchoolBoard().getProfessors())
                            if (pr.getProfessorColour().equals(Colour.values()[colorIndex])){
                                oldProfessorOwner = p;
                                break;
                            }

                    ArrayList<Player> rank = new ArrayList<>();
                    int max = 0;
                    for (Player p : players){  //trovo il giocatore con più studenti
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

                    ((Character11) c).addStudent(gameBoard.getBag().draw());
                    return 1;
                }
            }
        }
        return -1;
    }

    public int playCharacterCard12(int playerIndex, int cardIndex, int colorIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    System.out.println("STAI GIOCANDO LA CARTA 12");
                    players.get(playerIndex).removeCoin(c.getCost());
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1);
                    c.play();

                    for (Player p: players)
                            gameBoard.getBag().fill(p.getPlayerSchoolBoard().getDiningRoom().removeThreeStudents(Colour.values()[colorIndex]));

                    return 1;
                }
            }
        }
        return -1;
    }
}