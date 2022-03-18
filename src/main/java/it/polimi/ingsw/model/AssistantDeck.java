package it.polimi.ingsw.model;

import java.util.ArrayList;

public class AssistantDeck {

    private ArrayList<AssistantCard>assistantDeck = new ArrayList<>(10);

    //initialization of Assistant deck
    public void initAssistantDeck(){
        assistantDeck.add(new AssistantCard(1,1));
        assistantDeck.add(new AssistantCard(2,1));
        assistantDeck.add(new AssistantCard(3,2));
        assistantDeck.add(new AssistantCard(4,2));
        assistantDeck.add(new AssistantCard(5,3));
        assistantDeck.add(new AssistantCard(6,3));
        assistantDeck.add(new AssistantCard(7,4));
        assistantDeck.add(new AssistantCard(8,4));
        assistantDeck.add(new AssistantCard(9,5));
        assistantDeck.add(new AssistantCard(10,5));



    }

    public void printDeck(){
        for(int i=0;i< assistantDeck.size();i++){
            System.out.println(assistantDeck.get(i).getValue() + " " + assistantDeck.get(i).getMovements());
        }
    }


    // play a card and remove it from the deck (da implementare le funzionalità di giocata)
    public void playCard(AssistantCard cardPlayed){
        for(int i =0;i<assistantDeck.size();i++){
            if(cardPlayed.getValue() == assistantDeck.get(i).getValue() && cardPlayed.getMovements() == assistantDeck.get(i).getMovements()){
                assistantDeck.remove(i);
            }
        }
    }

}
