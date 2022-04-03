package it.polimi.ingsw;

import it.polimi.ingsw.model.AssistantCard;
import junit.framework.TestCase;
import org.junit.Test;

public class AssistantCardTest extends TestCase {

    @Test
    public void getNumber() {
        AssistantCard card = new AssistantCard(2,1);
        assertEquals(2, card.getValue());
        assertEquals(1,card.getMovements());
    }

}