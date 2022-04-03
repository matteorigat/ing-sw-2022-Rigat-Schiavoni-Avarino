package it.polimi.ingsw;
import it.polimi.ingsw.model.gameboard.GameBoard;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class GameBoardTest extends TestCase {

    @Test
    public void testGameBoard(){
        GameBoard gb = new GameBoard();
        gb.setMotherNature(1);
        assertEquals(gb.getMotherNature(),1);


    }
}
