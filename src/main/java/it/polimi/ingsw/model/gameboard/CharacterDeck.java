package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.gameboard.characters.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class CharacterDeck implements Serializable {
    private ArrayList<CharacterCard> characterCards;

    public CharacterDeck() {
        characterCards = new ArrayList<CharacterCard>();
        characterCards.add(new Character2());
        characterCards.add(new Character7());
        characterCards.add(new Character9());
        characterCards.add(new Character10());

        characterCards.add(new Character1());
        characterCards.add(new Character3());
        characterCards.add(new Character4());
        characterCards.add(new Character5());
        characterCards.add(new Character6());
        characterCards.add(new Character8());
        characterCards.add(new Character11());
        characterCards.add(new Character12());


    }

    public ArrayList<CharacterCard> getThreeRandomCards(){
        ArrayList<CharacterCard> threeCards = new ArrayList<CharacterCard>();

        Collections.shuffle(this.characterCards);

        for (int i=0; i<3; i++){
            threeCards.add(characterCards.get(0));
            characterCards.remove(0);
        }

        return threeCards;
    }

    public ArrayList<CharacterCard> getCharacterCards() {
        return characterCards;
    }
}
