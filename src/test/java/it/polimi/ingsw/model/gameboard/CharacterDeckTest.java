package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.gameboard.CharacterDeck;
import it.polimi.ingsw.model.gameboard.characters.CharacterCard;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharacterDeckTest{

    @Test
    public void testCharacterDeck(){
        CharacterDeck cd = new CharacterDeck();
        ArrayList<CharacterCard> characters = cd.getThreeRandomCards();
        CharacterCard cc1 = characters.get(0);
        CharacterCard cc2 = characters.get(0);
        CharacterCard cc3 = characters.get(0);
        ArrayList<CharacterCard> allCharacter = cd.getCharacterCards();
        boolean bool = true;
        if(allCharacter.contains(cc1) && allCharacter.contains(cc2) && allCharacter.contains(cc3)){
            bool = false;
        }
        assertTrue(bool);
    }

}
