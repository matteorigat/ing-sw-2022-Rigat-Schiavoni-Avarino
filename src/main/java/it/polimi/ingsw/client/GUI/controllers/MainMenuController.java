package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.client.GUI.ClientGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainMenuController {

    private ClientAppGUI gui;

    @FXML private TextField ip;
    @FXML private TextField port;

    /**
     * onButtonClick method gets the user input written in the button
     */
    @FXML
    protected void onButtonClick() {
        ClientGUI clientGUI;

        if(ip.getText() == "")
            clientGUI = new ClientGUI("127.0.0.1", 1337, gui);
        else
            clientGUI = new ClientGUI(ip.getText(), Integer.parseInt(port.getText()), gui); //192.168.100.10

        Thread clientThread = new Thread(clientGUI);
        clientThread.start();
        gui.setClientGUI(clientGUI);

    }

    /**
     * clearIpPost method clears port and ip
     */
    public void clearIpPort() {
        this.ip.clear();
        this.port.clear();
    }

    /**
     * setGui method sets GUI
     * @param gui
     */
    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
