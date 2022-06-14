package it.polimi.ingsw.client.GUI.controllers.characters;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.model.Model;
import javafx.stage.Stage;


/**
 * Character class defines an interface for all the character cards.
 *
 */
public interface Character {

    public void setGui(ClientAppGUI gui, Stage dialog);

    public void setModel(Model model, int cardPosition);


}
