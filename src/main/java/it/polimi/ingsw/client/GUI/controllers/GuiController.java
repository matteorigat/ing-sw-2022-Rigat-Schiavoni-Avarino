package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;

/**
 * GUIController class defines an interface representing a single GUI controller, which is different
 * from phase to phase.
 *
 * @author Luca Pirovano
 */
public interface GuiController {
  /**
   * Method setGui sets the GUI reference to the local controller.
   *
   * @param gui of type GUI - the main GUI class.
   */
  void setGui(ClientAppGUI gui);
}
