package it.polimi.ingsw.client.GUI.controllers;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.client.GUI.ClientGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainPageController implements GuiController {

    private ClientAppGUI gui;

    @FXML private TextField ip;
    @FXML private TextField port;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("ip: " + ip.getText() + "\nport: " + port.getText());

        ClientGUI clientGUI = new ClientGUI(ip.getText(), Integer.parseInt(port.getText())); //192.168.100.10
        try {
            clientGUI.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        gui.changeStage("njfrbfrh");
    }

    @Override
    public void setGui(ClientAppGUI gui) {
        this.gui = gui;
    }
}
