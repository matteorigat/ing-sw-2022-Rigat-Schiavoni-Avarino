package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Parameters;

import java.util.ArrayList;

public class CharacterDeck {
    private ArrayList<CharacterCard> characterCards;

    public ArrayList<CharacterCard> getThreeRandomCards(){
        ArrayList<CharacterCard> threeCards = null;
        double n;
        int k;

        for (int i=0; i<3; i++){
            n =  Math.random()* Parameters.numCharacterCards;
            k = (int) n + 1;
            threeCards.add(characterCards.get(k));
            characterCards.remove(k);
        }

        return threeCards;
    }
}
