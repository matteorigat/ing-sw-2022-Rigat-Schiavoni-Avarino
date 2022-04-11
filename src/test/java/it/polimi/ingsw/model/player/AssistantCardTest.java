package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.player.AssistantCard;

import static org.junit.Assert.*;
import org.junit.Test;

public class AssistantCardTest {

    @Test
    public void testCard() {
        AssistantCard card = new AssistantCard(2,1);
        assertNotNull(card);
        assertEquals(2, card.getValue());
        assertEquals(1,card.getMovements());
    }

}