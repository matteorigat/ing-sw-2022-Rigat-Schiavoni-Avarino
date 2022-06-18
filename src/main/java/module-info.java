module it.polimi.ingsw.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens it.polimi.ingsw.client.GUI.controllers to javafx.fxml;
    exports it.polimi.ingsw.client;
    opens it.polimi.ingsw.client.GUI.controllers.characters to javafx.fxml;
}