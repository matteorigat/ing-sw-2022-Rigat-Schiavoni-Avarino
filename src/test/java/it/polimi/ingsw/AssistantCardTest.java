package it.polimi.ingsw;

import it.polimi.ingsw.model.AssistantCard;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;


public class AssistantCardTest extends TestCase {

    @Test
    public void testCard() {
        AssistantCard card = new AssistantCard(2,1);
        assertNotNull(card);
        assertEquals(2, card.getValue());
        assertEquals(1,card.getMovements());
    }

}