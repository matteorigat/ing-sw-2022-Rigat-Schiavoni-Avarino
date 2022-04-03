package it.polimi.ingsw;

import it.polimi.ingsw.model.AssistantCard;
import junit.framework.TestCase;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AssistantCardTest extends TestCase {

    @Test
    void getNumber() {
        AssistantCard card = new AssistantCard(1,1);
        assertEquals(2, card.getValue());
    }
//bvdhhjdhd
}