module it.polimi.ingsw.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens it.polimi.ingsw.client.GUI.controllers to javafx.fxml;
    exports it.polimi.ingsw.client;
}