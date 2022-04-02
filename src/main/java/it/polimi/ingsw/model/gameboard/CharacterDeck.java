package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.gameboard.Characters.*;

import java.util.ArrayList;

public class CharacterDeck {
    private ArrayList<CharacterCard> characterCards;

    public CharacterDeck() {
        characterCards.add(new Character1());
        characterCards.add(new Character2());
        characterCards.add(new Character3());
        characterCards.add(new Character4());
        characterCards.add(new Character5());
        characterCards.add(new Character6());
        characterCards.add(new Character7());
        characterCards.add(new Character8());
    }

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
