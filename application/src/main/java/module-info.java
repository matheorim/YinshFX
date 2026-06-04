module but.info.sae2_12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires HexagonalCoordinate;
    requires java.desktop;

    opens but.info.sae2_12 to javafx.fxml;
    opens  but.info.sae2_12.model to javafx.base;
    opens but.info.sae2_12.model.state to javafx.base;
    exports but.info.sae2_12;
    opens but.info.sae2_12.controllers to javafx.fxml;
}
