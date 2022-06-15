package it.polimi.ingsw.model.player;

import java.io.Serializable;


/** AssistantCard class defines every assistant card with the integers value and movements attributes */
public class AssistantCard implements Serializable {

    private int value;
    private int movements;

    public AssistantCard(int value, int movements) {
        this.value = value;
        this.movements = movements;
    }

    /**
     * getValue method returns the value of the assistant card
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * getMovements method returns the value of the movements of the assistant card
     *
     * @return movements
     */
    public int getMovements() {
        return movements;
    }
}
