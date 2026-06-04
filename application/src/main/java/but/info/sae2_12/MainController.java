package but.info.sae2_12;

import but.info.sae2_12.controllers.BottomController;
import but.info.sae2_12.controllers.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private BottomController bottomController;
    @FXML private MenuController menuController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (bottomController != null) bottomController.setMainController(this);
        if (menuController != null) menuController.setMainController(this);
    }
}
